package de.the.nerd.automaton.christmas.backend.controllers;

import de.the.nerd.automaton.christmas.backend.dto.ErrorInfoDTO;
import de.the.nerd.automaton.christmas.backend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {


    @ResponseStatus(HttpStatus.NOT_FOUND)  // 409
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ErrorInfoDTO resourceNotFoundResponse(HttpServletRequest req, Exception ex) {
        ErrorInfoDTO response = new ErrorInfoDTO(req.getRequestURL().toString(), ex);
        return response;
    }
}
