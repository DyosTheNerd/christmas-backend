package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.WebhookObject;
import jp.co.axa.apidemo.entities.WebhookSubscription;
import jp.co.axa.apidemo.enums.SubscriptionStatus;
import jp.co.axa.apidemo.enums.Webhook;
import jp.co.axa.apidemo.repositories.WebhookSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebhookServiceImpl implements WebhookService{

    @Autowired
    WebhookSubscriptionRepository repository;

    @Override
    public void notifyWebhook(Webhook hook, WebhookObject object) {
        List<WebhookSubscription> activeSubs = repository.findByWebhookAndStatus(hook, SubscriptionStatus.ACTIVE);

        // TODO implement async call
        activeSubs.forEach(sub -> {

            // do post call of object.asJSON();

            // if fails try again after configurable time
            // if fails again, update webhook to INACTIVE
        });
    }

    @Override
    public void subscribeWebhook(Webhook hook, String url) {
        WebhookSubscription subscription = new WebhookSubscription();
        subscription.setUrl(url);
        subscription.setWebhook(hook);

        repository.save(subscription);
    }



}
