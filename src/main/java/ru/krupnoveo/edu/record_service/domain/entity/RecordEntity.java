package ru.krupnoveo.edu.record_service.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "records")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "barber_id", nullable = false)
    private UUID barberId;

    @Column(name = "barbershop_id", nullable = false)
    private UUID barbershopId;

    @Column(name = "service_id", nullable = false)
    private UUID serviceId;

    @ManyToOne
    @JoinColumn(name = "time_id", referencedColumnName = "id")
    private AvailableTimeEntity timeId;
}
