package ru.krupnoveo.edu.record_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krupnoveo.edu.record_service.api.client.UserClient;
import ru.krupnoveo.edu.record_service.api.dto.response.AvailableTimeResponse;
import ru.krupnoveo.edu.record_service.domain.entity.AvailableTimeEntity;
import ru.krupnoveo.edu.record_service.domain.entity.RecordEntity;
import ru.krupnoveo.edu.record_service.domain.repository.JpaAvailableTimeRepository;
import ru.krupnoveo.edu.record_service.domain.repository.JpaRecordRepository;
import ru.krupnoveo.edu.record_service.service.AvailableTimeService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvailableTimeServiceImpl implements AvailableTimeService {
    private final JpaAvailableTimeRepository availableTimeRepository;
    private final JpaRecordRepository recordRepository;
    private final UserClient userClient;

    @Override
    public List<AvailableTimeResponse> getAllAvailableTimes(UUID barberId) {
        List<AvailableTimeEntity> allTimes = availableTimeRepository.findAllByTimeIsAfter(OffsetDateTime.now());
        if (barberId != null) {
            List<AvailableTimeEntity> bookedTimes = recordRepository
                    .findAllByBarberId(barberId)
                    .stream().map(RecordEntity::getTimeId)
                    .toList();
            allTimes.removeAll(bookedTimes);
        } else {
            List<UUID> barbersId = userClient.getAllBarbers();
            for (var time : allTimes) {
                List<UUID> barbers = recordRepository
                        .findAllByTimeId_Id(time.getId())
                        .stream()
                        .map(RecordEntity::getBarberId)
                        .toList();
                if (barbersId == barbers) {
                    allTimes.remove(time);
                }
            }
        }
        return allTimes.stream().map(AvailableTimeEntity::toDto).toList();
    }

    @Override
    public void updateAvailableTimes() {
        availableTimeRepository.deleteAllByTimeIsBefore(OffsetDateTime.now());
        List<OffsetDateTime> times = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate temp = today;
        int count = 8 - temp.getDayOfWeek().getValue();

        for (int i = 0; i < count; i++) {
            LocalTime time = LocalTime.of(10, 0);
            for (int j = 0; j < 12; j++) {
                times.add(OffsetDateTime.of(today, time, OffsetDateTime.now().getOffset()));
                time = time.plusHours(1);
            }
            today = today.plusDays(1);
        }
        for (var time : times) {
            AvailableTimeEntity entity = new AvailableTimeEntity();
            entity.setTime(time);
            entity.setAvailable(true);
            availableTimeRepository.save(entity);
        }
    }

    @Override
    public boolean isDatabaseEmpty() {
        return availableTimeRepository.count() == 0;
    }
}
