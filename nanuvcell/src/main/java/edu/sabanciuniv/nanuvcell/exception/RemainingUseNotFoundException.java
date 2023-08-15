package edu.sabanciuniv.nanuvcell.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RemainingUseNotFoundException extends RuntimeException {

    public RemainingUseNotFoundException(String message) {
        super(message);
    }
}
