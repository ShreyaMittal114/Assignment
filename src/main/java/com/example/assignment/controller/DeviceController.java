package com.example.assignment.controller;

import com.example.assignment.model.Device;
import com.example.assignment.service.DeviceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/create")
    public String createDevice(@RequestBody Device device){

        return deviceService.createDevice(device);
    }
}
