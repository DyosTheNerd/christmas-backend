package jp.co.axa.apidemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

public class ChildrenFeedbackDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @JsonIgnore
    private Long originalChristmasMessageID;

    @Getter
    @Setter
    private String textExtract;


    @Getter
    @Setter
    private String sentiment;

}
