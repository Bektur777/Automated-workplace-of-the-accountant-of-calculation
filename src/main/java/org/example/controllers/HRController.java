package org.example.controllers;

import org.example.dao.HRDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HRController {

    private HRDao hrDao;

    @Autowired
    public HRController(HRDao hrDao) {
        this.hrDao = hrDao;
    }

}
