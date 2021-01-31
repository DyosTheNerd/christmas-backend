package de.the.nerd.automaton.christmas.backend.entities;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This entity class represents the entity ChristmasMessage.
 *
 * It contains links to the external original creator of the message, its status in the system, and the message itself.
 */
@Entity
@Table(name="CHRISTMAS_MESSAGE")
public class ChristmasMessage {

    /**
     * this entities id
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * the child ID the wish is for. No Child data is stored by this application but retrieved from the child app
     * as can be seen in the system architecture diagram
     */
    @Getter
    @Setter
    @Column(name="EXT_CHILD_ID")

    private String extChildID;

    /**
     * external ID of the origin system, for link purposes of client applications
     */
    @Getter
    @Setter
    @Column(name="EXTERNAL_ID")
    private String externalId;

    /**
     * the message text
     */
    @Getter
    @Setter
    @Column(name="TEXT")
    private String text;

}
