package jp.co.axa.apidemo.services;


import javafx.concurrent.Task;
import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;

import jp.co.axa.apidemo.enums.TaskStatus;
import jp.co.axa.apidemo.enums.TaskType;
import jp.co.axa.apidemo.exceptions.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.ChristmasMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ChristmasMessageServiceImpl implements ChristmasMessageService{

    @Autowired
    ChristmasMessageRepository christmasMessageRepository;

    @Autowired
    ElvenTaskService elvenTaskService;

    @Autowired
    ModelMapper modelMapper;


    public Long saveChristmasMessage(ChristmasMessageDTO chrMessage){

        ChristmasMessage incomingMessage = modelMapper.map(chrMessage,ChristmasMessage.class);

        incomingMessage.setState(TaskStatus.OPEN);

        ChristmasMessage persistedObject = christmasMessageRepository.saveAndFlush(incomingMessage);

        elvenTaskService.createElvenTask(TaskType.READING,persistedObject.getId());

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
