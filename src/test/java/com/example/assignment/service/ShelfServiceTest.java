package com.example.assignment.service;

import com.example.assignment.model.Device;
import com.example.assignment.model.Shelf;
import com.example.assignment.repository.ShelfRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShelfServiceTest {

    @Mock
    private ShelfRepo shelfRepo;

    @InjectMocks
    private ShelfService shelfService;

    @Test
    void testCreateShelf() {

        Shelf shelf = new Shelf();
        shelf.setName("Test Shelf");


        when(shelfRepo.createShelf(any(Shelf.class))).thenReturn("shelf created successfully");


        String result = shelfService.createShelf(shelf);


        assertNotNull(result);
        assertTrue(result.contains("shelf created successfully"));


        verify(shelfRepo, times(1)).createShelf(any(Shelf.class));
    }

    @Test
    void testUpdateShelf() {

        Shelf shelf = new Shelf();
        shelf.setName("Test Shelf");

        when(shelfRepo.UpdateShelf(anyString(),any(Shelf.class))).thenReturn("shelf updated successfully");

        String result = shelfService.UpdateShelf("shelf id",shelf);

        assertNotNull(result);
        assertEquals("shelf updated successfully",result);

        verify(shelfRepo, times(1)).UpdateShelf(anyString(),any(Shelf.class));
    }


    @Test
    void testDeleteShelf() {

        String shelfId = "1";

        when(shelfRepo.deleteShelf(anyString()))
                .thenReturn("shelf deleted successfully");

        String result = shelfService.deleteShelf(shelfId);

        assertEquals("shelf deleted successfully", result);

        verify(shelfRepo, times(1)).deleteShelf(anyString());
    }

    @Test
    void testGetAllShelves() {

        Shelf s1 = new Shelf();
        s1.setName("Router");

        Shelf s2 = new Shelf();
        s2.setName("Switch");

        List<Shelf> shelfList = List.of(s1, s2);

        when(shelfRepo.getAllShelf())
                .thenReturn(shelfList);

        List<Shelf> result = shelfService.getAllShelf();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(shelfRepo, times(1))
                .getAllShelf();
    }

    @Test
    void testGetShelfById() {

       Shelf s= new Shelf();
       s.setId("1");
       s.setName("Cd");

        when(shelfRepo.getShelfById(anyString())).thenReturn(s);

        Shelf result = shelfService.getShelfById("1");

        assertNotNull(result);
        assertEquals("Cd", result.getName());

        verify(shelfRepo, times(1)).getShelfById(anyString());
    }

    @Test
    void testOccupyShelf() {

        String shelfId = "s1";
        String shelfPositionId = "p1";

        when(shelfRepo.occupyShelf(shelfId, shelfPositionId))
                .thenReturn("Shelf occupied successfully");

        String result = shelfService.occupyShelf(shelfId, shelfPositionId);

        assertNotNull(result);
        assertEquals("Shelf occupied successfully", result);

        verify(shelfRepo, times(1)).occupyShelf(shelfId, shelfPositionId);
    }
}
