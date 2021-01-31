package de.the.nerd.automaton.christmas.backend.entities;

import de.the.nerd.automaton.christmas.backend.enums.WishType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This entity class represents the entity ChristmasMessage.
 *
 * It contains links to the external original creator of the message, its status in the system, and the message itself.
 */
@Entity
@Table(name="WISH")
public class Wish {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="WISH_LIST_ID",nullable=false)
    private WishList wishList;

    @Getter
    @Setter
    @Column(name="SUBJECT")
    private String subject;

    @Getter
    @Setter
    @Column(name="WISH_TYPE")
    private WishType wishType;

    @Getter
    @Setter
    @Column(name="QUANTITY")
    private int quantity;


}
