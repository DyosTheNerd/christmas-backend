package jp.co.axa.apidemo.services;


import java.util.List;

public interface ChristmasMessageAnalysisService {

    List<String> analyseMessage(Long messageID);

}
