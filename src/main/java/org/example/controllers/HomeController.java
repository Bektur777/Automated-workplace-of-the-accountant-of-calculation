package org.example.controllers;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        userRepository.getUser();
        return "hr/hr_page";
    }

    @GetMapping("/worker")
    public String worker(Model model) {
        model.addAttribute("user", userRepository.getUser());
        return "worker/worker_page";
    }

    @GetMapping("/admin")
    public String admin() {
        userRepository.getUser();
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.addUser(user);
        return "redirect:/login";
    }

}
