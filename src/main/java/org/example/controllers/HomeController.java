package org.example.controllers;

import org.example.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping()
    public String home() {
        return "home";
    }

    @GetMapping("/hr")
    public String hr() {
        return "hr/hr_page";
    }

    @GetMapping("/worker")
    public String worker() {
        return "worker/worker_page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin_page";
    }

    @GetMapping("/login")
    public String login() {
        return "authentication/login_page";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register() {
        return "authentication/register";
    }

    @PostMapping
    public String reg(@ModelAttribute("user") User user) {

        return "redirect:/home";
    }

}
