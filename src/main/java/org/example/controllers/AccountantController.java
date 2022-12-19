package org.example.controllers;

import org.example.dao.AccountantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountantController {
    private AccountantDAO accountantDAO;

    @Autowired
    public AccountantController(AccountantDAO accountantDAO) {
        this.accountantDAO = accountantDAO;
    }



}
