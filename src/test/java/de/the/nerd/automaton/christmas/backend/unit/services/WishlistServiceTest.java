package de.the.nerd.automaton.christmas.backend.unit.services;

import de.the.nerd.automaton.christmas.backend.entities.WishList;
import de.the.nerd.automaton.christmas.backend.dto.WishListDTO;
import de.the.nerd.automaton.christmas.backend.exceptions.ResourceNotFoundException;
import de.the.nerd.automaton.christmas.backend.repositories.WishListRepository;
import de.the.nerd.automaton.christmas.backend.services.WishlistServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WishlistServiceTest {

    @InjectMocks
    WishlistServiceImpl testObject;

    @Mock
    WishListRepository wishListRepo;

    @Mock
    ModelMapper mapper;

    /**
     * This tests, whether the find method uses the persistence architecture and then uses the mapper service to get a dto
     */
    @Test
    public void testFindCallsPersistenceAndGetsDTOFromService(){

        // Setup mocks
        WishList wl = new WishList();
        Optional<WishList> responseWrapper = Optional.of(wl);

        when(wishListRepo.findById(1l)).thenReturn(responseWrapper);

        WishListDTO expectedDTO = new WishListDTO();

        when(mapper.map(any(),any())).thenReturn(expectedDTO);


        // Perform
        WishListDTO returnValue = testObject.getWishList(1l);


        // Verify
        assertEquals("Returned Value is retrieved from Mapping Service",expectedDTO,returnValue);
    }

    @Test
    public void testFindNotFound(){

        // Setup mocks

        Optional<WishList> responseWrapper = Optional.empty();

        when(wishListRepo.findById(1l)).thenReturn(responseWrapper);

        // Perform
        try {
            WishListDTO returnValue = testObject.getWishList(1l);
        }
        catch (ResourceNotFoundException expected) {
            return;
        }

        fail();
    }

    @Test
    public void testUsePassedIDForPersistence(){

        // Setup mocks

        WishList list = new WishList();

        Optional<WishList> responseWrapper = Optional.of(list);

        when(wishListRepo.findById(1l)).thenReturn(responseWrapper);

        // Perform
        WishListDTO returnValue = testObject.getWishList(1l);


        // Verify
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);

        verify(wishListRepo).findById(captor.capture());

        assertEquals("Call uses passed ID value",new Long(1l),captor.getValue());
    }


}
