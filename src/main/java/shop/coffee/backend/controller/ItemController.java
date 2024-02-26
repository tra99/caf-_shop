package shop.coffee.backend.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.coffee.backend.entity.Item;
import shop.coffee.backend.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService=itemService;
    }

    @GetMapping("/getAllItem")
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> items=itemService.getAllItems();
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable long id){
        Optional<Item> item=itemService.getProductById(id);
        return item.map(value -> new ResponseEntity<>(value,HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/addItem")
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item createdItem=itemService.createOrUpdateItem(item);
        return new ResponseEntity<>(createdItem,HttpStatus.CREATED);
    }

    @PutMapping("/updateItem")
    public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item updatedItem){
        Optional<Item> existingItemOptional=itemService.getProductById(id);

        if(existingItemOptional.isPresent()){
            Item existingItem=existingItemOptional.get();
            existingItem.setItemName(updatedItem.getItemName());

            Item saveItem=itemService.createOrUpdateItem(existingItem);
            return new ResponseEntity<>(saveItem,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
