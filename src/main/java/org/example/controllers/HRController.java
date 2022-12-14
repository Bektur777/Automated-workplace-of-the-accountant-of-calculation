package org.example.controllers;

import org.example.dao.HRDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hr")
public class HRController {

    private HRDao hrDao;

    @Autowired
    public HRController(HRDao hrDao) {
        this.hrDao = hrDao;
    }

    @GetMapping("/list_of_workers")
    public String getAllWorkers(Model model) {
        model.addAttribute("users", hrDao.getUsers());
        return "/hr/all_users";
    }

    @GetMapping("/edit/{id}")
    public String editUsers(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", hrDao.findUserById(id));
        return "/hr/edit";
    }

    @PatchMapping("/edit/update/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        hrDao.updateUser(user, id);
        return "redirect:/hr";
    }

    @DeleteMapping("/edit/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        hrDao.deleteUserById(id);
        return "redirect:/hr/list_of_workers";
    }

    @GetMapping("/vacation_statement_list")
    public String vacationStatementList(Model model) {
        model.addAttribute("vacationList", hrDao.getVacationStatementList());
        return "hr/vacations_statement";
    }

    @GetMapping("/vacation_list")
    public String vacationList(Model model) {
        model.addAttribute("vacationList", hrDao.getVacationList());
        return "/hr/vacation_list";
    }

    @GetMapping("/sicker_leave_list")
    public String sickerLeaveList(Model model) {
        model.addAttribute("sickerLeave", hrDao.getSickerLeaveList());
        return "hr/sicker_list";
    }

    

}
