package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;
import jp.co.axa.apidemo.services.ChildrenFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ChildrenFeedbackController {

    @Autowired
    ChildrenFeedbackService feedbackService;

    @PostMapping("/christmasMessages/{messageID}/feedback/")
    public void saveExternalChristmasMessageFeedback(@PathVariable(name="messageID")Long messageID, ChildrenFeedbackListDTO feedbackListDTO){

        feedbackService.saveChildrenFeedbackWithTask(feedbackListDTO,messageID);
    }

    @GetMapping("/christmasMessages/{messageID}/feedback/")
    public ChildrenFeedbackListDTO getChristmasMessageFeedback(@PathVariable(name="messageID")Long messageID){

        return feedbackService.getChildrenFeedbackForMessageID(messageID);
    }


}
