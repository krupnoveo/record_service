package ru.krupnoveo.edu.record_service.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BarbershopInfoResponse(
        String address,
        @JsonProperty("phone_number") String phoneNumber
) {
}
