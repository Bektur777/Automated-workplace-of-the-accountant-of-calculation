package org.example.controllers;

import org.example.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminDAO adminDAO;

    @Autowired
    public AdminController(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @GetMapping("/accounts")
    public String users(Model model) {
        model.addAttribute("users", adminDAO.getUsers());
        return "admin/users";
    }

}

