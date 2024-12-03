package ru.krupnoveo.edu.record_service.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record BarbershopResponse(
        UUID id,
        String name,
        String address,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("working_time") String workingTime
) {
}
