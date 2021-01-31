package de.the.nerd.automaton.christmas.backend.repositories;

import de.the.nerd.automaton.christmas.backend.entities.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish,Long> {
}
