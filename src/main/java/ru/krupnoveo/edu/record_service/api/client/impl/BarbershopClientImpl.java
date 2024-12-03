package ru.krupnoveo.edu.record_service.api.client.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.krupnoveo.edu.record_service.api.client.BarbershopClient;
import ru.krupnoveo.edu.record_service.api.dto.response.BarbershopInfoResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.BarbershopResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.ServiceInfoResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.ServiceResponse;

import java.util.UUID;

@Service
public class BarbershopClientImpl implements BarbershopClient {
    private static final String BARBERSHOP_SERVICE_URL = "http://localhost:8081";

    private final WebClient webClient;

    public BarbershopClientImpl() {
        this.webClient = WebClient.builder().baseUrl(BARBERSHOP_SERVICE_URL).build();
    }

    @Override
    public BarbershopInfoResponse getBarbershopInfo(UUID id) {
        var resp = webClient
                .get()
                .uri("/barbershop/{id}", id)
                .retrieve()
                .bodyToMono(BarbershopResponse.class)
                .block();
        if (resp == null) {
            return new BarbershopInfoResponse("", "");
        }
        return new BarbershopInfoResponse(resp.address(), resp.phoneNumber());
    }

    @Override
    public ServiceInfoResponse getServiceInfo(UUID id, String grade) {
        var resp = webClient
                .get()
                .uri("/service/{id}", id)
                .retrieve()
                .bodyToMono(ServiceResponse.class)
                .block();
        if (resp == null) {
            return new ServiceInfoResponse("", -1);
        }
        return switch (grade) {
            case "1" -> new ServiceInfoResponse(resp.name(), resp.firstGradePrice());
            case "2" -> new ServiceInfoResponse(resp.name(), resp.secondGradePrice());
            default -> new ServiceInfoResponse(resp.name(), resp.thirdGradePrice());
        };
    }
}
