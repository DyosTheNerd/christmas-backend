package de.the.nerd.automaton.christmas.backend.repositories;


import de.the.nerd.automaton.christmas.backend.entities.ChristmasMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChristmasMessageRepository extends JpaRepository<ChristmasMessage,Long> {
}
