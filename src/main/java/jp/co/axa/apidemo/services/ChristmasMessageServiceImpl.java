package jp.co.axa.apidemo.services;


import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.events.ChristmasMessageCreatedEvent;
import jp.co.axa.apidemo.exceptions.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.ChristmasMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChristmasMessageServiceImpl implements ChristmasMessageService{

    @Autowired
    ChristmasMessageRepository christmasMessageRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;


    public Long saveChristmasMessage(ChristmasMessageDTO chrMessage){

        ChristmasMessage incomingMessage = modelMapper.map(chrMessage,ChristmasMessage.class);

        ChristmasMessage persistedObject = christmasMessageRepository.saveAndFlush(incomingMessage);

        chrMessage.setId(persistedObject.getId());

        ChristmasMessageCreatedEvent event = new ChristmasMessageCreatedEvent(this, chrMessage);



        applicationEventPublisher.publishEvent(event);


        return persistedObject.getId();
    }

    @Override
    public ChristmasMessage getChristmasMessage(Long id) {
        Optional<ChristmasMessage> optional = christmasMessageRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();

        }
        throw new ResourceNotFoundException(id,"ChristmasMessage");
    }

}
