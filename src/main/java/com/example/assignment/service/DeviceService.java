package com.example.assignment.service;

import com.example.assignment.model.Device;
import com.example.assignment.repository.DeviceRepo;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class DeviceService {

  private final DeviceRepo deviceRepo;


    public DeviceService(DeviceRepo deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    public String createDevice(Device device) {
        Long neo4jid = deviceRepo.createDevice(device);
       return "device created successfully with Id :"+neo4jid;

    }
}
