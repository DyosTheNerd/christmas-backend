package de.the.nerd.automaton.christmas.backend.services;

import de.the.nerd.automaton.christmas.backend.dto.LineIncomingEventDto;
import de.the.nerd.automaton.christmas.backend.dto.LineTextResponseAnswerDTO;
import de.the.nerd.automaton.christmas.backend.dto.LineTextResponseDTO;
import de.the.nerd.automaton.christmas.backend.dto.LineTextResponseMessageDTO;
import org.activiti.api.process.runtime.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


@Service
public class LineSendMessageServiceImpl implements LineSendMessageService{

    @Autowired
    public RestTemplate restTemplate;

    public static Logger logger = LoggerFactory.getLogger(LineReceiveMessageServiceImpl.class);

    @Value("${line.bot.api.bearer.token}")
    public String botApiBearerToken;


    @Bean
    public Connector lineChatReceivedMessageConnector() {
        return integrationContext -> {
            Map<String, Object> inBoundVariables = integrationContext.getInBoundVariables();
            LineIncomingEventDto messageDtls = (LineIncomingEventDto) inBoundVariables.get("msg");

            LineTextResponseDTO responseDTO = new LineTextResponseDTO();

            responseDTO.replyToken = messageDtls.replyToken;

            logger.info("replying to " + messageDtls.message.text);
            logger.info("on token " + messageDtls.replyToken);

            LineTextResponseMessageDTO sampleResponse = new LineTextResponseMessageDTO();

            sampleResponse.text = "Thanks for getting in contact, this is a bot response.";
            sampleResponse.type = "text";

            responseDTO.messages = new ArrayList<>();

            responseDTO.messages.add(sampleResponse);

            sendLineMessage(responseDTO);

            return integrationContext;
        };
    }

    public void sendLineMessage(LineTextResponseDTO message){
        String url = "https://api.line.me/v2/bot/message/reply";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        headers.setBearerAuth(botApiBearerToken);

        // create a post object


        // build the request
        HttpEntity<LineTextResponseDTO> entity = new HttpEntity<>(message, headers);

        logger.info(entity.toString());
        logger.info(message.replyToken);
        // send POST request
        LineTextResponseAnswerDTO lineResponse  = restTemplate.postForObject(url, entity,LineTextResponseAnswerDTO.class);

        logger.info(lineResponse.toString());
    }

}
