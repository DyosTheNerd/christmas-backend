package jp.co.axa.apidemo.entities;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class represents the entity ChristmasMessage.
 *
 * It contains links to the external original creator of the message, its status in the system, and the message itself.
 */
@Entity
@Table(name="CHRISTMAS_MESSAGE")
public class ChristmasMessage {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="EXT_CHILD_ID")
    // the child ID the wish is for. No Child data is stored by this application but retrieved from the child app
    // as can be seen in the system architecture diagram
    private String extChildID;

    @Getter
    @Setter
    @Column(name="EXTERNAL_ID")
    // external ID of the origin system
    private String externalId;


    @Getter
    @Setter
    @Column(name="TEXT")
    private String text;

}
