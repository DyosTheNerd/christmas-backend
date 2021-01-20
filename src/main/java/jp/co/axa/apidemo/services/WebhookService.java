package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.WebhookObject;
import jp.co.axa.apidemo.enums.Webhook;

public interface WebhookService {




    void notifyWebhook(Webhook hook, WebhookObject object);

    void subscribeWebhook(Webhook hook, String url);

}
