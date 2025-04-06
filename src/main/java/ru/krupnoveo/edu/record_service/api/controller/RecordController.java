package ru.krupnoveo.edu.record_service.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.krupnoveo.edu.record_service.api.dto.request.ChangeTimeRecordRequest;
import ru.krupnoveo.edu.record_service.api.dto.request.CreateRecordRequest;
import ru.krupnoveo.edu.record_service.api.dto.response.ListRecordResponse;
import ru.krupnoveo.edu.record_service.api.dto.response.RecordResponse;
import ru.krupnoveo.edu.record_service.service.RecordService;

import java.util.UUID;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/{id}")
    public ResponseEntity<RecordResponse> getRecordById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok().body(recordService.getRecordById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<ListRecordResponse> getAllRecords(
            @RequestHeader("Authorization") String token,
            @RequestParam(required = false) UUID barbershopId
    ) {
        return ResponseEntity.ok().body(
                new ListRecordResponse(recordService.getAllRecords(token, barbershopId))
        );
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<RecordResponse> deleteRecord(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok().body(recordService.deleteRecordById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<RecordResponse> createRecord(
            @RequestBody CreateRecordRequest request,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recordService.createRecord(request, token));
    }

    @PostMapping("/{id}/update/time")
    public ResponseEntity<RecordResponse> updateRecordTime(
            @PathVariable UUID id,
            @RequestBody ChangeTimeRecordRequest request
    ) {
        return ResponseEntity.ok().body(recordService.updateRecordTime(id, request));
    }
}
