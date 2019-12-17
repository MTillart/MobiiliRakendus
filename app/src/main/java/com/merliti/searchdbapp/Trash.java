package com.merliti.searchdbapp;

public class Trash {
    public int id;
    public String name, container, description;

    public Trash(int id, String name, String container, String description) {
        this.id = id;
        this.name = name;
        this.container = container;
        this.description = description;
    }

    public Trash(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
