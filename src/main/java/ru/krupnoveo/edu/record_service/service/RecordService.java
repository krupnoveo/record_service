package ru.krupnoveo.edu.record_service.service;

import ru.krupnoveo.edu.record_service.api.dto.request.ChangeTimeRecordRequest;
import ru.krupnoveo.edu.record_service.api.dto.request.CreateRecordRequest;
import ru.krupnoveo.edu.record_service.api.dto.response.RecordResponse;

import java.util.List;
import java.util.UUID;

public interface RecordService {
    RecordResponse getRecordById(UUID id);

    List<RecordResponse> getAllRecords(String token, UUID barbershopId);

    RecordResponse deleteRecordById(UUID id);

    RecordResponse createRecord(CreateRecordRequest request, String token);

    RecordResponse updateRecordTime(UUID id, ChangeTimeRecordRequest request);
}
