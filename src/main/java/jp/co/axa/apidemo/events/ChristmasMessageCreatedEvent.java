package jp.co.axa.apidemo.events;
import jp.co.axa.apidemo.dto.ChristmasMessageDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


/**
 * This Event is fired after a message entered the system and was persisted.
 */
public class ChristmasMessageCreatedEvent  extends ApplicationEvent {

    /**
     * The details of the message
     */
    @Getter
    private ChristmasMessageDTO details;

    public ChristmasMessageCreatedEvent(Object source, ChristmasMessageDTO taskDetails) {
        super(source);
        this.details = taskDetails;
    }

}
