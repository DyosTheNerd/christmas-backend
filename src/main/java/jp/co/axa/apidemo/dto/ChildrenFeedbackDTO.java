package jp.co.axa.apidemo.dto;

import jp.co.axa.apidemo.entities.ChristmasMessage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

public class ChildrenFeedbackDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long originalChristmasMessageID;

    @Getter
    @Setter
    private String textExtract;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String scaleValue;

    @Getter
    @Setter
    private String SENTIMENT;

}
