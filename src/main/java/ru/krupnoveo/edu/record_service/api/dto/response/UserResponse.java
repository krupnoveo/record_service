package ru.krupnoveo.edu.record_service.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        String email,
        @JsonProperty("phone_number") String phoneNumber,
        String role,
        @JsonProperty("date_of_birth") OffsetDateTime dateOfBirth,
        @JsonProperty("barbershop_id") UUID barbershopId,
        String grade
) {
}
