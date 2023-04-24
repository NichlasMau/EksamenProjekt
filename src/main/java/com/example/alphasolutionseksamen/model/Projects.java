package com.example.alphasolutionseksamen.model;

import java.time.LocalDate;

public class Projects {

    private int project_id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectManager;

    public Projects(int project_id, String name, LocalDate startDate, LocalDate endDate, String projectManager) {
        this.project_id = project_id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectManager = projectManager;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }
}
