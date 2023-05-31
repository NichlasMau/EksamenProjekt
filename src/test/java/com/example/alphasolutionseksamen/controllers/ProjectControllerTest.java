package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.Project;
import com.example.alphasolutionseksamen.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectControllerTest {
    private ProjectRepository projectRepository;

    @BeforeEach
    void setup() {
        projectRepository = new ProjectRepository();
    }

    @Test
    void testCreateProject() {
        int user_id = 1;
        String name = "Test project";
        String description = "Test project";
        String status = "todo";
        double budget = 500.00;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.parse("2023-07-10T18:00:00");

        Project testProject = new Project(name, description, status, budget, startDate, endDate);

        try {
            boolean success = projectRepository.createProject(testProject, user_id);
            assertTrue(success, "Project created successfully!");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
