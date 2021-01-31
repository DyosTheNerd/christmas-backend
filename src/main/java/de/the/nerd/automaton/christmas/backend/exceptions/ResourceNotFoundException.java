package de.the.nerd.automaton.christmas.backend.exceptions;

import lombok.Getter;

/**
 * This Exception is used when a resource cannot be found by the corresponding service.
 */
public class ResourceNotFoundException extends RuntimeException{

    @Getter
    private long id;

    @Getter
    private String resourceType;

    public ResourceNotFoundException(long id, String resourceType){
        this.id = id;
        this.resourceType = resourceType;

    }

    @Override
    public String getLocalizedMessage(){
        return "Resource of type " + resourceType + " with ID " + id + " not found.";

    }

}
