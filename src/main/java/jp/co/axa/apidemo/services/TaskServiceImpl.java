package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.ManualTaskDetailsDTO;
import jp.co.axa.apidemo.events.ManualTaskCompletedEvent;
import jp.co.axa.apidemo.exceptions.ResourceNotFoundException;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private org.activiti.engine.TaskService taskService;


    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Task getTaskForTypeAndMessageID(Long messageID, String taskName) {
        List<Task> openTasks = taskService.createTaskQuery().processDefinitionKey("christmasProcess_1").processVariableValueEquals("id", messageID).taskName(taskName).active().list();
        if (openTasks.size() != 1) {
            logger.info("No Task found, returning");
            throw new ResourceNotFoundException(messageID, "AnalysisTask - Resource Manipulation not allowed without Task");
        }
        return openTasks.get(0);
    }

    @Override
    public void completeTask(String id, Long messageID, Map<String, Object> taskVariables) {
        ManualTaskDetailsDTO detailsDTO = new ManualTaskDetailsDTO();
        detailsDTO.setMessageID(messageID);
        detailsDTO.setResultParams(taskVariables);
        detailsDTO.setTaskID(id);
        ManualTaskCompletedEvent event = new ManualTaskCompletedEvent(this, detailsDTO);
        applicationEventPublisher.publishEvent(event);
    }
}
