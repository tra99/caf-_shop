package shop.coffee.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.coffee.backend.entity.Item;
import shop.coffee.backend.service.ItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "*")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable long id) {
        Optional<Item> item = itemService.getItemById(id);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createOrUpdateItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item updateItem) {
        Optional<Item> existingItemOptional = itemService.getItemById(id);

        if (existingItemOptional.isPresent()) {
            Item existingItem = existingItemOptional.get();
            existingItem.setItemName(updateItem.getItemName());
            existingItem.setCategory(updateItem.getCategory());

            Item savedItem = itemService.createOrUpdateItem(existingItem);
            return new ResponseEntity<>(savedItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable long id) {
        Optional<Item> item = itemService.getItemById(id);

        if (item.isPresent()) {
            itemService.deleteItemById(id);
            String message = "Item with ID " + id + " is deleted";
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        } else {
            String message = "Item with ID " + id + " is not found";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
