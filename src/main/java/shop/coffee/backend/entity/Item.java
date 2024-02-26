package shop.coffee.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    private String itemName;
    private long itemCategory;
    private String itemSize;
    private float price;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Long getItemCategory() {
        return itemCategory;
    }
    public void setItemCategory(Long itemCategory) {
        this.itemCategory = itemCategory;
    }
    public String getItemSize() {
        return itemSize;
    }
    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }   
}
