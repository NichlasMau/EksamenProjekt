package com.example.alphasolutionseksamen.controllers;


import com.example.alphasolutionseksamen.enums.customerEnum;
import com.example.alphasolutionseksamen.model.Customer;
import com.example.alphasolutionseksamen.repository.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CustomerController {
    CustomerRepository customerRepository;


    public CustomerController() {
        customerRepository = new CustomerRepository();
    }


    @PostMapping("/assignCustomer/project")
    public ResponseEntity < String > assignUserProject(@RequestParam("project_id") int project_id,
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


    @PostMapping("/loginCustomer")
    public ResponseEntity < String > login(@RequestParam("email") String email, @RequestParam("pw") String pw,
                                           HttpSession session,
                                           Model model,
                                           HttpServletRequest request) {
        Customer customer = customerRepository.getCustomerEmail(email);
        if (customer != null) {
            if (customer.getPassword().equals(pw)) {
                session.setAttribute("customer", customer);
                session.setMaxInactiveInterval(600);
                return ResponseEntity.ok("SUCCESS");
            } else {
                return ResponseEntity.badRequest().body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    @GetMapping("/signupCustomer")
    public String signUp(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "index";
    }


    @PostMapping("/signupCustomer")
    public ResponseEntity < String > signup(@RequestParam("name") String name,
                                            @RequestParam("email") String email,
                                            @RequestParam("password") String password,
                                            HttpSession session, Model model) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Fill out all fields!");


        }


        try {
            Customer existingCustomer = customerRepository.getCustomerEmail(email);
            if (existingCustomer != null) {
                return ResponseEntity.badRequest().body("Email is already registered.");
            }


            Customer newCustomer = new Customer(name, email, password);
            session.setAttribute("customer", customerRepository.getCustomerEmail(customerRepository.createCustomer(newCustomer)));
            session.setMaxInactiveInterval(600);


            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


}



