package ru.krupnoveo.edu.record_service.api.client;

import ru.krupnoveo.edu.record_service.api.dto.response.BarbershopInfoResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.ServiceInfoResponse;

import java.util.UUID;

public interface BarbershopClient {

    BarbershopInfoResponse getBarbershopInfo(UUID id);

    ServiceInfoResponse getServiceInfo(UUID id, String grade);
}
