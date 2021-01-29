package jp.co.axa.apidemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


/**
 * This DTO stores task data.
 */
public class ManualTaskDetailsDTO {

    @Getter
    @Setter
    String taskID;

    @Getter
    @Setter
    Map resultParams;

    @Getter
    @Setter
    Long messageID;



}
