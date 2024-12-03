package ru.krupnoveo.edu.record_service.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krupnoveo.edu.record_service.domain.entity.AvailableTimeEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface JpaAvailableTimeRepository extends JpaRepository<AvailableTimeEntity, UUID> {

    void deleteAllByTimeIsBefore(OffsetDateTime timeBefore);

    List<AvailableTimeEntity> findAllByTimeIsAfter(OffsetDateTime timeAfter);
}
