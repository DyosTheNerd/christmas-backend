package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.ChristmasMessage;
import org.springframework.stereotype.Service;


/**
 * This class maintains the incoming christmas messages
 */
public interface ChristmasMessageService {

    /**
     * This method creates a new message object and starts subsequent workflows
     * @param chrMessage
     */
    void saveChristmasMessage(ChristmasMessage chrMessage);
}
