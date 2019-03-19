package org.evanharmon.neighborshare.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Item {

    private Integer id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Size(min=2, max=280)
    private String description;

    @NotNull
    private Category category;

    private User owner;

    private String image;

    // Prototyping
    private static ArrayList<Item> allItems = new ArrayList<>();
    private static Integer nextId = 1;

    public Item(String name, String description, Category category) {
        this();
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Item(){
        this.id = nextId;
        nextId++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
    public static ArrayList<Item> getAllItems() {
        return allItems;
    }

    public static void addAllItems(Item item) {
        Item.allItems.add(item);
    }
}
