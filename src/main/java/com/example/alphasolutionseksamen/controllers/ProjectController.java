package com.example.alphasolutionseksamen.controllers;


import com.example.alphasolutionseksamen.model.*;
import com.example.alphasolutionseksamen.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


@Controller
public class ProjectController {


    ProjectRepository projectRepository;
    ProjectMembersRepository projectMembersRepository;


    public ProjectController() {
        projectRepository = new ProjectRepository();
        projectMembersRepository = new ProjectMembersRepository();
    }


    @GetMapping(path = "/projects")
    public String getProjects(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Customer customer = (Customer) session.getAttribute("customer");
        List < Project > projects;
        String username;
        if (user != null) {
            projects = projectRepository.getUserProjects(user.getUser_id());
            model.addAttribute("updateProject", new Project());
            model.addAttribute("isUser", true);
            model.addAttribute("assignedUsers", projectMembersRepository.getAssignedUserProject(user.getUser_id()));
            username = user.getName();
            if (projectMembersRepository.userHasProject(user.getUser_id())) {
                model.addAttribute("isAdmin", true);
            }
        } else if (customer != null) {
            projects = projectRepository.getCustomerProjects(customer.getCustomer_id());
            username = customer.getName();
        } else {
            return "redirect:/";
        }
        model.addAttribute("projects", projects);
        model.addAttribute("username", username);
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



