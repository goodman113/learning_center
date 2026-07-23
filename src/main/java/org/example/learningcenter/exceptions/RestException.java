package org.example.learningcenter.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.example.learningcenter.entity.enums.ErrorType;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RestException extends RuntimeException {

    private HttpStatus status;
    private final ErrorType errorType;


    public RestException( ErrorType errorType) {
        this.errorType = errorType;
        this.status = errorType.getStatus();
    }

    private RestException( ErrorType errorType, HttpStatus status) {
        this.errorType = errorType;
        this.status = status;
    }

    public static RestException restThrow( ErrorType errorType) {
        return new RestException(errorType);
    }

    public static RestException restThrow( ErrorType errorType, HttpStatus status) {
        return new RestException(errorType, status);
    }

}