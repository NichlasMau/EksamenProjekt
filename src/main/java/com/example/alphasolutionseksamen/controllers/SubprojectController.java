package com.example.alphasolutionseksamen.controllers;


import com.example.alphasolutionseksamen.model.Customer;
import com.example.alphasolutionseksamen.model.Subproject;
import com.example.alphasolutionseksamen.model.User;
import com.example.alphasolutionseksamen.repository.ProjectMembersRepository;
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
    ProjectMembersRepository projectMembersRepository;


    public SubprojectController() {
        subprojectRepository = new SubprojectRepository();
        projectMembersRepository = new ProjectMembersRepository();
    }


    @GetMapping(path = "/{id}/subprojects")
    public String getSubprojects(Model model, HttpSession session, @PathVariable int id) {
        User user = (User) session.getAttribute("user");
        Customer customer = (Customer) session.getAttribute("customer");


        List < Subproject > subprojects;
        String username;


        if (user != null) {
            subprojects = subprojectRepository.getUserSubprojects(user.getUser_id(), id);
            model.addAttribute("isUser", true);
            username = user.getName();
            if (projectMembersRepository.userHasProject(user.getUser_id())) {
                model.addAttribute("isAdmin", true);
            }
        } else if (customer != null) {
            subprojects = subprojectRepository.getCustomerSubprojects(customer.getCustomer_id());
            username = customer.getName();
        } else {
            return "redirect:/";
        }


        model.addAttribute("project_id", id);
        model.addAttribute("project_budget", subprojectRepository.getProjectBudget(id));
        model.addAttribute("project_budgetUsed", subprojectRepository.getProjectBudgetUsed(id));
        model.addAttribute("project_budgetLeft", (subprojectRepository.getProjectBudget(id) - subprojectRepository.getProjectBudgetUsed(id)));
        model.addAttribute("subprojects", subprojects);
        model.addAttribute("username", username);


        return "subprojects";
    }


    @GetMapping("/create/subproject/{id}")
    public String createSubproject(@PathVariable int id, Model model) {
        Subproject subproject = new Subproject();
        model.addAttribute("subproject", subproject);
        return "subprojects";
    }


    @PostMapping("/create/subproject")
    public ResponseEntity < String > createSubproject(@RequestParam("project_id") int project_id,
                                                      @RequestParam("name") String name,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("status") String status,
                                                      @RequestParam("budget") Double budget,
                                                      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                                      @ModelAttribute("subproject") Subproject subproject,
                                                      Model model,
                                                      HttpSession session) {


        try {
            if (subprojectRepository.checkIfExceedsBudget(project_id, budget)) {
                return ResponseEntity.badRequest().body("Budget exceeds the project budget!");
            }
            subproject.setProject_id(project_id);
            subprojectRepository.createSubproject(subproject);
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    @PostMapping("/update/subproject")
    public ResponseEntity < String > updateSubproject(@RequestParam("subproject_id") int subproject_id,
                                                      @RequestParam("project_id") int project_id,
                                                      @RequestParam("name") String name,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("status") String status,
                                                      @RequestParam("budget") Double budget,
                                                      @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startDate,
                                                      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDate,
                                                      @ModelAttribute("subproject") Subproject subproject) {
        try {
            if (subprojectRepository.checkIfExceedsBudget(project_id, budget)) {
                return ResponseEntity.badRequest().body("Budget exceeds the project budget!");
            }
            subprojectRepository.updateSubproject(subproject);
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    @GetMapping("/delete/subproject/{p_id}/{id}")
    public String deleteSubproject(@PathVariable int id, @PathVariable int p_id) {
        subprojectRepository.deleteSubproject(id);
        return "redirect:/" + p_id + "/subprojects";
    }


}



