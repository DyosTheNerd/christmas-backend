package de.the.nerd.automaton.christmas.backend.events;

import de.the.nerd.automaton.christmas.backend.dto.ManualTaskDetailsDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


/**
 * This Event is fired when the work for a manual task was done.
 */
public class ManualTaskCompletedEvent extends ApplicationEvent {


    /**
     * The task details
     */
    @Getter
    private ManualTaskDetailsDTO details;

    public ManualTaskCompletedEvent(Object source, ManualTaskDetailsDTO taskDetails) {
        super(source);
        this.details = taskDetails;
    }
}
