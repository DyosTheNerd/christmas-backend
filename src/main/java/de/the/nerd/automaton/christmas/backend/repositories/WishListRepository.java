package de.the.nerd.automaton.christmas.backend.repositories;

import de.the.nerd.automaton.christmas.backend.entities.ChristmasMessage;
import de.the.nerd.automaton.christmas.backend.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListRepository  extends JpaRepository<WishList,Long> {


    Optional<WishList> findByOriginalChristmasMessage(ChristmasMessage originalChristmasMessage);

}
