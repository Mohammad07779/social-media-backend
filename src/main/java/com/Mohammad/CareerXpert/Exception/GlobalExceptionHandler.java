package com.Mohammad.CareerXpert.Exception;

import com.Mohammad.CareerXpert.DTOS.ErrorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> ResourceNotFoundExceptionHandler(ResourceNotFoundException e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KeyAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> KeyAlreadyExistsExceptionHandler(KeyAlreadyExistsException e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> BadCredentialsExceptionHandler(BadCredentialsException e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> AccessDeniedExceptionHandler(AccessDeniedException e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDTO> IllegalArgumentExceptionHandler(IllegalStateException e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDTO> UnauthorizedExceptionHandler(UnauthorizedException e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponseDTO> SignatureExceptionHandler(SignatureException e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO>ExceptionHandler(Exception e){
        return buildErrorMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<ErrorResponseDTO> buildErrorMessage(String message, HttpStatus status) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(LocalDateTime.now(), status, message);
        return new ResponseEntity<>(errorResponse, status);
    }

}
