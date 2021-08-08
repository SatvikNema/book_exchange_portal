package com.satvik.bookexchange.controller;

import com.satvik.bookexchange.service.MemcachedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private MemcachedService memcachedService;

    @GetMapping("/setKey")
    public ResponseEntity<?> setKeys(@RequestParam("key") String key,
                                     @RequestParam("value") String value){
        String response = memcachedService.setKey(key, value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testMemCached(){
        String response = memcachedService.testCachingClient();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/setSomeKeys")
    public ResponseEntity<?> setSomeKeys(){
        String response = memcachedService.setSomeKeys();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllKeys")
    public ResponseEntity<?> getAllKeys(){
        List<String> keys = memcachedService.getCachedKeys();
        return new ResponseEntity<>(keys, HttpStatus.OK);
    }

    @GetMapping("/flush")
    public ResponseEntity<String> flush() {
        String response = "all keys flushed!";
        memcachedService.flushData();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{key}")
    public ResponseEntity<Object> getValue(@PathVariable("key") String key) {
        Object response = memcachedService.getValue(key);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
