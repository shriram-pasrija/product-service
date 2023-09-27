package com.example.productservice.exceptions;

import com.example.productservice.dto.response.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionResponseDto> handleNotFoundException(
            NotFoundException notFoundException
    ) {
        return new ResponseEntity<>(
                new ExceptionResponseDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<ExceptionResponseDto> handleBadRequestException(
            BadRequestException badRequestException
    ) {
        return new ResponseEntity<>(
                new ExceptionResponseDto(HttpStatus.BAD_REQUEST, badRequestException.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
