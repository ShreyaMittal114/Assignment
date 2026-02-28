package com.example.assignment.service;

import com.example.assignment.model.Shelf;
import com.example.assignment.repository.ShelfRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfService {

    private final ShelfRepo  shelfRepo;

    public ShelfService(ShelfRepo shelfRepo) {
        this.shelfRepo = shelfRepo;
    }

    public String createShelf(Shelf shelf) {
        return shelfRepo.createShelf(shelf);
    }

    public String occupyShelf(String shelfId, String shelfPositionId) {
        return shelfRepo.occupyShelf(shelfId,shelfPositionId);
    }

    public Shelf getShelfById(String shelfId) {
        return shelfRepo.getShelfById(shelfId);
    }

    public List<Shelf> getAllShelf() {
        return shelfRepo.getAllShelf();
    }

    public String UpdateShelf(String shelfId, Shelf shelf) {
        return shelfRepo.UpdateShelf(shelfId, shelf);
    }

    public String deleteShelf(String id) {
        return shelfRepo.deleteShelf(id);
    }

//    public Shelf getShelfByName(String name) {
//        return shelfRepo.getByName(name);
//    }
}
