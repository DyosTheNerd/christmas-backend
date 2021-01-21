package jp.co.axa.apidemo.unit.services;

import static org.mockito.Mockito.*;

import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.enums.TaskType;
import jp.co.axa.apidemo.repositories.ChristmasMessageRepository;
import jp.co.axa.apidemo.services.ChristmasMessageServiceImpl;
import jp.co.axa.apidemo.services.ElvenTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class ChristmasMessageServiceTest {

    @InjectMocks
    ChristmasMessageServiceImpl testObject;

    @Mock
    ChristmasMessageRepository msgRepo;

    @Mock
    ElvenTaskService elvenTaskService;

    @Mock
    ModelMapper modelMapper;

    /**
     * Testing that the service triggers the task service to create a new task
     */
    @Test
    public void createMessageCreatesTask(){
        ChristmasMessageDTO message = setupMessage();
        ChristmasMessage mappedObject = new ChristmasMessage();
        mappedObject.setId(33l);
        when(modelMapper.map(message,ChristmasMessage.class)).thenReturn(mappedObject);

        when(msgRepo.saveAndFlush(any())).thenReturn(mappedObject);
        testObject.saveChristmasMessage(message);

        verify(elvenTaskService).createElvenTask(TaskType.READING,33l);
    }


    /**
     * Testing that service actually forwards a message object into the dao layer
     */
    @Test
    public void createMessageStoresMessage(){
        ChristmasMessageDTO  message = setupMessage();
        ChristmasMessage mappedObject = new ChristmasMessage();
        when(modelMapper.map(message,ChristmasMessage.class)).thenReturn(mappedObject);

        when(msgRepo.saveAndFlush(any())).thenReturn(mappedObject);

        testObject.saveChristmasMessage(message);

        verify(msgRepo).saveAndFlush(mappedObject);
    }

    /**
     * @return simple message object for testing purposes
     */
    private ChristmasMessageDTO setupMessage(){
        ChristmasMessageDTO message = new ChristmasMessageDTO();
        message.setExtChildID("123");
        message.setExternalId("ext");
        message.setId(123l);
        message.setText("Santa, bring me stuff!");
        return message;
    }

}
