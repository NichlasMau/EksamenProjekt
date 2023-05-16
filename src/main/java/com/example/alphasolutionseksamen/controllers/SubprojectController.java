package com.example.alphasolutionseksamen.controllers;

import com.example.alphasolutionseksamen.model.Project;
import com.example.alphasolutionseksamen.model.Subproject;
import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.SubprojectRepository;
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
public class SubprojectController {
    SubprojectRepository subprojectRepository;

    public SubprojectController() {
        subprojectRepository = new SubprojectRepository();
    }

    @GetMapping(path = "/{id}/subprojects")
    public String getSubprojects(Model model, HttpSession session, @PathVariable int id) {
        User user = (User) session.getAttribute("user");
        List<Subproject> subprojects = subprojectRepository.getUserSubprojects(user.getUser_id(), id);
        model.addAttribute("subprojects", subprojects);
        model.addAttribute("project_id", id);
        return "subprojects";
    }

    @GetMapping("/create/subproject/{id}")
    public String createSubproject(@PathVariable int id, Model model) {
        Subproject subproject = new Subproject();
        model.addAttribute("subproject", subproject);
        return "subprojects";
    }

    @PostMapping("/create/subproject")
    public String createSubproject(@RequestParam("project_id") int project_id,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("status") String status,
                                   @RequestParam("budget") Double budget,
                                   @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                   @ModelAttribute("subproject") Subproject subproject,
                                   Model model,
                                   HttpSession session) {
        subproject.setProject_id(project_id);
        subprojectRepository.createSubproject(subproject);
        return "redirect:/" + project_id + "/subprojects";
    }

    @PostMapping("/update/subproject")
    public String updateSubproject(@RequestParam("subproject_id") int subproject_id,
                                   @RequestParam("project_id") int project_id,
                                   @RequestParam("name") String name,
                                   @RequestParam("description") String description,
                                   @RequestParam("status") String status,
                                   @RequestParam("budget") Double budget,
                                   @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startDate,
                                   @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                   @ModelAttribute("subproject") Subproject subproject) {
        subprojectRepository.updateSubproject(subproject);
        return "redirect:/" + project_id + "/subprojects";
    }

    @GetMapping("/delete/subproject/{p_id}/{id}")
    public String deleteSubproject(@PathVariable int id, @PathVariable int p_id) {
        subprojectRepository.deleteSubproject(id);
        return "redirect:/" + p_id + "/subprojects";
    }


}
