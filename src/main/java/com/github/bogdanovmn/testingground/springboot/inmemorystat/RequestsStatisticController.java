package com.github.bogdanovmn.testingground.springboot.inmemorystat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/request-statistic")
@RequiredArgsConstructor
class RequestsStatisticController {
    private final InMemoryStatistic statistic;

    @GetMapping
    List<Statistic> getStatistic() {
        statistic.increment("GET", "statistic");
        return statistic.get();
    }
}
