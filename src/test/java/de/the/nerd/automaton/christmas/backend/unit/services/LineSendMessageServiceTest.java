package de.the.nerd.automaton.christmas.backend.unit.services;


import de.the.nerd.automaton.christmas.backend.dto.*;
import de.the.nerd.automaton.christmas.backend.services.LineReceiveMessageServiceImpl;
import de.the.nerd.automaton.christmas.backend.services.LineRequestAuthenticationServiceImpl;
import de.the.nerd.automaton.christmas.backend.services.LineSendMessageServiceImpl;
import org.activiti.api.process.model.IntegrationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LineSendMessageServiceTest {


    @Mock
    LineSendMessageServiceImpl testableService;

    @Mock
    public RestTemplate restTemplate;

    @Test
    public void testConnectorToMessageSend(){
        when(testableService.lineChatReceivedMessageConnector()).thenCallRealMethod();
        IntegrationContext simpleContext = mock(IntegrationContext.class);
        Map<String, Object> integrationVariables = new HashMap<>();
        LineIncomingEventDto evtDtls = new LineIncomingEventDto();
        evtDtls.replyToken = "abc";
        evtDtls.type = "message";
        evtDtls.message = new LineIncomingMessageContentDto();
        evtDtls.message.text = "msg text";
        evtDtls.message.id = "123";
        evtDtls.message.type = "text";
        integrationVariables.put("msg",evtDtls);
        when(simpleContext.getInBoundVariables()).thenReturn(integrationVariables);
        testableService.lineChatReceivedMessageConnector().apply(simpleContext);

        verify(testableService).sendLineMessage(any());

    }

    @Test
    public void testRestTemplateCall(){
        LineTextResponseDTO message = new LineTextResponseDTO();

        doCallRealMethod().when(testableService).sendLineMessage(any());
        testableService.restTemplate = restTemplate;
        testableService.logger = LoggerFactory.getLogger(LineReceiveMessageServiceImpl.class);

        LineTextResponseAnswerDTO testResponse = new LineTextResponseAnswerDTO();

        when(restTemplate.postForObject(eq("https://api.line.me/v2/bot/message/reply"),any(HttpEntity.class),eq(LineTextResponseAnswerDTO.class))).thenReturn(testResponse);

        testableService.sendLineMessage(message);
    }




}
