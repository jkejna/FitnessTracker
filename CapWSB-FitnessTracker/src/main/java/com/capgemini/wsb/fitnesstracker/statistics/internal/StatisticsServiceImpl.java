package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsMapper;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class StatisticsServiceImpl {
    private final StatisticsMapper statisticsMapper;
    private final StatisticsRepository statisticsRepository;
    public void createNewStatistics(StatisticsTO statisticsTO){
        statisticsRepository.save(statisticsMapper.toEntity(statisticsTO));
    }
}
