package ru.krupnoveo.edu.record_service.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.krupnoveo.edu.record_service.api.dto.response.AvailableTimeResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "available_times")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "time", nullable = false)
    private OffsetDateTime time;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    public AvailableTimeResponse toDto() {
        return new AvailableTimeResponse(
                id,
                time,
                isAvailable
        );
    }
    @OneToMany(mappedBy = "timeId")
    private List<RecordEntity> records;
}
