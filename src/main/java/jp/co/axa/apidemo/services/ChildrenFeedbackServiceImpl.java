package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.ChildrenFeedbackDTO;
import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;
import jp.co.axa.apidemo.dto.ManualTaskDetailsDTO;
import jp.co.axa.apidemo.entities.ChildrenFeedback;
import jp.co.axa.apidemo.events.ManualTaskCompletedEvent;
import jp.co.axa.apidemo.exceptions.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.ChildrenFeedbackRepository;
import org.activiti.engine.task.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class ChildrenFeedbackServiceImpl implements ChildrenFeedbackService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    ModelMapper modelMapper;


    @Autowired
    ChristmasMessageService messageService;

    @Autowired
    ChildrenFeedbackRepository feedbackRepos;

    @Autowired
    private TaskService taskService;



    @Override
    public void saveChildrenFeedback(ChildrenFeedbackListDTO feedbackDTO) {
        feedbackDTO.getFeedback().stream().forEach(item -> {
            ChildrenFeedback feedbackEntity = modelMapper.map(item, ChildrenFeedback.class);
            feedbackEntity.setOriginalChristmasMessage(messageService.getChristmasMessage(item.getOriginalChristmasMessageID()));
            feedbackRepos.saveAndFlush(feedbackEntity);
        });
    }

    @Override
    public ChildrenFeedbackListDTO getChildrenFeedbackForMessageID(Long messageID) {
        List<ChildrenFeedback> entities = feedbackRepos.findByOriginalChristmasMessage(messageService.getChristmasMessage(messageID));
        ChildrenFeedbackListDTO returnValue = new ChildrenFeedbackListDTO();
        returnValue.setFeedback(new ArrayList<ChildrenFeedbackDTO>());
        entities.forEach(entry -> {returnValue.getFeedback().add(modelMapper.map(entry, ChildrenFeedbackDTO.class));});


        return returnValue;
    }

    @Override
    public void saveChildrenFeedbackWithTask(ChildrenFeedbackListDTO feedbackDTO, Long messageID) {
        Task task = taskService.getTaskForTypeAndMessageID(messageID,"Enter Feedback Manually");

        boolean feedbackFound = (feedbackDTO != null && feedbackDTO.getFeedback() != null && feedbackDTO.getFeedback().size() > 0);

        if (feedbackFound) {
            for (ChildrenFeedbackDTO childrenFeedbackDTO : feedbackDTO.getFeedback()) {
                childrenFeedbackDTO.setOriginalChristmasMessageID(messageID);
            }
            logger.info("No Feedback given, only close task");
            saveChildrenFeedback(feedbackDTO);
        }
        Map<String, Object> taskVariables = new HashMap<>();
        taskVariables.put("manualFeedbackFound", feedbackFound);

        taskService.completeTask(task.getId(),messageID,taskVariables);
    }
}
