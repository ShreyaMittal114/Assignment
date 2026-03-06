package com.example.assignment.model;

public class ShelfPosition {

    String positionId;
    boolean Deleted;
    boolean Occupied;

    private Shelf shelf;

    public boolean isDeleted() {
        return Deleted;
    }

    public boolean isOccupied() {
        return Occupied;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public ShelfPosition(String positionId, boolean deleted, boolean occupied, Shelf shelf) {
        this.positionId = positionId;
        Deleted = deleted;
        Occupied = occupied;
        this.shelf = shelf;
    }

    public ShelfPosition(String positionId, boolean Deleted, boolean Occupied) {

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
