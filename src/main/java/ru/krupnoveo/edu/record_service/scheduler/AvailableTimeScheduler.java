package ru.krupnoveo.edu.record_service.scheduler;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.krupnoveo.edu.record_service.service.AvailableTimeService;

@Component
public class AvailableTimeScheduler {

    private final AvailableTimeService availableTimeService;

    public AvailableTimeScheduler(AvailableTimeService availableTimeService) {
        this.availableTimeService = availableTimeService;
        if (availableTimeService.isDatabaseEmpty()) {
            updateAvailableTime();
        }
    }

    @Scheduled(cron = "0 0 0 */7 * *")
    public void updateAvailableTime() {
        availableTimeService.updateAvailableTimes();
    }
}
