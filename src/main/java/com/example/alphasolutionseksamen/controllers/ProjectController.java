package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.Project;
import com.example.alphasolutionseksamen.repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProjectController {

    ProjectRepository projectRepository;

    public ProjectController() {
        projectRepository = new ProjectRepository();
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Project>> getProjects(){
        List<Project> wishlists = projectRepository.getProjects();
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }
}
