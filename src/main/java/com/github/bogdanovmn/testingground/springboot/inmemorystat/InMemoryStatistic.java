package com.github.bogdanovmn.testingground.springboot.inmemorystat;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InMemoryStatistic {
    private static final String ROOT_KEY = "http-call";

    private final HashOperations<String, String, Integer> hashOps;

    public InMemoryStatistic(RedisTemplate<String, Object> redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
    }

    public void increment(Object... args) {
        hashOps.increment(ROOT_KEY, computeKey(args), 1);
    }

    private String computeKey(Object... args) {
        return Arrays.stream(args).map(Object::toString)
            .collect(Collectors.joining("-"));
    }

    List<Statistic> get() {
        return hashOps.entries(ROOT_KEY).entrySet().stream()
            .map(
                entry -> new Statistic(
                    entry.getKey(),
                    entry.getValue()
                )
            ).sorted(
                Comparator.comparingInt(Statistic::getCounter).reversed()
                    .thenComparing(Statistic::getKey)
            ).collect(Collectors.toList());
    }
}
