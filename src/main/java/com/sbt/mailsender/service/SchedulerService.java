package com.sbt.mailsender.service;

import com.sbt.mailsender.api.EmailService;
import com.sbt.mailsender.api.UserRepositoryService;
import com.sbt.mailsender.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class SchedulerService {

    private final UserRepositoryService userRepositoryService;

    private final EmailService emailService;

    private static final String CRON = "*/10 * * * * *";

    @Autowired
    public SchedulerService(UserRepositoryService userRepositoryService, EmailService emailService) {
        this.userRepositoryService = userRepositoryService;
        this.emailService = emailService;
    }

    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<User> users = userRepositoryService.getAllUsers();

        if (!users.isEmpty()) {
            users.forEach(user -> {
                try {
                    String message = "Happy Birthday dear " + user.getName() + "!";
                    emailService.send(user.getEmail(), "Happy Birthday!", message);
                    log.info("Email have been sent. User id : {},  Date : {} ", user.getId(), date);
                } catch (Exception e) {
                    log.error("Email can't be sent.User's id: {}, Error: {}", user.getId(), e.getMessage());
                    log.error("Email can't be sent", e);
                }
            });
        }

    }
}
