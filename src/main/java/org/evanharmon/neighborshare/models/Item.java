package org.evanharmon.neighborshare.models;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private Long id;

    private String name;

    private Category category;

    private String description;

    private String image;

    private static List<Item> allItems = new ArrayList<>();

    public Item(Long id, String name, Category category, String description, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static List<Item> getAllItems() {
        return allItems;
    }

    public static void addAllItems(Item item) {
        Item.allItems.add(item);
    }
}
