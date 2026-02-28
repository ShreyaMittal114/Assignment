package com.example.assignment.service;

import com.example.assignment.model.Shelf;
import com.example.assignment.model.ShelfPosition;
import com.example.assignment.repository.ShelfPositionRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfPositionService {
    private final ShelfPositionRepo  shelfPositionRepo;

    public ShelfPositionService(ShelfPositionRepo shelfPositionRepo) {
        this.shelfPositionRepo = shelfPositionRepo;
    }

    public List<ShelfPosition> getShelvesPositionByDeviceId(String deviceId) {
        return shelfPositionRepo.getShelvesPositionByDeviceId(deviceId);
    }
}
