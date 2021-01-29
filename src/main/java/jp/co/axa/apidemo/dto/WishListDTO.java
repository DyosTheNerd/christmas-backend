package jp.co.axa.apidemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * This DTO stores the header of a wishlist and all its wishes. It is detached from the message.
 */
public class WishListDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private List<WishDTO> wishes;



}
