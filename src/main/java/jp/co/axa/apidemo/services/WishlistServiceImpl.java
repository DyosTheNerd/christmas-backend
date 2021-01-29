package jp.co.axa.apidemo.services;


import jp.co.axa.apidemo.dto.WishListDTO;
import jp.co.axa.apidemo.entities.ChristmasMessage;
import jp.co.axa.apidemo.entities.WishList;
import jp.co.axa.apidemo.exceptions.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.WishListRepository;
import jp.co.axa.apidemo.repositories.WishRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.io.OutputStream;
import java.util.*;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private DocumentService docService;

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ChristmasMessageService christmasMessageService;

    @Autowired
    WishRepository wishRepository;


    @Override
    public WishListDTO getWishListByChristmasMessageID(Long messageID) {
        ChristmasMessage message = christmasMessageService.getChristmasMessage(messageID);
        Optional<WishList> optional = wishListRepository.findByOriginalChristmasMessage(message);

        if (optional.isPresent()){
            WishList theList = optional.get();
            WishListDTO listDto = modelMapper.map(theList,WishListDTO.class);
            return listDto;
        }

        throw new ResourceNotFoundException(messageID,"WishList");



    }

    @Override
    public void getWishlistAsPDFIntoStream(Long wishListID, OutputStream output) {

        Map<String,Object> wishListVars = new HashMap<>();



        wishListVars.put("wishList",getWishList(wishListID));

        IContext context = new Context(Locale.UK,wishListVars);


        docService.renderPDFIntoStreamForTemplate("templates//documents//Wishlist",output, context);
    }

    @Override
    public WishListDTO getWishList(Long wishListID) throws ResourceNotFoundException {
        Optional<WishList> optional = wishListRepository.findById(wishListID);
        if (optional.isPresent()){
            WishList theList = optional.get();
            WishListDTO listDto = modelMapper.map(theList,WishListDTO.class);
            return listDto;
        }
        throw new ResourceNotFoundException(wishListID,"Wishlist");

    }

    @Override
    public Long saveWishList(WishListDTO wishListDTO, Long christmasMessageID) {
        WishList list = modelMapper.map(wishListDTO,WishList.class);


        ChristmasMessage message = christmasMessageService.getChristmasMessage(christmasMessageID);
        list.setOriginalChristmasMessage(message);

        Optional<WishList> wishList = wishListRepository.findByOriginalChristmasMessage(message);

        if (!wishList.isPresent()){
            return 0l;
        }

        if (wishListDTO.getWishes().size() == 0){
            return 0l;
        }

        WishList persistedListHead = wishListRepository.save(list);


        list.getWishes().stream().forEach(item ->{
                    item.setWishList(persistedListHead);
                    persistedListHead.getWishes().add(item);
                    wishRepository.save(item);
                }
        );
        wishListRepository.flush();

        return persistedListHead.getId();


    }

    @Override
    public Long findWishlistIDByMessageID(Long messageID){
        ChristmasMessage message = christmasMessageService.getChristmasMessage(messageID);

        Optional<WishList> optional  = wishListRepository.findByOriginalChristmasMessage(message);
        if (!optional.isPresent()){
            return 0l;
        }
        return optional.get().getId();
    }



}
