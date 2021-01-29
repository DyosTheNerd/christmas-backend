package jp.co.axa.apidemo.events;
import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class ChristmasMessageCreatedEvent  extends ApplicationEvent {

    @Getter
    private ChristmasMessageDTO details;

    public ChristmasMessageCreatedEvent(Object source, ChristmasMessageDTO taskDetails) {
        super(source);
        this.details = taskDetails;
    }

}
