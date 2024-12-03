package ru.krupnoveo.edu.record_service.api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.krupnoveo.edu.record_service.api.dto.response.ApiErrorResponse;
import ru.krupnoveo.edu.record_service.api.exception.AvailableTimeNotFoundException;
import ru.krupnoveo.edu.record_service.api.exception.RecordNotFoundException;

@ControllerAdvice
public class RecordExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleRecordNotFoundException(RecordNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(AvailableTimeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleAvailableTimeNotFoundException(AvailableTimeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
}
