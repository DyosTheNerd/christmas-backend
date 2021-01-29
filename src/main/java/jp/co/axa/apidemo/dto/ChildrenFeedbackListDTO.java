package jp.co.axa.apidemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * List Wrapper DTO.
 */
public class ChildrenFeedbackListDTO {

    @Getter
    @Setter
    private List<ChildrenFeedbackDTO> feedback;
}
