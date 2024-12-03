package ru.krupnoveo.edu.record_service.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreateRecordRequest(
    @JsonProperty("client_id") UUID clientId,
    @JsonProperty("barber_id") UUID barberId,
    @JsonProperty("barbershop_id") UUID barbershopId,
    @JsonProperty("service_id") UUID serviceId,
    @JsonProperty("time_id") UUID timeId
) {
}
