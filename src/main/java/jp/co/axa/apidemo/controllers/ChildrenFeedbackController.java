package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;
import jp.co.axa.apidemo.services.ChildrenFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ChildrenFeedbackController {

    @Autowired
    ChildrenFeedbackService feedbackService;

    @PostMapping("/christmasMessages/{christmasMessageID}/feedback/")
    public void saveChristmasMessage(ChildrenFeedbackListDTO feedbackListDTO){

        feedbackService.saveChildrenFeedback(feedbackListDTO);
    }

}
