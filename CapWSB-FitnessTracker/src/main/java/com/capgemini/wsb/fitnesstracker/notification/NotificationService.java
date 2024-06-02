package com.capgemini.wsb.fitnesstracker.notification;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSupplier;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
import static java.util.Calendar.DAY_OF_MONTH;

@EnableScheduling
@Service
@Data
@Slf4j

class NotificationService {
    private static final String TITLE = "Training report from last week";
    private final UserProvider userProvider;
    private final TrainingProvider trainingProvider;
    private final EmailSupplier emailSupplier;
    private final EmailSender emailSender;

    @Scheduled(cron = "0 0 12 ? * 1")
    public void reportGeneration() {
        log.info("Training reports are getting generated");
        final List<User> allUsers = userProvider.findAllUsers();
        allUsers.forEach(user -> {
            final EmailDto email = emailSupplier.createEmail(user.getEmail(), TITLE, trainingProvider.findAllForUserId(user.getId()));
        log.info("Sending mail to {}", user.getEmail());
        emailSender.send(email);
        });
        log.info("Training reports are finished");
    }
}
