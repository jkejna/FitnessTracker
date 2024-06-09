package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsMapper {
    private UserMapper userMapper;
    public Statistics toEntity(StatisticsTO to) {
        return new Statistics(userMapper.toEntitySave(to.getUser()), to.getTotalTrainings(), to.getTotalDistance(), to.getTotalCaloriesBurned());
    }
}
