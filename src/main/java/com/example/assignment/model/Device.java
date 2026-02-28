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
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Long getNoOfShelfPositions() {
        return noOfShelfPositions;
    }

    public void setNoOfShelfPositions(Long noOfShelfPositions) {
        this.noOfShelfPositions = noOfShelfPositions;
    }

    private String name;
    private String partNumber ;



    private String buildingName;
    private String deviceType;
    private Long noOfShelfPositions ;
    private Boolean Deleted;

    public Device(String id, String name, String partNumber, String buildingName, String deviceType, Long noOfShelfPositions, Boolean Deleted) {
        this.id = id;
        this.name = name;
        this.partNumber = partNumber;
        this.buildingName = buildingName;
        this.deviceType = deviceType;
        this.noOfShelfPositions = noOfShelfPositions;
        this.Deleted = Deleted;
    }

    public Boolean getDeleted() {
        return Deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.Deleted = deleted;
    }

    public Device(){}


}
