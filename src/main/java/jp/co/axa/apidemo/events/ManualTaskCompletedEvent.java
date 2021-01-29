package jp.co.axa.apidemo.events;

import jp.co.axa.apidemo.dto.ManualTaskDetailsDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ManualTaskCompletedEvent extends ApplicationEvent {


    @Getter
    private ManualTaskDetailsDTO details;

    public ManualTaskCompletedEvent(Object source, ManualTaskDetailsDTO taskDetails) {
        super(source);
        this.details = taskDetails;
    }
}
