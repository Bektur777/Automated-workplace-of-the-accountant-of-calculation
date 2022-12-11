package org.example.dao;

import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HRDao {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public HRDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers(String role) {
        return jdbcTemplate.query("SELECT * FROM users WHERE role=?", new BeanPropertyRowMapper<>(User.class), role);
    }

//    public User findUser() {
//            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE ");
//    }

}
