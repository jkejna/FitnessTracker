package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TONewTraining;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

// TODO: Provide Impl
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;
    private final MapperTraining mapperTraining;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findAllForUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    public Training processTrainingEntity(final TONewTraining toTraining) {
        final User linkedUser = userProvider.getUser(toTraining.getUserId()).get();
        final Training mappedTraining = mapperTraining.toEntity(toTraining);
        mappedTraining.setUser(linkedUser);
        trainingRepository.save(mappedTraining);
        return mappedTraining;
    }

    public List<Training> findByDistance(final double distance) {
        return trainingRepository.findAll().stream().filter(training -> training.getDistance() == distance).toList();
    }
}
