package jp.co.axa.apidemo.services;


import jp.co.axa.apidemo.dto.AnalysisResultDTO;

import java.util.List;

public interface ChristmasMessageAnalysisService {

    List<String> getBasicMessageAnalysis(Long messageID);


}
