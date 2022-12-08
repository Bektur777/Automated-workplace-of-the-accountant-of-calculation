package org.example.controllers;

import org.example.dao.AdminDAO;
import org.example.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/wallet")
    public String wallet() {
        return "admin/wallet";
    }

    @PostMapping()
    public ModelAndView create(@ModelAttribute("wallet") Wallet wallet) {
        return new ModelAndView("redirect:/admin");
    }

}

