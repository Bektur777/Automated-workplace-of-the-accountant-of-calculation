package org.example.dao;

import org.example.model.SickerLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class WorkerDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
