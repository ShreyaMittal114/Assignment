package com.example.assignment.repository;

import com.example.assignment.Exception.DeviceNotFound;
import com.example.assignment.model.Device;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DeviceRepo {

    private final Driver driver;

    public DeviceRepo(Driver driver) {
        this.driver = driver;
    }

    public String createDevice(Device device) {

        String query = """
            CREATE (d:Device {
                name: $name,
                partNumber: $partNumber,
                buildingName: $buildingName,
                deviceType: $deviceType,
                noOfShelfPositions: $noOfShelfPositions
            })
            RETURN elementId(d) AS nodeId
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

                return result.single().get("nodeId").asString();
            });
        }
    }

    public Device getById(String id) {
        String query = """
                MATCH(d:Device)
                WHERE elementId(d)=$id
                RETURN d
                """;

        try(Session session = driver.session()){
            return session.executeRead(tx->{
                Result result= tx.run(query,Values.parameters("id",id));
                if(!result.hasNext()){
                    throw new DeviceNotFound("There is no device with this id");
                }

                Record record= result.single();
                Node node = record.get("d").asNode();
                Device device = new Device();
                device.setId(node.elementId());
                device.setName(node.get("name").asString());
                device.setPartNumber(node.get("partNumber").asString());
                device.setBuildingName(node.get("buildingName").asString());
                device.setDeviceType(node.get("deviceType").asString());
                device.setNoOfShelfPositions(node.get("noOfShelfPositions").asLong());
                return device;
            });

        }


    }

    public List<Device> getAllDevices() {
        String query = """
                MATCH(d:Device)
                RETURN d
                """;

        try(Session session = driver.session()){
            return session.executeRead(tx-> {
                Result result = tx.run(query);

                List<Device> devices = new ArrayList<>();
                while (result.hasNext()) {
                    Record record = result.next();
                    Node node = record.get("d").asNode();
                    Device device = new Device();
                    device.setId(node.elementId());
                    device.setName(node.get("name").asString());
                    device.setPartNumber(node.get("partNumber").asString());
                    device.setBuildingName(node.get("buildingName").asString());
                    device.setDeviceType(node.get("deviceType").asString());
                    device.setNoOfShelfPositions(node.get("noOfShelfPositions").asLong());
                    devices.add(device);
                }
                return devices;
            });
        }

    }

    public String updateDevice(String id, Device device) {
        String query = """
                MATCH(d:Device)
                WHERE elementId(d)=$id
                SET d.name = $name,
                     d.partNumber = $partNumber,
                     d.buildingName= $buildingName,
                     d.deviceType = $deviceType,
                     d.noOfShelfPositions = $noOfShelfPositions 
                RETURN d
                """;

        try (Session session = driver.session()) {
            return session.executeWrite(tx -> {
                Result result = tx.run(query, Values.parameters(
                        "id",id,
                        "name", device.getName(),
                        "partNumber", device.getPartNumber(),
                        "buildingName", device.getBuildingName(),
                        "deviceType", device.getDeviceType(),
                        "noOfShelfPositions", device.getNoOfShelfPositions()
                ));
                if (!result.hasNext()) {
                    throw new DeviceNotFound("There is no device with this id");
                }

                return "Device updated Successfully";


            });


        }
    }

    public List<Device> searchDevices(String name, String type,String buildingName) {

        StringBuilder query = new StringBuilder("MATCH (d:Device)");

        Map<String, Object> params = new HashMap<>();

        if (name != null || type != null || buildingName!=null) {
            query.append(" WHERE ");

            List<String> conditions = new ArrayList<>();

            if (name != null) {
                conditions.add("d.name = $name");
                params.put("name", name);
            }

            if (type != null) {
                conditions.add("d.deviceType = $type");
                params.put("type", type);
            }
            if (buildingName != null) {
                conditions.add("d.buildingName = $buildingName");
                params.put("buildingName", buildingName);
            }



            query.append(String.join(" AND ", conditions));
        }
        query.append(" RETURN d");

        try (Session session = driver.session()) {
            return session.executeRead(tx -> {

                Result result = tx.run(query.toString(), params);
                List<Device> devices = new ArrayList<>();
               if(!result.hasNext()){
                   throw new DeviceNotFound("No device found");
               }
                while (result.hasNext()) {
                    Record record = result.next();
                    Node node = record.get("d").asNode();

                    Device device = new Device();
                    device.setId(node.elementId());
                    device.setName(node.get("name").asString());
                    device.setPartNumber(node.get("partNumber").asString());
                    device.setBuildingName(node.get("buildingName").asString());
                    device.setDeviceType(node.get("deviceType").asString());
                    device.setNoOfShelfPositions(node.get("noOfShelfPositions").asLong());

                    devices.add(device);
                }

                return devices;
            });
        }
    }
}