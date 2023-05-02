package com.example.alphasolutionseksamen.model;

public class Customer {
    private int customer_id;
    private String name;
    private String email;
    private int phone;

    public Customer(int customerId, String name, String email, int phoneNr) {
        this.customer_id = customerId;
        this.name = name;
        this.email = email;
        this.phone = phoneNr;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}

