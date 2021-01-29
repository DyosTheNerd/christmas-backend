package jp.co.axa.apidemo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.services.ChristmasMessageAnalysisService;
import jp.co.axa.apidemo.services.ChristmasMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * This controller is the main entry point into the start of the application processes.
 */
@RestController
@RequestMapping("/api/v1")
public class ChristmasMessageController {

    @Autowired
    ChristmasMessageService christmasMessageService;

    @Autowired
    ChristmasMessageAnalysisService christmasMessageAnalysisService;


    @Operation(summary = "Retrieve the an existing message.", description = "If the message exists, " +
            "this method will return it. If not, a ResourceNotFoundException will be thrown")
    @GetMapping("/christmasMessages/{messageID}")
    public ChristmasMessage getChristmasMessage(@PathVariable(name="messageID")Long messageID) {

        return christmasMessageService.getChristmasMessage(messageID);
    }

    @Operation(summary = "Create a new message object.", description = "This creates a new message object and starts " +
            "the processing workflow. The external IDs are optional and not used in the system.")
    @PostMapping("/christmasMessages")
    public Long saveChristmasMessage(ChristmasMessageDTO christmasMessage){
        return christmasMessageService.saveChristmasMessage(christmasMessage);
    }

    @Operation(summary ="Retrieve the automated sentence analysis for an existing message.", description = "This retrieves" +
            "the automated sentence category analysis. its a good starting point for manual tasks regarding feedback" +
            "and wish list creation.")
    @GetMapping("/christmasMessages/{messageID}/analysis")
    public List<String> getBasicMessageAnalysis(@PathVariable(name="messageID")Long messageID) {
        return christmasMessageAnalysisService.getBasicMessageAnalysis(messageID);
    }


}
