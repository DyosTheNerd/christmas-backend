package jp.co.axa.apidemo.listeners;


import jp.co.axa.apidemo.enums.Webhook;
import jp.co.axa.apidemo.events.ElvenTaskCreatedEvent;
import jp.co.axa.apidemo.services.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * This class listens to task events to call webhooks on created events
 */

@Component
@Async
public class ElvenTaskCreatedEventListener implements ApplicationListener<ElvenTaskCreatedEvent> {

    @Autowired
    WebhookService whService;

    @Override
    public void onApplicationEvent(ElvenTaskCreatedEvent elvenTaskCreatedEvent) {
        whService.notifyWebhook(Webhook.TASKCREATED,elvenTaskCreatedEvent.getDetails());
    }
}
