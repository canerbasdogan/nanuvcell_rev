package edu.sabanciuniv.nanuvcell.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MobileTariffNotFoundException extends RuntimeException {

    public MobileTariffNotFoundException(String message) {
        super(message);
    }
}