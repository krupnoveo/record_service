package ru.krupnoveo.edu.record_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krupnoveo.edu.record_service.api.client.BarbershopClient;
import ru.krupnoveo.edu.record_service.api.client.UserClient;
import ru.krupnoveo.edu.record_service.api.dto.request.ChangeTimeRecordRequest;
import ru.krupnoveo.edu.record_service.api.dto.request.CreateRecordRequest;
import ru.krupnoveo.edu.record_service.api.dto.response.*;
import ru.krupnoveo.edu.record_service.api.exception.AvailableTimeNotFoundException;
import ru.krupnoveo.edu.record_service.api.exception.RecordNotFoundException;
import ru.krupnoveo.edu.record_service.domain.entity.AvailableTimeEntity;
import ru.krupnoveo.edu.record_service.domain.entity.RecordEntity;
import ru.krupnoveo.edu.record_service.domain.repository.JpaAvailableTimeRepository;
import ru.krupnoveo.edu.record_service.domain.repository.JpaRecordRepository;
import ru.krupnoveo.edu.record_service.service.RecordService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordServiceImpl implements RecordService {

    private final JpaRecordRepository recordRepository;
    private final JpaAvailableTimeRepository availableTimeRepository;

    private final UserClient userClient;

    private final BarbershopClient barbershopClient;

    @Override
    public RecordResponse getRecordById(UUID id) {
        RecordEntity record = getRecord(id);
        ClientInfoResponse clientInfoResponse = userClient.getClientInfo(record.getClientId());
        BarberInfoResponse barberInfoResponse = userClient.getBarberInfo(record.getBarberId());
        ServiceInfoResponse serviceInfoResponse = barbershopClient.getServiceInfo(record.getServiceId(), barberInfoResponse.grade());
        BarbershopInfoResponse barbershopInfoResponse = barbershopClient.getBarbershopInfo(record.getBarbershopId());
        return new RecordResponse(
                record.getId(),
                record.getClientId(),
                clientInfoResponse.firstName(),
                record.getBarberId(),
                barberInfoResponse.firstName(),
                barberInfoResponse.grade(),
                record.getTimeId().getTime(),
                record.getBarbershopId(),
                barbershopInfoResponse.address(),
                barbershopInfoResponse.phoneNumber(),
                record.getServiceId(),
                serviceInfoResponse.name(),
                serviceInfoResponse.price()
        );
    }

    @Override
    public List<RecordResponse> getAllRecords(String token, UUID barbershopId) {
        List<RecordEntity> records;
        var user = userClient.getClientInfo(token);

        if (user.role().equals("BARBER")) {
            records = recordRepository.findAllByBarberId(user.id());
        } else if (user.role().equals("CLIENT")) {
            records = recordRepository.findAllByClientId(user.id());
        } else  {
            if (barbershopId != null) {
                records = recordRepository.findAllByBarbershopId(barbershopId);
            } else {
                records = recordRepository.findAll();
            }
        }

        return records.stream().map(a -> getRecordById(a.getId())).toList();
    }

    @Override
    public RecordResponse deleteRecordById(UUID id) {
        RecordEntity record = getRecord(id);
        RecordResponse response = getRecordById(id);
        recordRepository.delete(record);
        return response;
    }

    @Override
    public RecordResponse createRecord(CreateRecordRequest request, String token) {
        RecordEntity entity = new RecordEntity();
        if (request.clientId() != null) {
            entity.setClientId(request.clientId());
        } else {
            entity.setClientId(userClient.getClientId(token));
        }
        entity.setBarberId(request.barberId());
        entity.setBarbershopId(request.barbershopId());
        entity.setServiceId(request.serviceId());
        AvailableTimeEntity availableTime = getAvailableTime(request.timeId());
        entity.setTimeId(availableTime);
        recordRepository.save(entity);
        return getRecordById(entity.getId());
    }

    @Override
    public RecordResponse updateRecordTime(UUID id, ChangeTimeRecordRequest request) {
        RecordEntity record = getRecord(id);
        AvailableTimeEntity availableTime = getAvailableTime(request.time());
        record.setTimeId(availableTime);
        recordRepository.save(record);
        return getRecordById(id);
    }

    private RecordEntity getRecord(UUID id) {
        Optional<RecordEntity> record = recordRepository.findById(id);
        return record.orElseThrow(() -> new RecordNotFoundException(id));
    }

    private AvailableTimeEntity getAvailableTime(UUID id) {
        Optional<AvailableTimeEntity> availableTime = availableTimeRepository.findById(id);
        return availableTime.orElseThrow(() -> new AvailableTimeNotFoundException(id));
    }
}
