package com.example.cslink.exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomResourceNotFoundException extends ResourceNotFoundException {
    public CustomResourceNotFoundException(String message, Long id) {
        super(message + id);
    }

    public CustomResourceNotFoundException(String message) {
        super(message);
    }
}
