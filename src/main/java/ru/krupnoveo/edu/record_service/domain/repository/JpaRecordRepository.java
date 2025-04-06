package ru.krupnoveo.edu.record_service.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krupnoveo.edu.record_service.domain.entity.RecordEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaRecordRepository extends JpaRepository<RecordEntity, UUID> {
    List<RecordEntity> findAllByClientId(UUID clientId);

    List<RecordEntity> findAllByBarberId(UUID barberId);

    List<RecordEntity> findAllByTimeId_Id(UUID timeIdId);

    List<RecordEntity> findAllByBarbershopId(UUID barbershopId);
}
