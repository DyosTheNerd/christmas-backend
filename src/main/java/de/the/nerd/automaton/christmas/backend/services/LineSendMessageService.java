package de.the.nerd.automaton.christmas.backend.services;

import de.the.nerd.automaton.christmas.backend.dto.LineTextResponseMessageDTO;

public interface LineSendMessageService {


    void sendLineMessage(LineTextResponseMessageDTO message);
}
