package jp.co.axa.apidemo.entities;

import jp.co.axa.apidemo.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class WishList {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="CHRISTMAS_MESSAGE")
    // each wishlist has an original message it was created of.
    private ChristmasMessage originalChristmasMessage;


    @Getter
    @Setter
    @Column(name="ELVEN_TASK_ID")
    private ElvenTask approvalRequest;
}
