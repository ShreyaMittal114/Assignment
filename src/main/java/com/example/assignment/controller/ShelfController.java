package com.example.assignment.controller;

import com.example.assignment.model.Shelf;
import com.example.assignment.service.ShelfService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

    private final ShelfService shelfService;

    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @PostMapping("/create")
    public String createShelf(@RequestBody Shelf shelf){
        return shelfService.createShelf(shelf);
    }

     @GetMapping("/{shelfId}")
     public Shelf getShelfById(@PathVariable String shelfId){
        return shelfService.getShelfById(shelfId);
     }
//    @GetMapping("/{name}")
//    public Shelf getShelfByName(@PathVariable String name){
//        return shelfService.getShelfByName(name);
//    }

     @GetMapping("/all")
     public List<Shelf> getAllShelf(){
        return  shelfService.getAllShelf();
     }
    //Attaching shelf to shelf position
    @PutMapping("/occupy")
    public String occupyShelf(@RequestParam String shelfId, @RequestParam String shelfPositionId){
       return  shelfService.occupyShelf(shelfId,shelfPositionId);

    }

    @PutMapping("/{id}/update")
    public String UpdateShelf(@PathVariable String id,@RequestBody Shelf shelf){
        return shelfService.UpdateShelf(id,shelf);
    }



}
