package org.evanharmon.neighborshare.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

public class Group {

    private Integer id;

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Size(min=2, max=280)
    private String description;

    private ArrayList<User> users = new ArrayList<>();

    // Prototyping
    private static ArrayList<Group> allGroups = new ArrayList<>();
    private static Integer nextId = 1;

    public Group(String name, String description) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Group() {
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + users +
                '}';
    }

    // Prototyping
    public static ArrayList<Group> getAllGroups() {
        return allGroups;
    }

    public static void addAllGroups(Group group) {
        Group.allGroups.add(group);
    }
}
