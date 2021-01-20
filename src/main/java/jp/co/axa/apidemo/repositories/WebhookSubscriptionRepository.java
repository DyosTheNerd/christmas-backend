package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.WebhookSubscription;
import jp.co.axa.apidemo.enums.SubscriptionStatus;
import jp.co.axa.apidemo.enums.Webhook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebhookSubscriptionRepository extends JpaRepository<WebhookSubscription,Long> {

    List<WebhookSubscription> findByWebhookAndStatus(Webhook hook, SubscriptionStatus status);
}
