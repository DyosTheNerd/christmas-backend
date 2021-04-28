package de.the.nerd.automaton.christmas.backend.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.the.nerd.automaton.christmas.backend.dto.ChildrenFeedbackListDTO;
import de.the.nerd.automaton.christmas.backend.dto.LineIncomingMessageDto;
import de.the.nerd.automaton.christmas.backend.services.ChildrenFeedbackService;
import de.the.nerd.automaton.christmas.backend.services.LineReceiveMessageService;
import de.the.nerd.automaton.christmas.backend.services.LineRequestAuthenticationService;
import de.the.nerd.automaton.christmas.backend.services.LineRequestAuthenticationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * This controller works with the feedback business object, to provide it to consumers.
 */
@RestController
@RequestMapping("/api/v1")
public class LineAPIController {


    static Logger logger = LoggerFactory.getLogger(LineAPIController.class);

    @Autowired
    LineRequestAuthenticationService feedbackService;

    @Autowired
    LineReceiveMessageService receiveMessageService;

    @Autowired
    ObjectMapper objectMapper;

    @Operation(summary = "Create feedback for a message.", description = "Create feedback for a message with given id. " +
            "If no feedback or wishlist is found, the system will request via email for feedback to be amended manually. " +
            "If and only if this happened, calling this method will succeed and amend the feedback.")
    @PostMapping("/chat/line")
    public  ResponseEntity<String> receiveLineMessageCall(@RequestBody String request, @RequestHeader("x-line-signature") Map<String, String> headers){

        String authHeaderContent = feedbackService.getAuthHeaderContent(headers);

        boolean isValidRequest = feedbackService.validateLineRequest(request, authHeaderContent);

        logger.info(request);

        if (!isValidRequest){

            HttpHeaders responseHeaders = new HttpHeaders();

            ResponseEntity responseE = ResponseEntity.badRequest().body("");

            return responseE;
        }

        try {
            LineIncomingMessageDto deserializedObj = objectMapper.readValue(request, LineIncomingMessageDto.class);
            receiveMessageService.receiveMessage(deserializedObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            HttpHeaders responseHeaders = new HttpHeaders();

            ResponseEntity responseE = ResponseEntity.badRequest().body("");

            return responseE;
        }



        HttpHeaders responseHeaders = new HttpHeaders();

        ResponseEntity<String> responseE = ResponseEntity.ok("");

        return responseE;





    }



}
