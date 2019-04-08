package org.evanharmon.neighborshare.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
@Transactional
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Size(min=2, max=999)
    private String description;

    @ManyToOne
    private Category category;

    private Boolean available = true;

    @ManyToOne
    private User user;

    private String image = "https://s3.us-west-2.amazonaws.com/neighborshare-images/default.png";

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(int id, @NotNull @Size(min = 2, max = 30) String name, @NotNull @Size(min = 2, max = 999) String description, Category category, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.user = user;
    }

    public Item(){ }

    public int getId() {
        return id;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                '}';
    }

}
