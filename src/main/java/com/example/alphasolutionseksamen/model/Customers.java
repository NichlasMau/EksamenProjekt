package com.example.alphasolutionseksamen.model;

public class Customers {
    private int customerId;
    private String name;
    private String address;
    private String email;
    private int phoneNr;

    public Customers(int customerId, String name, String address, String email, int phoneNr) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNr = phoneNr;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }
}

