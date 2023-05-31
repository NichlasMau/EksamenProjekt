package com.example.alphasolutionseksamen.controllers;


import com.example.alphasolutionseksamen.enums.customerEnum;
import com.example.alphasolutionseksamen.model.Customer;
import com.example.alphasolutionseksamen.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CustomerController {
    CustomerRepository customerRepository;


    public CustomerController() {
        customerRepository = new CustomerRepository();
    }


    @PostMapping("/assignCustomer/project")
    public ResponseEntity<String> assignUserProject(@RequestParam("project_id") int project_id,
                                                    @RequestParam("email") String email) {
        customerEnum result = customerRepository.assignCustomerProject(email, project_id);


        if (result == customerEnum.CUSTOMER_NOT_FOUND) {
            return ResponseEntity.badRequest().body("Customer not found");
        } else if (result == customerEnum.ERROR) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        } else {
            return ResponseEntity.ok("SUCCESS");
        }
    }




}



