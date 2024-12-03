package ru.krupnoveo.edu.record_service.api.exception;

import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(UUID id) {
        super("Запись с id " + id + " не найдена");
    }
}
