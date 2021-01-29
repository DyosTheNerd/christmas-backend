package jp.co.axa.apidemo.controllers;


import io.swagger.v3.oas.annotations.Operation;
import jp.co.axa.apidemo.dto.WishListDTO;
import jp.co.axa.apidemo.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


/**
 * This controller works with the wish list business object, to provide it and derived artifacts to consumers.
 */
@RestController
@RequestMapping("/api/v1")
public class WishListController {

    @Autowired
    WishlistService wishlistService;



    @Operation(summary = "Get a pdf file of the wishlist.", description = "If the wishlist exists, this will return a" +
            "pdf document with the wishlist for a order processing elf to work on.")
    @GetMapping("/wishlists/{wishListID}/wishlistdocument")
    public void getWishList(@PathVariable(name="wishListID")Long wishListID, HttpServletResponse response) {

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition","attachment:filename=wishlistdocument.pdf");

        try {
            OutputStream output = response.getOutputStream();
            wishlistService.getWishlistAsPDFIntoStream(wishListID, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Operation(summary = "Retrieve the an existing wishlist.", description = "If the wishlist exists, " +
            "this method will return it. If not, a ResourceNotFoundException will be thrown")
    @GetMapping("/wishlists/{wishListID}")
    public WishListDTO getWishList(@PathVariable(name="wishListID")Long wishListID) {

        return wishlistService.getWishList(wishListID);
    }


    @Operation(summary = "Create a new wishlist for the corresponding messageid.", description = "If ")
    @PostMapping("/christmasMessages/{christmasMessageID}/wishList/")
    public void saveChristmasMessage(WishListDTO wishList, @PathVariable(name="christmasMessageID") Long christmasMessageID){

        wishlistService.saveWishListWithTask(wishList, christmasMessageID);
    }


    @Operation(summary = "Retrieve the an existing wishlist by the corresponding messageid.", description = "Create a wish " +
            "list for a message with given id. If no feedback or wishlist is found, the system will request via email for " +
            "a wishlist  to be amended manually. If and only if this happened, calling this method will succeed and amend the wish list once.")
    @GetMapping("/christmasMessages/{christmasMessageID}/wishList/")
    public WishListDTO getWishListForChristmasMessage(@PathVariable(name="christmasMessageID")Long christmasMessageID) {

        return wishlistService.getWishListByChristmasMessageID(christmasMessageID);
    }


}
