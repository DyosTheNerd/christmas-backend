package de.the.nerd.automaton.christmas.backend.services;

import de.the.nerd.automaton.christmas.backend.dto.ChristmasMessageDTO;
import de.the.nerd.automaton.christmas.backend.entities.ChristmasMessage;


/**
 * This class maintains the incoming christmas messages
 */
public interface ChristmasMessageService {

    /**
     * This Method creates a new message object and starts subsequent workflows
     * @param chrMessage
     */
    Long saveChristmasMessage(ChristmasMessageDTO chrMessage);

    /**
     * This Method retrieves a previously created message.
     */
    ChristmasMessage getChristmasMessage(Long id);

}
