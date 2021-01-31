package de.the.nerd.automaton.christmas.backend.services;

import de.the.nerd.automaton.christmas.backend.dto.WishListDTO;

import java.io.OutputStream;

public interface WishlistService {

    WishListDTO getWishListByChristmasMessageID(Long messageID);

    void getWishlistAsPDFIntoStream(Long wishListID, OutputStream output);

    WishListDTO getWishList(Long wishListID);


    /**
     * This Method creates a new Wishlist object, and links it if given with the christmas message
     * @param wishListDTO the data of the list
     * @param christmasMessageID optional parameter for a message this list was generated from
     */
    Long saveWishList(WishListDTO wishListDTO, Long christmasMessageID);

    Long findWishlistIDByMessageID(Long messageID);


    Long saveWishListWithTask(WishListDTO wishListDTO, Long messageID);
}
