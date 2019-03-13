package org.evanharmon.neighborshare.models;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private Integer id;

    private String name;

    private String description;

    private List<User> users = new ArrayList<>();

    // Prototyping
    private static List<Group> allGroups = new ArrayList<>();

    public Group(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public List<User> getUsers() {
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
    public static List<Group> getAllGroups() {
        return allGroups;
    }

    public static void addAllGroups(Group group) {
        Group.allGroups.add(group);
    }
}
