package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.ElvenTaskDTO;
import jp.co.axa.apidemo.entities.ElvenTask;
import jp.co.axa.apidemo.enums.TaskStatus;
import jp.co.axa.apidemo.enums.TaskType;
import jp.co.axa.apidemo.events.ElvenTaskCreatedEvent;
import jp.co.axa.apidemo.repositories.ElvenTaskRepository;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


/**
 * This class handles Simple Elven Task Maintenance.
 */
@Service
public class ElvenTaskServiceImpl implements ElvenTaskService {


    @Autowired
    private ElvenTaskRepository elvenTaskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Setter
    private ApplicationEventPublisher applicationEventPublisher;

    public void createElvenTask(TaskType type, Long linkedID){
        ElvenTask task = new ElvenTask();
        task.setLinkedObjectID(linkedID);
        task.setTaskType(type);
        task.setTaskStatus(TaskStatus.OPEN);
        elvenTaskRepository.save(task);


        ElvenTaskDTO elvenTaskDTO = modelMapper.map(task,ElvenTaskDTO.class);


        ElvenTaskCreatedEvent event = new ElvenTaskCreatedEvent(this, elvenTaskDTO);


        applicationEventPublisher.publishEvent(event);

    }

}
