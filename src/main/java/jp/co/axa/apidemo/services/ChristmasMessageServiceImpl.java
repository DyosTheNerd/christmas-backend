package jp.co.axa.apidemo.services;


import jp.co.axa.apidemo.entities.ChristmasMessage;

import jp.co.axa.apidemo.enums.TaskType;
import jp.co.axa.apidemo.repositories.ChristmasMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChristmasMessageServiceImpl implements ChristmasMessageService{

    @Autowired
    ChristmasMessageRepository christmasMessageRepository;

    @Autowired
    ElvenTaskService elvenTaskService;

    public void saveChristmasMessage(ChristmasMessage chrMessage){
        christmasMessageRepository.save(chrMessage);

        elvenTaskService.createElvenTask(TaskType.READING,chrMessage.getId());



    }

}
