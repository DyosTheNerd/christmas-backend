package jp.co.axa.apidemo.unit.services;

import static org.mockito.Mockito.*;

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

@RunWith(MockitoJUnitRunner.class)
public class ChristmasMessageServiceTest {

    @InjectMocks
    ChristmasMessageServiceImpl testObject = new ChristmasMessageServiceImpl();

    @Mock
    ChristmasMessageRepository msgRepo;

    @Mock
    ElvenTaskService elvenTaskService;

    /**
     * Testing that the service triggers the task service to create a new task
     */
    @Test
    public void createMessageCreatesTask(){
        ChristmasMessage  message = setupMessage();
        testObject.saveChristmasMessage(message);

        verify(elvenTaskService).createElvenTask(TaskType.READING,123l);
    }


    /**
     * Testing that service actually forwards a message object into the dao layer
     */
    @Test
    public void createMessageStoresMessage(){
        ChristmasMessage  message = setupMessage();
        testObject.saveChristmasMessage(message);

        verify(msgRepo).save(message);
    }

    /**
     * @return simple message object for testing purposes
     */
    private ChristmasMessage setupMessage(){
        ChristmasMessage message = new ChristmasMessage();
        message.setExtChildID("123");
        message.setExternalId("ext");
        message.setId(123l);
        message.setText("Santa, bring me stuff!");
        return message;
    }

}
