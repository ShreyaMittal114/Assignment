package com.example.assignment.controller;

import com.example.assignment.model.Device;
import com.example.assignment.model.Shelf;
import com.example.assignment.model.ShelfPosition;
import com.example.assignment.service.DeviceService;
import com.example.assignment.service.ShelfPositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;
    private final ShelfPositionService shelfPositionService;

    public DeviceController(DeviceService deviceService, ShelfPositionService shelfPositionService) {
        this.deviceService = deviceService;
        this.shelfPositionService = shelfPositionService;
    }

    @PostMapping("/create")
    public String createDevice(@RequestBody Device device){

        return deviceService.createDevice(device);
    }

    @GetMapping("/{id}")
    public Device getDevice(@PathVariable String id) {
        return deviceService.getById(id);
    }

    @GetMapping("/all")
    public List<Device> getAllDevice(){
        return deviceService.getAllDevice();
    }

    @GetMapping
    public List<Device> getDevices(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String buildingName
    ) {

        return deviceService.searchDevices(name, type,buildingName);
    }


    @PutMapping("/{id}")
    public String  UpdateDevice(@PathVariable String id , @RequestBody Device device){
        return deviceService.updateDevice(id,device);
    }

    @GetMapping("/{deviceId}/shelves")
    public List<ShelfPosition>getShelvesByDeviceId(@PathVariable String deviceId){
        return shelfPositionService.getShelvesByDeviceId(deviceId);

    }





}
