package com.example.assignment.service;

import com.example.assignment.model.ShelfPosition;
import com.example.assignment.repository.ShelfPositionRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ShelfPositionServiceTest {

    @Mock
    private ShelfPositionRepo  shelfPositionRepo;

    @InjectMocks
    private ShelfPositionService  shelfPositionService;


    @Test
    void  testgetShelvesPositionByDeviceId() {

        List<ShelfPosition> positions = List.of(new ShelfPosition(), new ShelfPosition());

        when(shelfPositionRepo.getShelvesPositionByDeviceId(anyString()))
                .thenReturn(positions);

        List<ShelfPosition> result =
                shelfPositionService.getShelvesPositionByDeviceId("1");

        assertEquals(2, result.size());

        verify(shelfPositionRepo, times(1))
                .getShelvesPositionByDeviceId(anyString());


    }

}
