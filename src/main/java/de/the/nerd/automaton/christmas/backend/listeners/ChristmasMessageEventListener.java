package de.the.nerd.automaton.christmas.backend.listeners;

import de.the.nerd.automaton.christmas.backend.events.ChristmasMessageCreatedEvent;
import de.the.nerd.automaton.christmas.backend.services.ChristmasMessageAnalysisService;
import de.the.nerd.automaton.christmas.backend.util.SecurityUtil;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * This Event is fired when the automated analysis is done.
 */
@Component
public class ChristmasMessageEventListener  implements ApplicationListener<ChristmasMessageCreatedEvent> {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    ChristmasMessageAnalysisService analysisService;

    @Autowired
    private SecurityUtil securityUtil;


    @Override
    public void onApplicationEvent(ChristmasMessageCreatedEvent christmasMessageCreatedEvent) {

        securityUtil.logInAs("system");


        processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("christmasProcess_1")
                .withName("Processing of Christmas Message: " + christmasMessageCreatedEvent.getDetails().getId())
                .withVariable("id", christmasMessageCreatedEvent.getDetails().getId())
                .build());
    }
}

