package de.the.nerd.automaton.christmas.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;



/**
 * This entity class is the basic model for the feedback object that is extracted from the incoming messages.
 */
@Entity
@Table(name="CHILDREN_FEEDBACK")
public class ChildrenFeedback {


    /**
     * this entities id
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * the message this feedback was extracted from
     */
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "originalMessage_id", referencedColumnName = "id")
    private ChristmasMessage originalChristmasMessage;


    /**
     * a quote of text to qualify the feedback
     */
    @Getter
    @Setter
    @Column(name="TEXT_EXTRACT")
    private String textExtract;


    /**
     *  What kind of emotion was the most apparent in the feedback
     */
    @Getter
    @Setter
    @Column(name="SENTIMENT")
    private String sentiment;


}
