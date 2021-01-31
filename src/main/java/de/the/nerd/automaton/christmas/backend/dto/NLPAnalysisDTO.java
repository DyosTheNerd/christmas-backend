package de.the.nerd.automaton.christmas.backend.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * This DTO is used by to store model information Analysis Services.
 */
public class NLPAnalysisDTO {

    @Getter
    @Setter
    String[] tokens ;
    @Getter
    @Setter
    String[] tags;
    @Getter
    @Setter
    String[] lemmata ;
    @Getter
    @Setter
    String category ;
    @Getter
    @Setter
    String sentence;
}
