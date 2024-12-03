package ru.krupnoveo.edu.record_service.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ServiceResponse(
        UUID id,
        String name,
        String description,
        @JsonProperty("first_grade_price") int firstGradePrice,
        @JsonProperty("second_grade_price") int secondGradePrice,
        @JsonProperty("third_grade_price") int thirdGradePrice,
        @JsonProperty("average_time") int averageTime
) {
}
