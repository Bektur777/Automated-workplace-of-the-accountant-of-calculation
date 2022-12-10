package org.example.controllers;

import org.example.dao.HRDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("users", hrDao.getUsers("worker"));
        return "/hr/all_users";
    }

    @GetMapping("/list_of_accountants")
    public String getAllAccountant(Model model) {
        model.addAttribute("users", hrDao.getUsers("accountant"));
        return "/hr/all_users";
    }

}
