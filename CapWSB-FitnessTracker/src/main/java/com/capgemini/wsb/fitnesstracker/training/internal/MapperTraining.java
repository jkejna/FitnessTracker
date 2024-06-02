package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.training.api.TONewTraining;
import com.capgemini.wsb.fitnesstracker.training.api.TOTraining;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class MapperTraining {
    private final UserMapper userMapper;
    private final UserProvider userProvider;
    TOTraining toTraining(Training training) {
        return new TOTraining(training.getId(),
        userMapper.toDto(training.getUser()),
        training.getStartTime(),
        training.getEndTime(),
        training.getActivityType(),
        training.getDistance(),
        training.getAverageSpeed());
    }

    Training toEntity(TONewTraining toTraining) {
        return new Training(
                toTraining.getStartTime(),
                toTraining.getEndTime(),
                toTraining.getActivityType(),
                toTraining.getDistance(),
                toTraining.getAverageSpeed());
    }

    Training toEntityUpdate(TONewTraining toTraining) {
        return new Training(toTraining.getId(),
                userMapper.toEntitySave(userMapper.toDto(userProvider.getUser(toTraining.getUserId()).get())),
                toTraining.getStartTime(),
                toTraining.getEndTime(),
                toTraining.getActivityType(),
                toTraining.getDistance(),
                toTraining.getAverageSpeed());
    }
}
