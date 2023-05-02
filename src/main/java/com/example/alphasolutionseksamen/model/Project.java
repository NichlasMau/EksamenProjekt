package com.example.alphasolutionseksamen.model;

import java.util.Date;

public class Project {

    private int project_id;
    private String name;
    private String description;
    private Enum status;
    private double budget;
    private Date startDate;
    private Date endDate;

    public Project(int project_id, String name, String description, Enum status, double budget, Date startDate, Date endDate) {
        this.project_id = project_id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
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

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
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