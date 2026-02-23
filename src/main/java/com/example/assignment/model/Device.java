package com.example.assignment.model;

public class Device {


    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartNumber() {
        return PartNumber;
    }

    public void setPartNumber(String partNumber) {
        PartNumber = partNumber;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String buildingName) {
        BuildingName = buildingName;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public Long getNoOfShelfPositions() {
        return noOfShelfPositions;
    }

    public void setNoOfShelfPositions(Long noOfShelfPositions) {
        this.noOfShelfPositions = noOfShelfPositions;
    }

    private String name;
    private String PartNumber ;

    public Device(String id, String name, String partNumber, String buildingName, String deviceType, Long noOfShelfPositions) {
        this.id = id;
        this.name = name;
        PartNumber = partNumber;
        BuildingName = buildingName;
        DeviceType = deviceType;
        this.noOfShelfPositions = noOfShelfPositions;
    }
  public Device(){}
    private String BuildingName;
    private String DeviceType;
    private Long noOfShelfPositions ;


}
