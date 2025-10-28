package ru.ryatronth.service.desk.module.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
