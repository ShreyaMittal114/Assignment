package com.example.assignment.service;

import com.example.assignment.model.Device;
import com.example.assignment.repository.DeviceRepo;
import com.example.assignment.repository.ShelfPositionRepo;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class DeviceService {

  private final DeviceRepo deviceRepo;
  private final ShelfPositionRepo shelfPositionRepo;


    public DeviceService(DeviceRepo deviceRepo, ShelfPositionRepo shelfPositionRepo) {
        this.deviceRepo = deviceRepo;
        this.shelfPositionRepo = shelfPositionRepo;
    }

    public String createDevice(Device device) {
       String deviceid = deviceRepo.createDevice(device);
       shelfPositionRepo.createShelves(deviceid,device.getNoOfShelfPositions());
       return "device created successfully with Id :"+deviceid;

    }

    public Device getById(String id) {
        return deviceRepo.getById(id);
    }

    public List<Device> getAllDevice() {
        return deviceRepo.getAllDevices();
    }

    public String  updateDevice(String id, Device device) {
        return deviceRepo.updateDevice(id,device);
    }

    public List<Device> searchDevices(String name, String type,String buildingName) {
        return deviceRepo. searchDevices(name,type,buildingName);
    }
}
