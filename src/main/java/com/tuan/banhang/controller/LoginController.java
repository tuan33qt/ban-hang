package com.tuan.banhang.controller;

import com.tuan.banhang.global.GlobalData;
import com.tuan.banhang.model.Customers;
import com.tuan.banhang.model.category;
import com.tuan.banhang.respository.CustomerRepository;
import com.tuan.banhang.respository.RoleRepository;
import com.tuan.banhang.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CustomerService customerService;
    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }
    @GetMapping("/logout")
    public String logout() {
        return "shop";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") Customers customers) {
       customerRepository.save(customers);
        return "redirect:/login";
    }

}
