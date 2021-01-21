package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="WISHLIST")
public class WishList {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORIGINAL_MESSAGE_ID", referencedColumnName = "ID")
    /* each wishlist has an original message it was created of.*/
    private ChristmasMessage originalChristmasMessage;


    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "APPROVAL_ID", referencedColumnName = "ID")
    private ElvenTask approvalRequest;

    @Getter
    @Setter
    @OneToMany(mappedBy = "wishList",fetch = FetchType.EAGER)
    private Set<Wish> wishes;

}
