package de.the.nerd.automaton.christmas.backend.unit.services;

import de.the.nerd.automaton.christmas.backend.services.LineRequestAuthenticationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LineRequestAuthenticationServiceImplTest {


    @InjectMocks
    LineRequestAuthenticationServiceImpl testableService;

    @Test
    public void testAuthSignature(){

        testableService.botApiSecret = "ABC";
        boolean returnValue = testableService.validateLineRequest("testmsg","QrViOlWUH6ZYjP0fJZGhSMLa/qgM80EJs0AXAaVRzbU=");

        assertTrue(returnValue);

    }


    @Test
    public void testAuthSignatureWrong(){

        testableService.botApiSecret = "ABC";
        boolean returnValue = testableService.validateLineRequest("test","QrViOlWUH6ZYjP0fJZGhSMLa/qgM80EJs0AXAaVRzbU=");

        assertFalse(returnValue);

    }

    @Test
    public void testGetHeaderEmpty(){

        Map<String,String> headers = new HashMap<String, String>();

        headers.put("otherheader", "somepayload");


        String returnValue = testableService.getAuthHeaderContent(headers);

        assertEquals("",returnValue);
    }

    @Test
    public void testGetHeaderExists(){

        Map<String,String> headers = new HashMap<String, String>();

        headers.put("x-line-signature", "somepayload");


        String returnValue = testableService.getAuthHeaderContent(headers);

        assertEquals("somepayload",returnValue);
    }


    @Test
    public void testGetHeaderExistsCase(){

        Map<String,String> headers = new HashMap<String, String>();

        headers.put("X-liNe-sIgnature", "somepayload");


        String returnValue = testableService.getAuthHeaderContent(headers);

        assertEquals("somepayload",returnValue);
    }


}
