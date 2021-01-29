package jp.co.axa.apidemo.listeners;

import jp.co.axa.apidemo.events.ChristmasMessageCreatedEvent;
import jp.co.axa.apidemo.events.ManualTaskCompletedEvent;
import jp.co.axa.apidemo.services.ChristmasMessageAnalysisService;
import jp.co.axa.apidemo.util.SecurityUtil;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class ManualTaskCompletedEventListener implements ApplicationListener<ManualTaskCompletedEvent> {

        @Autowired
        private TaskService taskService;

        @Autowired
        ChristmasMessageAnalysisService analysisService;

        @Autowired
        private SecurityUtil securityUtil;

        Logger logger = LoggerFactory.getLogger(this.getClass().getName());

        @Async
        @Override
        public void onApplicationEvent(ManualTaskCompletedEvent event) {

            securityUtil.logInAs("system");

            logger.info("completing task " + event.getDetails().getTaskID() + " for message " + event.getDetails().getMessageID());
            taskService.complete(event.getDetails().getTaskID(),event.getDetails().getResultParams());
        }
}
