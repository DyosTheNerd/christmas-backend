package de.the.nerd.automaton.christmas.backend.events;


import de.the.nerd.automaton.christmas.backend.dto.LineIncomingEventDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class LineMessageReceivedEvent extends ApplicationEvent {

    /**
     * The details of the message
     */
    @Getter
    private LineIncomingEventDto details;

    public LineMessageReceivedEvent(Object source, LineIncomingEventDto taskDetails) {
        super(source);
        this.details = taskDetails;
    }
}
