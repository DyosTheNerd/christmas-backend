package de.the.nerd.automaton.christmas.backend.services;

import de.the.nerd.automaton.christmas.backend.dto.LineIncomingMessageDto;


public interface LineReceiveMessageService {

     void receiveMessage(LineIncomingMessageDto messageData);


}

