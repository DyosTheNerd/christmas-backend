package de.the.nerd.automaton.christmas.backend.dto;

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
