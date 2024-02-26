package shop.coffee.backend.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;
import shop.coffee.backend.entity.Item;
import shop.coffee.backend.repository.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Optional<Item> getProductById(long id){
        return itemRepository.findById(id);
    }

    public Item createOrUpdateItem(Item item){
        return itemRepository.save(item);
    }

    public void deleteItemById(long id){
        itemRepository.deleteById(id);
    }
}
