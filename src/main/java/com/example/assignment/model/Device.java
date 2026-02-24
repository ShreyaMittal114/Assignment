package com.example.assignment.model;

public class Device {


    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        partNumber = partNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        buildingName = buildingName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        deviceType = deviceType;
    }

    public Long getNoOfShelfPositions() {
        return noOfShelfPositions;
    }

    public void setNoOfShelfPositions(Long noOfShelfPositions) {
        this.noOfShelfPositions = noOfShelfPositions;
    }

    private String name;
    private String partNumber ;

    public Device(Long id, String name, String partNumber, String buildingName, String deviceType, Long noOfShelfPositions) {
        this.id = id;
        this.name = name;
        this.partNumber = partNumber;
       this.buildingName = buildingName;
        this.deviceType = deviceType;
        this.noOfShelfPositions = noOfShelfPositions;
    }
  public Device(){}
    private String buildingName;
    private String deviceType;
    private Long noOfShelfPositions ;


}
