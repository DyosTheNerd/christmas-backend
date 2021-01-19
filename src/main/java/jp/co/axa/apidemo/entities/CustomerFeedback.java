package jp.co.axa.apidemo.entities;

import jp.co.axa.apidemo.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CustomerFeedback {



    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="CHRISTMAS_MESSAGE")
    private ChristmasMessage originalChristmasMessage;

    @Getter
    @Setter
    @Column(name="TEXT_EXTRACT")
    // a quote of text to qualify the feedback
    private String textExtract;

    @Getter
    @Setter
    @Column(name="SUBJECT")
    // What was the feedback about
    private String subject;

    @Getter
    @Setter
    @Column(name="SCALE_VALUE")
    // How apparent was the emotion?
    private String scaleValue;

    @Getter
    @Setter
    @Column(name="SENTIMENT")
    // What kind of emotion was the most apparent in the feedback
    private String SENTIMENT;


}
