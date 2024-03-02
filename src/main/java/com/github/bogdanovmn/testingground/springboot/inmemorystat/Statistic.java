package com.github.bogdanovmn.testingground.springboot.inmemorystat;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
class Statistic {
    String key;
    Integer counter;
}
