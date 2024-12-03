package ru.krupnoveo.edu.record_service.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ClientInfoResponse(
        @JsonProperty("first_name") String firstName,
        String role,
        UUID id
) {
}
