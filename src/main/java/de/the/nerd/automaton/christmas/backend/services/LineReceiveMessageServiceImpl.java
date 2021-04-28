package de.the.nerd.automaton.christmas.backend.services;


import de.the.nerd.automaton.christmas.backend.dto.*;
import de.the.nerd.automaton.christmas.backend.entities.ChristmasMessage;
import de.the.nerd.automaton.christmas.backend.events.ChristmasMessageCreatedEvent;
import de.the.nerd.automaton.christmas.backend.events.LineMessageReceivedEvent;
import org.activiti.api.process.runtime.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LineReceiveMessageServiceImpl implements LineReceiveMessageService{

    static Logger logger = LoggerFactory.getLogger(LineReceiveMessageServiceImpl.class);

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void receiveMessage(LineIncomingMessageDto messageData) {
        if (messageData.events.size() > 0){
            messageData.events.forEach(evt -> {
                logger.info(evt.message.text);
                triggerResponseToMessage(evt);
            });



        }

    }


    public void triggerResponseToMessage(LineIncomingEventDto evtMessage){

        LineMessageReceivedEvent event = new LineMessageReceivedEvent(this, evtMessage);



        applicationEventPublisher.publishEvent(event);

    }


}
