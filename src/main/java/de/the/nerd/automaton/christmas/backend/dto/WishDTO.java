package de.the.nerd.automaton.christmas.backend.dto;

import de.the.nerd.automaton.christmas.backend.enums.WishType;
import lombok.Getter;
import lombok.Setter;


/**
 * This DTO stores wish data
 */
public class WishDTO {

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private WishType wishType;

    @Getter
    @Setter
    private int quantity;
}
