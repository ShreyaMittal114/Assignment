package com.example.assignment.controller;

import com.example.assignment.service.DriverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final DriverService driverService;

    public TestController(DriverService deviceService) {
        this.driverService = deviceService;
    }

    @GetMapping("/test")
    public String testconnection(){
        return driverService.testConnection();
    }

}
