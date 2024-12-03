package ru.krupnoveo.edu.record_service.api.dto.request;

import java.util.UUID;

public record ChangeTimeRecordRequest(
        UUID time
) {
}
