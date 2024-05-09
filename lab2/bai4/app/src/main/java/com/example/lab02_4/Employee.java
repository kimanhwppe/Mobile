package com.example.lab02_4;

public abstract class Employee {
    private String id;
    private String name;

    public Employee(){};
    protected String getId() {
        return id;
    }
    protected String getName() {
        return name;
    }
    protected void setId(String id) {
        this.id = id;
    }
    protected void setName(String name) {
        this.name = name;
    }

    public abstract double TinhLuong();

    public String toString() {
        return id + " - " +
                name;
    }
}
