package com.capgemini.wsb.fitnesstracker.mail.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import java.util.List;

public interface EmailSupplier {
    EmailDto createEmail(final String recipient, final String title, final List<Training> trainings);
}
