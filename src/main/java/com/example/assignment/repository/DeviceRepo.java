package com.example.assignment.repository;

import com.example.assignment.model.Device;
import org.neo4j.driver.*;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceRepo {

    private final Driver driver;

    public DeviceRepo(Driver driver) {
        this.driver = driver;
    }

    public Long createDevice(Device device) {

        String query = """
            CREATE (d:Device {
                name: $name,
                partNumber: $partNumber,
                buildingName: $buildingName,
                deviceType: $deviceType,
                noOfShelfPositions: $noOfShelfPositions
            })
            RETURN id(d) AS nodeId
        """;

        try (Session session = driver.session()) {

            return session.executeWrite(tx -> {
                Result result = tx.run(query, Values.parameters(
                        "name", device.getName(),
                        "partNumber", device.getPartNumber(),
                        "buildingName", device.getBuildingName(),
                        "deviceType", device.getDeviceType(),
                        "noOfShelfPositions", device.getNoOfShelfPositions()
                ));

                return result.single().get("nodeId").asLong();
            });
        }
    }
}