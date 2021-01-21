package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;
import jp.co.axa.apidemo.entities.ChildrenFeedback;
import jp.co.axa.apidemo.exceptions.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.ChildrenFeedbackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildrenFeedbackServiceImpl implements ChildrenFeedbackService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ChristmasMessageService messageService;

    @Autowired
    ChildrenFeedbackRepository feedbackRepos;

    @Override
    public void saveChildrenFeedback(ChildrenFeedbackListDTO feedbackDTO) {



        feedbackDTO.getFeedback().stream().forEach(item -> {
            ChildrenFeedback feedbackEntity = modelMapper.map(item, ChildrenFeedback.class);
            feedbackEntity.setOriginalChristmasMessage(messageService.getChristmasMessage(item.getOriginalChristmasMessageID()));
            feedbackRepos.saveAndFlush(feedbackEntity);
        });
    }
}
