package jp.co.axa.apidemo.unit.dto;

import jp.co.axa.apidemo.dto.ChildrenFeedbackDTO;
import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;
import jp.co.axa.apidemo.entities.ChildrenFeedback;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class FeedbackDTOTest {



    @InjectMocks
    ModelMapper mapper;

    @Test
    public void testMappingFeedbackListToDTO(){


        ChildrenFeedback childfeedback1 = new ChildrenFeedback();
        ChristmasMessage chmsg = new ChristmasMessage();
        chmsg.setId(1l);

        childfeedback1.setOriginalChristmasMessage(chmsg);
        childfeedback1.setTextExtract("sometext");
        childfeedback1.setId(2l);
        childfeedback1.setSentiment("1");




        ChildrenFeedbackDTO returnValue = mapper.map(childfeedback1, ChildrenFeedbackDTO.class);


        assertEquals((Long) 1l, (Long) returnValue.getOriginalChristmasMessageID());

    }

}
