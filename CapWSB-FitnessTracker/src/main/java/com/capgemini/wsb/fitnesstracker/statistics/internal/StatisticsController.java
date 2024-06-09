package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsTO;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/statistics")
public class StatisticsController {
    private final StatisticsServiceImpl statisticsService;

    @PostMapping()
    public StatisticsTO createStatistic(StatisticsTO statisticsTO){
        statisticsService.createNewStatistics(statisticsTO);
        return statisticsTO;
    }
}
