package de.the.nerd.automaton.christmas.backend.dto;

import lombok.Getter;


/**
 * This DTO is used and provided by the analysis services to simplify interfaces.
 */
public class AnalysisResultDTO {


    @Getter
    private Long messageID;

    @Getter
    private  int numberOfFeedbackFound;

    @Getter
    private int numberOfWishesFound;

    @Getter
    Long wishListID;

    public AnalysisResultDTO(Long messageID, int numberOfFeedbackFound, int numberOfWishesFound, Long wishListID) {
        this.messageID = messageID;
        this.numberOfFeedbackFound = numberOfFeedbackFound;
        this.numberOfWishesFound = numberOfWishesFound;
        this.wishListID = wishListID;
    }
}
