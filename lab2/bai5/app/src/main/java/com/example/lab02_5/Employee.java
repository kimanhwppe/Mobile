package com.example.lab02_5;

public class Employee {
    private String id;
    private String name;
    private Boolean manager;

    public Employee(){};
    protected String getId() {
        return id;
    }
    protected String getFullName() {
        return name;
    }

    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }

    protected void setId(String id) {
        this.id = id;
    }
    protected void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return id + " - " +
                name;
    }

    public boolean isManager() {
        return this.manager;
    }
}
