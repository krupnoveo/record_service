package ru.krupnoveo.edu.record_service.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BarberInfoResponse(
        @JsonProperty("first_name") String firstName,
        String grade
) {
}
