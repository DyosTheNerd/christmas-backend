package jp.co.axa.apidemo.dto;

import lombok.Getter;

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
