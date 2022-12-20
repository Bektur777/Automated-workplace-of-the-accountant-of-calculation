package org.example.controllers;

import org.example.dao.WorkerDAO;
import org.example.model.SickerLeave;
import org.example.model.User;
import org.example.model.Vacation;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @GetMapping("/create/sicker_leave/{id}")
    public String sickerLeave(@ModelAttribute("sicker") SickerLeave sickerLeave,
                              Model model, @PathVariable("id") int id) {
        model.addAttribute("sicker", sickerLeave);
        model.addAttribute("userId", id);
        model.addAttribute("userProfile", userRepository.getUser());
        return "worker/create_sicker_page";
    }

    @PostMapping("/create/sicker_leave/{id}")
    public String createSickerLeave(@ModelAttribute("sickerLeave") SickerLeave sickerLeave,
                                    @PathVariable("id") int id) {
        userRepository.addSickerLeave(sickerLeave, id);
        return "redirect:/worker";
    }

    @GetMapping("/sicker_leave/{id}")
    public String getUserSickerLeave(@PathVariable("id") int id, Model model) {
        model.addAttribute("sickerLeaves", userRepository.getSickerLeave(id));
        model.addAttribute("userProfile", userRepository.getUser());
        return "worker/sicker_page";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("userProfile", userRepository.getUser());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        model.addAttribute("userProfile", userRepository.getUser());
        return "profile_edit";
    }

    @PatchMapping("/profile/edit/{id}")
    public String updateProfile(@PathVariable("id") int id, @ModelAttribute User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User userInDb = userRepository.getUser();
        if (!userInDb.getPassword().equals(user.getPassword())) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userRepository.updateUser(user, id);
        return "redirect:/";
    }

    @GetMapping("/wallet/{id}")
    public String getWallet(@PathVariable("id") int id, Model model) {
        model.addAttribute("wallet", userRepository.getWalletUser(id));
        model.addAttribute("userProfile", userRepository.getUser());
        return "wallet";
    }

    @GetMapping("/vacation/{id}")
    public String getVacationList(@PathVariable("id") int id, Model model) {
        model.addAttribute("vacation", userRepository.getVacationList(id));
        model.addAttribute("userProfile", userRepository.getUser());
        return "vacation_list";
    }

    @GetMapping("/create/vacation/{id}")
    public String createVacationList(@PathVariable("id") int id,
                              @ModelAttribute("vacation") Vacation vacation, Model model) {
        model.addAttribute("vacation", vacation);
        model.addAttribute("id", id);
        model.addAttribute("userProfile", userRepository.getUser());
        return "create_vacation";
    }

    @PostMapping("/create/vacation/{id}")
    public String createVacationList(@ModelAttribute("vacation") Vacation vacation,
                                     @PathVariable("id") int id) {
        userRepository.createVacationList(vacation, id);
        return "redirect:/worker";
    }

    @GetMapping("/payroll/{id}")
    public String userPayroll(@PathVariable("id") int id, Model model) {
        model.addAttribute("payroll", userRepository.getUserPayroll(id));
        model.addAttribute("userProfile", userRepository.getUser());
        return "payroll";
    }

}
