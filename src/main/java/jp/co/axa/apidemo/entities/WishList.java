package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * This entity class represents the entity WishList.
 *
 * It acts as a header between the wishes and the christmas message.
 */
@Entity
@Table(name="WISHLIST")
public class WishList {

    /**
     * This entities ID
     */
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * This is the link to the original message this wishlist was created for.
     */
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORIGINAL_MESSAGE_ID", referencedColumnName = "ID")

    private ChristmasMessage originalChristmasMessage;

    /**
     * The wish list items.
     */
    @Getter
    @Setter
    @OneToMany(mappedBy = "wishList",fetch = FetchType.EAGER)
    private Set<Wish> wishes;

}
