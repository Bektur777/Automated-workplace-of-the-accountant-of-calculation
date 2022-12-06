package org.example.controllers;

import org.example.dao.WorkerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerDAO workerDAO;

    @Autowired
    public WorkerController(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }



}
