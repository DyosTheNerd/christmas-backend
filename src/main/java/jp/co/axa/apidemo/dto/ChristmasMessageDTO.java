package jp.co.axa.apidemo.dto;

import jp.co.axa.apidemo.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ChristmasMessageDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String extChildID;

    @Getter
    @Setter
    private String externalId;


    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private TaskStatus state;
}
