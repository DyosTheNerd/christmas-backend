package de.the.nerd.automaton.christmas.backend.unit.controllers;


import de.the.nerd.automaton.christmas.backend.controllers.LineAPIController;
import de.the.nerd.automaton.christmas.backend.repositories.ChristmasMessageRepository;
import de.the.nerd.automaton.christmas.backend.services.LineRequestAuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LineAPIControllerTest
{
    @InjectMocks
    LineAPIController controller;


    @Mock
    LineRequestAuthenticationService mockService;

    @Test
    public void testAuthenticatedResponseOK(){

        String mockedHeaderContent = "abc";

        when(mockService.getAuthHeaderContent(any())).thenReturn(mockedHeaderContent);

        when(mockService.validateLineRequest(any(),any())).thenReturn(true);

        Map<String, String> headermap = new HashMap<>();

        Map<String, String> requestParams = new HashMap<>();
        ResponseEntity<String> respnse = controller.saveExternalChristmasMessageFeedback("", headermap, requestParams);

        assertEquals(respnse.getStatusCode(), HttpStatus.resolve(200));

    }

    @Test
    public void testAuthenticatedResponseFalse(){

        String mockedHeaderContent = "abc";

        when(mockService.getAuthHeaderContent(any())).thenReturn(mockedHeaderContent);

        when(mockService.validateLineRequest(any(),any())).thenReturn(false);

        Map<String, String> headermap = new HashMap<>();

        Map<String, String> requestParams = new HashMap<>();
        ResponseEntity<String> respnse = controller.saveExternalChristmasMessageFeedback("", headermap, requestParams);

        assertEquals(respnse.getStatusCode(), HttpStatus.resolve(400));

    }


}
