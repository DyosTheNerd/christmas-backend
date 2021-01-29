package jp.co.axa.apidemo.listeners;

import jp.co.axa.apidemo.events.ChristmasMessageCreatedEvent;
import jp.co.axa.apidemo.services.ChristmasMessageAnalysisService;
import jp.co.axa.apidemo.util.LogUtil;
import jp.co.axa.apidemo.util.SecurityUtil;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.ProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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

