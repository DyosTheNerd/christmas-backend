package de.the.nerd.automaton.christmas.backend.dto;

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
