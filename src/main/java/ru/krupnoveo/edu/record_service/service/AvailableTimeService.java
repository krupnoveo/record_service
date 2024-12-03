package ru.krupnoveo.edu.record_service.service;

import ru.krupnoveo.edu.record_service.api.dto.response.AvailableTimeResponse;

import java.util.List;
import java.util.UUID;

public interface AvailableTimeService {
    List<AvailableTimeResponse> getAllAvailableTimes(UUID barberId);

    void updateAvailableTimes();

    boolean isDatabaseEmpty();
}
