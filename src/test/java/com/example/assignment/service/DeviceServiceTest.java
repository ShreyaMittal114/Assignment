package com.example.assignment.service;

import com.example.assignment.model.Device;
import com.example.assignment.repository.DeviceRepo;
import com.example.assignment.repository.ShelfPositionRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension; // Correct extension import

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepo deviceRepo;
    @Mock
    private ShelfPositionRepo shelfPositionRepo;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    void testCreateDevice() {

        Device device = new Device();
        device.setName("Test Device");


        when(deviceRepo.createDevice(any(Device.class))).thenReturn("device created successfully");
        when(shelfPositionRepo.createShelves(anyString(),any())).thenReturn(null);

        String result = deviceService.createDevice(device);


        assertNotNull(result);
        assertTrue(result.contains("device created successfully"));


        verify(deviceRepo, times(1)).createDevice(any(Device.class));
    }

    @Test
    void testUpdateDevice() {

        Device device = new Device();

        device.setName("Updated Device");

        when(deviceRepo.updateDevice(anyString(),any(Device.class))).thenReturn("device updated successfully");

        String result = deviceService.updateDevice("device id",device);

        assertNotNull(result);
        assertEquals("device updated successfully",result);

        verify(deviceRepo, times(1)).updateDevice(anyString(),any(Device.class));
    }

    @Test
    void testDeleteDevice() {

        String deviceId = "1";

        when(deviceRepo.deleteDevice(anyString()))
                .thenReturn("Device deleted successfully");

        String result = deviceService.deleteDevice(deviceId);

        assertEquals("Device deleted successfully", result);

        verify(deviceRepo, times(1)).deleteDevice(anyString());
    }

    @Test
    void testGetAllDevices() {

        Device d1 = new Device();
        d1.setName("Router");

        Device d2 = new Device();
        d2.setName("Switch");

        List<Device> deviceList = List.of(d1, d2);

        when(deviceRepo.getAllDevices())
                .thenReturn(deviceList);

        List<Device> result = deviceService.getAllDevice();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(deviceRepo, times(1))
                .getAllDevices();
    }
    @Test
    void testGetDeviceById() {

        Device device = new Device();
        device.setId("1");
        device.setName("Router");

        when(deviceRepo.getById(anyString())).thenReturn(device);

        Device result = deviceService.getById("1");

        assertNotNull(result);
        assertEquals("Router", result.getName());

        verify(deviceRepo, times(1)).getById(anyString());
    }

}