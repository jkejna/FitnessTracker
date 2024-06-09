package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TONewTraining;
import com.capgemini.wsb.fitnesstracker.training.api.TOTraining;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping
@RequiredArgsConstructor

class ControllerTraining {
    private final TrainingProvider trainingProvider;
    private final MapperTraining mapperTraining;
    private final TrainingRepository trainingRepository;
    private final TrainingServiceImpl trainingService;
    @GetMapping
    public List<TOTraining> getTrainings() {
        return trainingProvider.getTrainings().stream().map(mapperTraining::toTraining).collect(toList());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public TOTraining addTraining(@RequestBody TONewTraining toTraining){
        final Training savedEntity = trainingService.processTrainingEntity(toTraining);
        return mapperTraining.toTraining(savedEntity);
    }
    @GetMapping
    public List<TOTraining> getTrainingsForUser(@PathVariable("userId")final Long userId) {
        return trainingRepository.findByUserId(userId).stream().map(mapperTraining::toTraining).collect(toList());
    }
    @GetMapping
    public List<TOTraining> getTrainingsForUser(@PathVariable("afterTime") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date endDate) {
        return trainingRepository.findByEndTimeAfter(endDate).stream().map(mapperTraining::toTraining).collect(toList());
    }
    @GetMapping
    public List<TOTraining> getTrainingsForActivityType(@RequestParam("activityType") final ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType).stream().map(mapperTraining::toTraining).collect(toList());
    }

    @PutMapping
    public TOTraining trainingUpdate(@PathVariable Long trainingId, @RequestBody TONewTraining toTraining) {
        Training mappedEntity = mapperTraining.toEntityUpdate(toTraining);
        mappedEntity.setId(trainingId);
        trainingRepository.save(mappedEntity);
        return mapperTraining.toTraining(mappedEntity);
    }

    @GetMapping("/distance/(distance)")
    public List<TOTraining> getByDistance(@PathVariable("distance")double distance) {
        return trainingService.findByDistance(distance).stream().map(mapperTraining::toTraining).collect(toList());
    }
}
