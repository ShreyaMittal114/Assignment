package com.example.assignment.model;

public class ShelfPosition {

    String positionId;
    boolean Deleted;
    boolean Occupied;

    public ShelfPosition( String positionId, boolean Deleted, boolean Occupied) {

        this.positionId = positionId;
        this.Deleted = Deleted;
        this.Occupied = Occupied;
    }

    public ShelfPosition() {

    }



    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public boolean getDeleted() {
        return Deleted;
    }

    public void setDeleted(boolean Deleted) {
        this.Deleted = Deleted;
    }

    public boolean getOccupied() {
        return Occupied;
    }

    public void setOccupied(boolean Occupied) {
        this.Occupied = Occupied;
    }
}
