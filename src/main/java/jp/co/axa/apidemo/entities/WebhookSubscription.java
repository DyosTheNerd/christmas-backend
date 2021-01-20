package jp.co.axa.apidemo.entities;


import jp.co.axa.apidemo.enums.SubscriptionStatus;
import jp.co.axa.apidemo.enums.Webhook;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class WebhookSubscription {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name="WEBHOOK")
    private Webhook webhook;

    @Setter
    @Getter
    @Column(name="url")
    private String url;

    /** subscription details such as connection info of the subscriber go here.
     *
      */
    @Setter
    @Getter
    @Column(name="SUBSCRIPTIONDETAILS")
    private String subscriptionDetails;


    @Setter
    @Getter
    @Column(name="STATUS")
    private SubscriptionStatus status;

}
