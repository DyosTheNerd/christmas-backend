package jp.co.axa.apidemo.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AnalysisPerformedEvent  extends ApplicationEvent {


    @Getter
    private Long christmasMessageID;

    @Getter
    private int wishesFound;

    @Getter
    private int feedbackFound;

    public AnalysisPerformedEvent(Object source, Long christmasMessageID, int noOfWishes, int noOfFeedback) {
        super(source);
        this.christmasMessageID = christmasMessageID;
        wishesFound = noOfWishes;
        feedbackFound = noOfFeedback;

    }

}
