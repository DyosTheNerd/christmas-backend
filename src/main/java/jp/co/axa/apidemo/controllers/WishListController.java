package jp.co.axa.apidemo.controllers;


import jp.co.axa.apidemo.dto.WishListDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.entities.WishList;
import jp.co.axa.apidemo.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;



@RestController
@RequestMapping("/api/v1")
public class WishListController {

    @Autowired
    WishlistService wishlistService;


    @GetMapping("/wishlist/{wishlistID}/wishlistdocument")
    public void getWishList(@PathVariable(name="wishlistID")Long wishListID, HttpServletResponse response) {

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition","attachment:filename=wishlistdocument.pdf");

        try {
            OutputStream output = response.getOutputStream();
            wishlistService.getWishlistAsPDFIntoStream(wishListID, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/wishLists/{wishListID}")
    public WishListDTO getWishList(@PathVariable(name="wishListID")Long wishListID) {

        return wishlistService.getWishList(wishListID);
    }

    @PostMapping("/christmasMessages/{christmasMessageID}/wishList/")
    public void saveChristmasMessage(WishListDTO wishList, @PathVariable(name="christmasMessageID") Long christmasMessageID){

        wishlistService.saveWishList(wishList, christmasMessageID);
    }

}
