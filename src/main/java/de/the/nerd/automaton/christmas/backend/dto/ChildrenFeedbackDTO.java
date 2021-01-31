package de.the.nerd.automaton.christmas.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;



/**
 * DTO for the exchange of feedback objects.
 */
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
