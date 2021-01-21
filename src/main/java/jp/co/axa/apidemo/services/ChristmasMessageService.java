package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;


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
