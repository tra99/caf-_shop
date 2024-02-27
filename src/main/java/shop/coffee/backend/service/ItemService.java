package shop.coffee.backend.service;

import org.springframework.stereotype.Service;
import shop.coffee.backend.entity.Item;
import shop.coffee.backend.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(long id) {
        return itemRepository.findById(id);
    }

    public Item createOrUpdateItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItemById(long id) {
        itemRepository.deleteById(id);
    }
}
