package org.evanharmon.neighborshare.models;

public enum Category {
    TOOL ("Tool"),
    LAWNCARE ("Lawncare"),
    COOKING ("Cooking"),
    FURNITURE ("Furniture"),
    CAMPING ("Camping"),
    SPORTS ("Sports"),
    ELECTRONICS ("Electronics");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
