package com.satvik.bookexchange.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class ReactiveController {

    @GetMapping("/reactive1")
    public Mono<String> test1(){
        return Mono.just("hello from reactive spring!");
    }

    @GetMapping(value = "/reactive2", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> test2(){
        return Flux.just(1, 200, 3, 400, 5, 6, 700).delayElements(Duration.ofSeconds(1L)).log();
    }
}
