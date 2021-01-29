package jp.co.axa.apidemo.dto;

import lombok.Getter;
import lombok.Setter;



public class ChristmasMessageDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String extChildID;

    @Getter
    @Setter
    private String externalId;


    @Getter
    @Setter
    private String text;

    @Getter
    @Setter
    private Long wishListID;

}
