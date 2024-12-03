package ru.krupnoveo.edu.record_service.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

public record AvailableTimeResponse(
        UUID id,
        OffsetDateTime time,
        @JsonProperty("is_available") Boolean isAvailable
) {
}
