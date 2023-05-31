package com.example.alphasolutionseksamen.controllers;


import com.example.alphasolutionseksamen.DTO.Project_members;
import com.example.alphasolutionseksamen.repository.ProjectMembersRepository;
import com.example.alphasolutionseksamen.repository.ProjectRepository;
import com.example.alphasolutionseksamen.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProjectUserController {
    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMembersRepository projectMembersRepository;


    public ProjectUserController() {
        projectRepository = new ProjectRepository();
        userRepository = new UserRepository();
        projectMembersRepository = new ProjectMembersRepository();
    }


    @PostMapping("/assignUser/project")
    public String assignUserProject(@RequestParam("project_id") int project_id,
                                    @RequestParam("email") String email,
                                    @RequestParam("hourly_rate") int hourly_rate,
                                    @RequestParam("role") String role,
                                    @ModelAttribute("project_members") Project_members projectMembers) {
        int assignUser_id = userRepository.getUserEmail(email).getUser_id();
        Project_members projectMember = new Project_members(assignUser_id, hourly_rate, project_id, role);
        projectMembersRepository.assignUserProject(projectMember);
        return "redirect:/projects";
    }
}



