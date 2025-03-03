package ru.krupnoveo.edu.record_service.api.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.krupnoveo.edu.record_service.api.client.UserClient;
import ru.krupnoveo.edu.record_service.api.dto.response.BarberInfoResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.ClientInfoResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.ListUserResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;


@Service
public class UserClientImpl implements UserClient {

    private final WebClient webClient;

    public UserClientImpl(
            @Autowired WebClient.Builder webClient,
            @Value("${user.service.name}") String userServiceName
    ) {
        String baseUrl = "http://" + userServiceName;
        this.webClient = webClient.baseUrl(baseUrl).build();
    }


    @Override
    public ClientInfoResponse getClientInfo(UUID id) {
        var resp = webClient
                .get()
                .uri("/me/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (resp != null) {
            return new ClientInfoResponse(resp.firstName(), resp.role(), resp.id());
        }
        return new ClientInfoResponse("", "", null);
    }

    @Override
    public ClientInfoResponse getClientInfo(String token) {
        UUID id = getClientId(token);
        return getClientInfo(id);
    }

    @Override
    public UUID getClientId(String token) {
        var resp = webClient
                .get()
                .uri("/me")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (resp != null) {
            return resp.id();
        }
        return null;
    }

    @Override
    public BarberInfoResponse getBarberInfo(UUID id) {
        var resp = webClient
                .get()
                .uri("/me/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (resp != null) {
            return new BarberInfoResponse(resp.firstName(), resp.grade());
        }
        return new BarberInfoResponse("", "");
    }

    @Override
    public List<UUID> getAllBarbers() {
        var resp = webClient
                .get()
                .uri("/me/all?role=BARBER")
                .retrieve()
                .bodyToMono(ListUserResponse.class)
                .block();
        if (resp == null) {
            return List.of();
        }
        return resp.users().stream().map(UserResponse::id).toList();
    }
}
