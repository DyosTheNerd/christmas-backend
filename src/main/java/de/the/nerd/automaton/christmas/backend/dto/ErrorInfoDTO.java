package de.the.nerd.automaton.christmas.backend.dto;


/**
 * This DTO is used for errorhandling.
 */
public class ErrorInfoDTO {
    public final String url;
    public final String exception;

    public ErrorInfoDTO(String url, Exception ex) {
        this.url = url;
        this.exception = ex.getLocalizedMessage();
    }
}
