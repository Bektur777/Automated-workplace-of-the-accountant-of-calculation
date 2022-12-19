package org.example.controllers;

import org.example.dao.AccountantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accountant")
public class AccountantController {
    private AccountantDAO accountantDAO;

    @Autowired
    public AccountantController(AccountantDAO accountantDAO) {
        this.accountantDAO = accountantDAO;
    }

    @GetMapping("/position")
    public String positionSalary(Model model) {
        model.addAttribute("salaries", accountantDAO.getPositionSalary());
        return "accountant/position_salary";
    }

    @GetMapping("/payroll")
    public String userPayroll(Model model) {
        model.addAttribute("payrolls", accountantDAO.getUserPayroll());
        return "accountant/user_payroll";
    }

}
