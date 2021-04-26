package de.the.nerd.automaton.christmas.backend.services;

import java.util.Map;

public interface LineRequestAuthenticationService {

    public boolean validateLineRequest(String requestBody, String authHeader);


    public String getAuthHeaderContent(Map<String,String> headers);
}
