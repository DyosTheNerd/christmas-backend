package de.the.nerd.automaton.christmas.backend.listeners;


import de.the.nerd.automaton.christmas.backend.events.LineMessageReceivedEvent;
import de.the.nerd.automaton.christmas.backend.services.ChristmasMessageAnalysisService;
import de.the.nerd.automaton.christmas.backend.util.SecurityUtil;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * This Event is fired when a new message enters the system
 */
@Component
public class LineMessageReceivedEventListener  implements ApplicationListener<LineMessageReceivedEvent> {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    ChristmasMessageAnalysisService analysisService;

    @Autowired
    private SecurityUtil securityUtil;


    @Override
    public void onApplicationEvent(LineMessageReceivedEvent lineMessageReceivedEvent) {

        securityUtil.logInAs("system");


        processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("lineChatProcess_1")
                .withName("Processing of Christmas Message: " + lineMessageReceivedEvent.getDetails().message.text)
                .withVariable("msg", lineMessageReceivedEvent.getDetails())
                .build());
    }
}


