package ru.krupnoveo.edu.record_service.api.dto.response;

import java.util.List;

public record ListAvailableTimeResponse(
        List<AvailableTimeResponse> times
) {
}
