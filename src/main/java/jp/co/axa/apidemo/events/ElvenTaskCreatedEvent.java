package jp.co.axa.apidemo.events;


import jp.co.axa.apidemo.dto.ElvenTaskDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;




public class ElvenTaskCreatedEvent extends ApplicationEvent {

    @Getter
    private ElvenTaskDTO details;

    public ElvenTaskCreatedEvent(Object source, ElvenTaskDTO taskDetails) {
        super(source);
        this.details = taskDetails;
    }


}
