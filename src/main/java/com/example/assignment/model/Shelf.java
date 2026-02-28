package com.example.assignment.model;

public class Shelf {

    private String id;
    private String name;
    private Long partNumber;
    private Boolean Deleted;
    private Boolean Occupied;

    public Shelf(String id, String name, Long partNumber, Boolean Deleted, Boolean Occupied) {
        this.id = id;
        this.name = name;
        this.partNumber = partNumber;
        this.Deleted = Deleted;
        this.Occupied = Occupied;
    }

    public Boolean getOccupied() {
        return Occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.Occupied = occupied;
    }

    public Shelf(){

    }

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

    public Long getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Long partNumber) {
        this.partNumber = partNumber;
    }

    public Boolean getDeleted() {
        return Deleted;
    }

    public void setDeleted(Boolean isdeleted) {
        this.Deleted = isdeleted;
    }
}
