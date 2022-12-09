package org.example.controllers;

import org.example.dao.WorkerDAO;
import org.example.model.SickerLeave;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerDAO workerDAO;
    private final UserRepository userRepository;

    @Autowired
    public WorkerController(WorkerDAO workerDAO, UserRepository userRepository) {
        this.workerDAO = workerDAO;
        this.userRepository = userRepository;
    }

    @GetMapping("/sicker_leave/{id}")
    public String sickerLeave(@ModelAttribute("sicker") SickerLeave sickerLeave,
                              Model model, @PathVariable("id") int id) {
        model.addAttribute("sicker", sickerLeave);
        model.addAttribute("userId", id);
        return "sicker_page";
    }

    @PostMapping("/sicker_leave/{id}")
    public String createSickerLeave(@ModelAttribute("sickerLeave") SickerLeave sickerLeave,
                                    @PathVariable("id") int id) {
        userRepository.addSickerLeave(sickerLeave, id);
        return "redirect:/worker";
    }

}
