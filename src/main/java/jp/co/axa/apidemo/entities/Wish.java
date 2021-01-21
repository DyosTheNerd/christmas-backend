package jp.co.axa.apidemo.entities;

import jp.co.axa.apidemo.enums.WishType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
