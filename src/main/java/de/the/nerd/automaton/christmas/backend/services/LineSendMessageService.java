package de.the.nerd.automaton.christmas.backend.services;

import de.the.nerd.automaton.christmas.backend.dto.LineTextResponseDTO;

public interface LineSendMessageService {


    void sendLineMessage(LineTextResponseDTO message);
}
