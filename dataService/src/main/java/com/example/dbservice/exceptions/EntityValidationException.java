package com.example.dbservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Represent all kind of EntityValidationException.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EntityValidationException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST; // set default to bad request
    public EntityValidationException(String message) {
        super(message);
    }

}
