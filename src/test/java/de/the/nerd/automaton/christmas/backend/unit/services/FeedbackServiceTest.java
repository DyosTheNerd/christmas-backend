package de.the.nerd.automaton.christmas.backend.unit.services;

import de.the.nerd.automaton.christmas.backend.dto.ChildrenFeedbackDTO;
import de.the.nerd.automaton.christmas.backend.dto.ChildrenFeedbackListDTO;
import de.the.nerd.automaton.christmas.backend.entities.ChildrenFeedback;
import de.the.nerd.automaton.christmas.backend.repositories.ChildrenFeedbackRepository;
import de.the.nerd.automaton.christmas.backend.services.ChildrenFeedbackServiceImpl;
import de.the.nerd.automaton.christmas.backend.services.ChristmasMessageService;
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
