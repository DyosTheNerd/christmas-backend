package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.dto.WishListDTO;
import jp.co.axa.apidemo.entities.WishList;

import java.io.OutputStream;

public interface WishlistService {


    void getWishlistAsPDFIntoStream(Long wishListID, OutputStream output);

    WishListDTO getWishList(Long wishListID);


    /**
     * This Method creates a new Wishlist object, and links it if given with the christmas message
     * @param wishListDTO the data of the list
     * @param christmasMessageID optional parameter for a message this list was generated from
     */
    void saveWishList(WishListDTO wishListDTO, Long christmasMessageID);

}
