package jp.co.axa.apidemo.dto;

import jp.co.axa.apidemo.enums.WishType;
import lombok.Getter;
import lombok.Setter;


/**
 * This DTO stores wish data
 */
public class WishDTO {

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private WishType wishType;

    @Getter
    @Setter
    private int quantity;
}
