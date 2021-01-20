package jp.co.axa.apidemo.integration;

import jp.co.axa.apidemo.dto.ElvenTaskDTO;
import jp.co.axa.apidemo.enums.TaskType;
import jp.co.axa.apidemo.events.ElvenTaskCreatedEvent;
import jp.co.axa.apidemo.services.ElvenTaskService;
import jp.co.axa.apidemo.services.ElvenTaskServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElvenTaskServiceIntegrationTest {


    @Autowired
    ElvenTaskServiceImpl testFocusObject;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;


    @Test
    public void creationOfATask() {

        testFocusObject.setApplicationEventPublisher(applicationEventPublisher );

        testFocusObject.createElvenTask(TaskType.APPROVAL,new Long (1001l));

        ArgumentCaptor<ElvenTaskCreatedEvent> eventCaptor = ArgumentCaptor.forClass(ElvenTaskCreatedEvent.class);
        verify(applicationEventPublisher).publishEvent(eventCaptor.capture());

        ElvenTaskDTO dto = eventCaptor.getValue().getDetails();
        assertNotNull(dto);
        assertEquals("Task Type is part of event Object", TaskType.APPROVAL,dto.getTaskType());
        assertEquals("Linked ObjectID is used",new Long(1001l),dto.getLinkedObjectID());
        assertNotNull("ID given by other component",dto.getId());
        assertNotEquals(new Long(0l),dto.getId());

    }

}
