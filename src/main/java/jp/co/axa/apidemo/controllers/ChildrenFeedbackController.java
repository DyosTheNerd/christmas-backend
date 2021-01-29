package jp.co.axa.apidemo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jp.co.axa.apidemo.dto.ChildrenFeedbackListDTO;
import jp.co.axa.apidemo.services.ChildrenFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * This controller works with the feedback business object, to provide it to consumers.
 */
@RestController
@RequestMapping("/api/v1")
public class ChildrenFeedbackController {

    @Autowired
    ChildrenFeedbackService feedbackService;

    @Operation(summary = "Create feedback for a message.", description = "Create feedback for a message with given id. " +
            "If no feedback or wishlist is found, the system will request via email for feedback to be amended manually. " +
            "If and only if this happened, calling this method will succeed and amend the feedback.")
    @PostMapping("/christmasMessages/{messageID}/feedback/")
    public void saveExternalChristmasMessageFeedback(@PathVariable(name="messageID")Long messageID, ChildrenFeedbackListDTO feedbackListDTO){

        feedbackService.saveChildrenFeedbackWithTask(feedbackListDTO,messageID);
    }

    @Operation(summary = "Retrieve the feedback for an existing message.", description = "If the message exists, " +
            "this method will at least return an empty array of feedback items")
    @GetMapping("/christmasMessages/{messageID}/feedback/")
    public ChildrenFeedbackListDTO getChristmasMessageFeedback(@PathVariable(name="messageID")Long messageID){

        return feedbackService.getChildrenFeedbackForMessageID(messageID);
    }


}
