package com.example.assignment.service;

import com.example.assignment.model.Device;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class DeviceService {

    private final Driver driver;


    public DeviceService(Driver driver) {
        this.driver = driver;
    }

     public String  createDevice(Device device){

        String generatedId= UUID.randomUUID().toString();
        device.setId(generatedId);

        String query = """
                      MERGE (d:Device{
                     id: $id,
                      name : $name,
                      PartNumber : $PartNumber,
                       BuildingName : $BuildingName,
                       DeviceType : $DeviceType,
                      noOfShelfPositions: $noOfShelfPositions"})
                """;
        try(Session session = driver.session()){
            session.executeWrite((TransactionContext tx)->{
                tx.run(query, Values.parameters(
                        "id",generatedId,
                        "name",device.getName(),
                        "PartNumber",device.getPartNumber(),
                        "BuildingName",device.getBuildingName(),
                        "DeviceType",device.getDeviceType(),
                        "noOfShelfPositions",device.getNoOfShelfPositions()

                ));
                return null;
            });
            return "device created successfully";

        }
        catch (Exception e){
            return "failed to create device";


        }
     }

}
