package de.the.nerd.automaton.christmas.backend.unit.dto;


import de.the.nerd.automaton.christmas.backend.dto.WishListDTO;
import de.the.nerd.automaton.christmas.backend.entities.Wish;
import de.the.nerd.automaton.christmas.backend.entities.WishList;
import de.the.nerd.automaton.christmas.backend.enums.WishType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;


/**
 * This class tests the model Mapper configuration for WishList Entity WishListDTO
 */
@RunWith(MockitoJUnitRunner.class)
public class WishListDTOTest {

    @InjectMocks
    ModelMapper mapper;

    @Test
    public void testAssignEntityToDTO(){
        WishList source = new WishList();
        source.setId(2l);

        HashSet<Wish> wishSet = new HashSet<Wish>();
        Wish wishSource = new Wish();
        wishSource.setWishType(WishType.PHYSICAL);
        wishSource.setId(3l);
        wishSource.setSubject("A Subject");
        wishSource.setQuantity(2);

        wishSet.add(wishSource);
        source.setWishes(wishSet);

        WishListDTO targetDTO = mapper.map(source,WishListDTO.class);

        assertEquals("ID correct",source.getId(),targetDTO.getId());
        assertEquals("Wish Item ID correct",source.getWishes().iterator().next().getId(),wishSource.getId());
        assertEquals("Wish Item Quantity correct",source.getWishes().iterator().next().getQuantity(),wishSource.getQuantity());
        assertEquals("ID correct",source.getWishes().iterator().next().getSubject(),wishSource.getSubject());
        assertEquals("ID correct",source.getWishes().iterator().next().getWishType(),wishSource.getWishType());

    }



}
