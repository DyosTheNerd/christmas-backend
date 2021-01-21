package jp.co.axa.apidemo.exceptions;

import lombok.Getter;

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
