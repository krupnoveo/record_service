package ru.krupnoveo.edu.record_service.api.client;

import ru.krupnoveo.edu.record_service.api.dto.response.BarberInfoResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.ClientInfoResponse;

import java.util.List;
import java.util.UUID;

public interface UserClient {

    ClientInfoResponse getClientInfo(UUID id);

    ClientInfoResponse getClientInfo(String token);

    UUID getClientId(String token);

    BarberInfoResponse getBarberInfo(UUID id);

    List<UUID> getAllBarbers();
}
