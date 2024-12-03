package ru.krupnoveo.edu.record_service.api.exception;

import java.util.UUID;

public class AvailableTimeNotFoundException extends RuntimeException {
    public AvailableTimeNotFoundException(UUID id) {
        super("Время с id " + id + " не найдено");
    }
}
