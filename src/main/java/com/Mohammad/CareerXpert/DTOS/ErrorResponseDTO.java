package com.Mohammad.CareerXpert.DTOS;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseDTO {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;

    public ErrorResponseDTO(LocalDateTime timestamp, HttpStatus statusCode, String error) {
        this.timestamp = timestamp;
        this.status = statusCode;
        this.error = error;
    }

    public ErrorResponseDTO() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatusCode() {
        return status;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.status = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
