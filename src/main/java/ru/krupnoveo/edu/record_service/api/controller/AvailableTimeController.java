package ru.krupnoveo.edu.record_service.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.krupnoveo.edu.record_service.api.dto.response.ListAvailableTimeResponse;
import ru.krupnoveo.edu.record_service.service.AvailableTimeService;

import java.util.UUID;

@RestController
@RequestMapping("/available_time")
@RequiredArgsConstructor
public class AvailableTimeController {

    private final AvailableTimeService availableTimeService;

    @GetMapping("/all")
    public ResponseEntity<ListAvailableTimeResponse> getAllAvailableTimes(@RequestParam(required = false) UUID barberId) {
        return ResponseEntity.ok().body(
                new ListAvailableTimeResponse(availableTimeService.getAllAvailableTimes(barberId))
        );
    }
}
