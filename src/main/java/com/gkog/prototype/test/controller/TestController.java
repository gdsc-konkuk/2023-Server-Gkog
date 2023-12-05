package com.gkog.prototype.test.controller;


import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @GetMapping()
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("hello world");
    }

}
