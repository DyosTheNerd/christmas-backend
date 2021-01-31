package de.the.nerd.automaton.christmas.backend.services;

import de.the.nerd.automaton.christmas.backend.dto.WishListDTO;
import de.the.nerd.automaton.christmas.backend.entities.ChristmasMessage;
import de.the.nerd.automaton.christmas.backend.entities.WishList;
import de.the.nerd.automaton.christmas.backend.exceptions.ResourceNotFoundException;
import de.the.nerd.automaton.christmas.backend.repositories.WishRepository;
import de.the.nerd.automaton.christmas.backend.repositories.WishListRepository;
import org.activiti.engine.task.Task;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @Autowired
    TaskService taskService;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public WishListDTO getWishListByChristmasMessageID(Long messageID) {
        return  modelMapper.map(getWishlistByChristmasMessageIDInternal(messageID),WishListDTO.class);
    }

    private WishList getWishlistByChristmasMessageIDInternal(Long messageID){
        ChristmasMessage message = christmasMessageService.getChristmasMessage(messageID);
        Optional<WishList> optional = wishListRepository.findByOriginalChristmasMessage(message);

        if (optional.isPresent()){
            return optional.get();
        }

        throw new ResourceNotFoundException(messageID,"WishList");
    }

    private WishList getWishlistByWishlistIDInternal(Long wishlistID){
        Optional<WishList> optional = wishListRepository.findById(wishlistID);
        if (optional.isPresent()){
            return optional.get();
        }

        throw new ResourceNotFoundException(wishlistID,"WishList");
    }


    @Override
    public void getWishlistAsPDFIntoStream(Long wishListID, OutputStream output) {

        WishList wishList = getWishlistByWishlistIDInternal(wishListID);

        logger.info("Trying to close document print task and render wish list pdf for message id " + wishList.getOriginalChristmasMessage().getId());
        try {
            Task task = taskService.getTaskForTypeAndMessageID(wishList.getOriginalChristmasMessage().getId(), "Download PDF");

            taskService.completeTask(task.getId(), wishList.getOriginalChristmasMessage().getId(), new HashMap<>());
        }
        catch(ResourceNotFoundException e){
            // do nothing, document is ok to be downloaded multiple times.
            logger.info("Task for wish list document print does not exist (anymore)");
        }

        Map<String,Object> wishListVars = new HashMap<>();
        wishListVars.put("wishList",wishList);
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

        if (wishList.isPresent()){
            logger.debug("wishlist already present. cannot save more.");
            return 0l;
        }

        if (wishListDTO.getWishes().size() == 0){
            logger.debug("Tried saving Wishlist but no wishes for messageID " + christmasMessageID);
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

    @Override
    public Long saveWishListWithTask(WishListDTO wishListDTO, Long messageID) {
        Task task = taskService.getTaskForTypeAndMessageID(messageID, "Enter Wishlist Manually");

        Long wishListID = 0l;

        boolean wishListFound = (wishListDTO != null && wishListDTO.getWishes() != null && wishListDTO.getWishes().size() > 0);

        if (wishListFound) {
            logger.info("No Wishlist given, only close task");
            wishListID = saveWishList(wishListDTO, messageID);
        }
        Map<String, Object> taskVariables = new HashMap<>();
        taskVariables.put("wishListID",wishListID);
        taskVariables.put("manualWishListFound", wishListFound);



        taskService.completeTask(task.getId(),messageID,taskVariables);

        return wishListID;

    }
}
