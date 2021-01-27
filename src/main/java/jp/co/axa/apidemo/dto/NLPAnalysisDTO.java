package jp.co.axa.apidemo.dto;

import lombok.Getter;
import lombok.Setter;

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
