package com.example.assignment.model;

public class ShelfPosition {

    String positionId;
    boolean isDeleted;
    boolean isOccupied;

    public ShelfPosition( String positionId, boolean isDeleted, boolean isOccupied) {

        this.positionId = positionId;
        this.isDeleted = isDeleted;
        this.isOccupied = isOccupied;
    }

    public ShelfPosition() {

    }



    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public boolean getisDeleted() {
        return isDeleted;
    }

    public void setisDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public boolean getisOccupied() {
        return isOccupied;
    }

    public void setisOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }
}
