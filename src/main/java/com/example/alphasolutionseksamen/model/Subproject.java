package com.example.alphasolutionseksamen.model;

import java.util.Date;

public class Subproject {
    private int subproject_id;
    private String name;
    private String description;
    private String status;
    private double budget;
    private Date startDate;
    private Date endDate;

    public Subproject(int subproject_id, String name, String description, String status, double budget, Date startDate, Date endDate) {
        this.subproject_id = subproject_id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;



    }
    public int getSubproject_id() {
        return subproject_id;
    }
    public void setSubproject_id(int subproject_id) {
        this.subproject_id = subproject_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    }

