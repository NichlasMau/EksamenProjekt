package com.example.alphasolutionseksamen.model;


import java.time.LocalDateTime;
import java.util.Date;


public class Subproject {
    private int subproject_id;
    private int project_id;
    private String name;
    private String description;
    private String status;
    private double budget;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public Subproject(int subproject_id, int project_id, String name, String description, String status, double budget, LocalDateTime startDate, LocalDateTime endDate) {
        this.subproject_id = subproject_id;
        this.project_id = project_id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Subproject() {
    }


    public int getSubproject_id() {
        return subproject_id;
    }


    public void setSubproject_id(int subproject_id) {
        this.subproject_id = subproject_id;
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


    public LocalDateTime getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }


    public LocalDateTime getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
