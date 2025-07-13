package dev.redbeans.bootcamp202506eirzarog.springapi.exceptions;

import org.springframework.http.HttpStatus;

public class BootcampException extends Exception {

    private HttpStatus httpStatus;

    public BootcampException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
