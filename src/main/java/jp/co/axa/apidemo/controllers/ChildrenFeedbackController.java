package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;
import jp.co.axa.apidemo.services.ChildrenFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class ChildrenFeedbackController {

    @Autowired
    ChildrenFeedbackService feedbackService;

    @PostMapping("/christmasMessages/{christmasMessageID}/feedback/")
    public void saveChristmasMessage(ChildrenFeedbackListDTO feedbackListDTO){

        feedbackService.saveChildrenFeedback(feedbackListDTO);
    }

}
