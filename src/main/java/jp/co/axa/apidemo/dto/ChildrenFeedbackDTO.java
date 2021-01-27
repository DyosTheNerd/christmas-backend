package jp.co.axa.apidemo.dto;

import lombok.Getter;
import lombok.Setter;

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
    private String sentiment;

}
