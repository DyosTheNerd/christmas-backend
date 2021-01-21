package jp.co.axa.apidemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class WishListDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private List<WishDTO> wishes;



}
