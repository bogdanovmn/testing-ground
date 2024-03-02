package com.github.bogdanovmn.testingground.springboot.something;

import com.github.bogdanovmn.testingground.springboot.inmemorystat.InMemoryStatistic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/somethings")
@RequiredArgsConstructor
class SomethingController {
    private final SomethingService somethingService;
    private final InMemoryStatistic statistic;

    @GetMapping
    ResponseEntity<Page<SomethingResponse>> getPage(
        @Min(1)
        @RequestParam("page") int page,
        @Min(1)
        @RequestParam(name = "size", required = false, defaultValue = "3") int size
    ) {
        statistic.increment("GET", "page", page);
        return ResponseEntity.ok(
            somethingService.getPage(page, size)
                .map(SomethingResponse::of)
        );
    }

    @GetMapping("{id}")
    ResponseEntity<SomethingResponse> getSomething(@PathVariable("id") long id) {
        statistic.increment("GET", id);
        return ResponseEntity.ok(
            SomethingResponse.of(
                somethingService.get(id)
            )
        );
    }

    @PostMapping
    ResponseEntity<SomethingResponse> createSomething(@RequestBody SomethingPostRequest postRequest) throws Exception {
        statistic.increment("POST");
        return ResponseEntity.ok(
            SomethingResponse.of(
                somethingService.create(postRequest)
            )
        );
    }
}
