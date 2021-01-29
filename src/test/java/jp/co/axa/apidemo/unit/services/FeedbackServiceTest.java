package jp.co.axa.apidemo.unit.services;

import jp.co.axa.apidemo.dto.ChildrenFeedbackDTO;
import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;
import jp.co.axa.apidemo.entities.ChildrenFeedback;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.repositories.ChildrenFeedbackRepository;
import jp.co.axa.apidemo.services.ChildrenFeedbackServiceImpl;
import jp.co.axa.apidemo.services.ChristmasMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceTest {

    @InjectMocks
    ChildrenFeedbackServiceImpl testObject;

    @Mock
    ChildrenFeedbackRepository fbRepo;

    @Mock
    ChristmasMessageService msgService;

    @Mock
    ModelMapper modelMapper;

    private Long testMessageID = 2l;


    /**
     * this tests that we use the mapper and message service
     */
    @Test
    public void testMappingOnGetAndReuseService(){

        List<ChildrenFeedback> entityList = new ArrayList<>();

        // add three entries for assert later
        entityList.add(new ChildrenFeedback());
        entityList.add(new ChildrenFeedback());
        entityList.add(new ChildrenFeedback());

        when(fbRepo.findByOriginalChristmasMessage(any())).thenReturn(entityList);
        ChildrenFeedbackDTO expectedObject = new ChildrenFeedbackDTO();
        when(modelMapper.map(any(),eq(ChildrenFeedbackDTO.class))).thenReturn(expectedObject);

        // test call
        ChildrenFeedbackListDTO returnedObject = testObject.getChildrenFeedbackForMessageID(testMessageID);

        // verification: use actual message
        verify(msgService).getChristmasMessage(testMessageID);

        assertEquals("Entries from mapper are used", expectedObject, returnedObject.getFeedback().get(0));

        assertEquals("Three entities convert to three returned values",3, returnedObject.getFeedback().size());
    }



}
