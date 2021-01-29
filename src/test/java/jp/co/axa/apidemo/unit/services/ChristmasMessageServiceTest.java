package jp.co.axa.apidemo.unit.services;

import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.enums.TaskType;
import jp.co.axa.apidemo.events.ChristmasMessageCreatedEvent;
import jp.co.axa.apidemo.repositories.ChristmasMessageRepository;
import jp.co.axa.apidemo.services.ChristmasMessageServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChristmasMessageServiceTest {

    @InjectMocks
    ChristmasMessageServiceImpl testObject;

    @Mock
    ChristmasMessageRepository msgRepo;


    @Mock
    ModelMapper modelMapper;

    @Mock
    ApplicationEventPublisher publisher;

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

        // an event should be thrown
        verify(publisher).publishEvent(any(ChristmasMessageCreatedEvent.class));

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
