package com.example.alphasolutionseksamen.model;

public class Employees {
    private int employeeId;
    private String name;
    private String email;
    private String password;

    public Employees(int employeeId, String name, String email, String password) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}



