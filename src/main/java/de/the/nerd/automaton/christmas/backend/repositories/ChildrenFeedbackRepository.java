package de.the.nerd.automaton.christmas.backend.repositories;

import de.the.nerd.automaton.christmas.backend.entities.ChildrenFeedback;
import de.the.nerd.automaton.christmas.backend.entities.ChristmasMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChildrenFeedbackRepository extends JpaRepository<ChildrenFeedback,Long> {


    List<ChildrenFeedback> findByOriginalChristmasMessage(ChristmasMessage originalChristmasMessage);

}
