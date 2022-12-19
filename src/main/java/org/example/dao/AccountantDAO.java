package org.example.dao;

import org.example.model.Payroll;
import org.example.model.Position;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountantDAO {

    private JdbcTemplate jdbcTemplate;

    public AccountantDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Position> getPositionSalary() {
        return jdbcTemplate.query("SELECT * FROM position", new BeanPropertyRowMapper<>(Position.class));
    }

    public List<Payroll> getUserPayroll() {
        return jdbcTemplate.query("SELECT * FROM payroll", new BeanPropertyRowMapper<>(Payroll.class));
    }

}
