package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.ChristmasMessageService;
import jp.co.axa.apidemo.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ChristmasMessageController {

    @Autowired
    ChristmasMessageService christmasMessageService;

    @GetMapping("/christmasMessages/{messageID}")
    public ChristmasMessage getChristmasMessage(@PathVariable(name="messageID")Long messageID) {
        return christmasMessageService.getChristmasMessage(messageID);
    }

    @PostMapping("/christmasMessages")
    public Long saveChristmasMessage(ChristmasMessageDTO christmasMessage){
        return christmasMessageService.saveChristmasMessage(christmasMessage);
    }

}
