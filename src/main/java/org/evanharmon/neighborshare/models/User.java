package org.evanharmon.neighborshare.models;

import java.util.ArrayList;

public class User {

    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private ArrayList<Group> groups = new ArrayList<>();

    private ArrayList<Item> items = new ArrayList<>();

    // Prototyping
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static Integer nextId = 1;

    public User(String email, String password, String firstName, String lastName) {
        this();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
        this.id = nextId;
        nextId++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groups=" + groups +
                ", items=" + items +
                '}';
    }

    // Prototyping
    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void addAllUsers(User user) {
        User.allUsers.add(user);
    }
}
