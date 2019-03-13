package org.evanharmon.neighborshare.models;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private Integer id;

    private String name;

    private String description;

    private String image;

    private Category category;

    private User owner;

    // Prototyping
    private static List<Item> allItems = new ArrayList<>();

    public Item(Integer id, String name, String description, String image, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category=" + category +
                ", owner=" + owner +
                '}';
    }

    // Prototyping
    public static List<Item> getAllItems() {
        return allItems;
    }

    public static void addAllItems(Item item) {
        Item.allItems.add(item);
    }
}
