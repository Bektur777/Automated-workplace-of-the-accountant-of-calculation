package org.example.controllers;

import org.example.dao.AdminDAO;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {

    private UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String registration(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "authentication/register";
    }

    @PostMapping("/user/register")
    public String reg(@ModelAttribute("user") @Valid User user) {
        userRepository.addUser(user);
        return "redirect:/login";
    }

}
