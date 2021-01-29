package jp.co.axa.apidemo.events;

import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AnalysisFailedEvent  extends ApplicationEvent {

    @Getter
    private Long christmasMessageID;

    public AnalysisFailedEvent(Object source, Long christmasMessageID) {
        super(source);
        this.christmasMessageID = christmasMessageID;
    }
}
