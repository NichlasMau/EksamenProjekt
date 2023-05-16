package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.Project;
import com.example.alphasolutionseksamen.model.Subproject;
import com.example.alphasolutionseksamen.model.Task;
import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.ProjectRepository;
import com.example.alphasolutionseksamen.repository.SubprojectRepository;
import com.example.alphasolutionseksamen.repository.TaskRepository;
import com.example.alphasolutionseksamen.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class ProjectController {

    ProjectRepository projectRepository;

    public ProjectController() {
        projectRepository = new ProjectRepository();
    }

    @GetMapping(path = "/projects")
    public String getProjects(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Project> projects = projectRepository.getUserProjects(user.getUser_id());
        model.addAttribute("projects", projects);
        model.addAttribute("updateProject", new Project());
        return "projects";
    }

    @GetMapping("/create/project/{id}")
    public String createProject(@PathVariable int id, Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "projects";
    }

    @PostMapping("/create/project")
    public String createProject(@RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("status") String status,
                                @RequestParam("budget") Double budget,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                @ModelAttribute("project") Project project,
                                Model model,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        projectRepository.createProject(project, user.getUser_id());
        return "redirect:/projects";
    }

    @PostMapping("/update/project")
    public String updateProject(@RequestParam("project_id") int project_id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("status") String status,
                                @RequestParam("budget") Double budget,
                                @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startDate,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                @ModelAttribute("project") Project project) {
        projectRepository.updateProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete/project/{id}")
    public String deleteProject(@PathVariable int id) {
        projectRepository.deleteProject(id);
        return "redirect:/projects";
    }
}
