package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping()
    public String home() {
        return "home";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager/manager_page";
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
        return "authentication/logout_page";
    }

}
