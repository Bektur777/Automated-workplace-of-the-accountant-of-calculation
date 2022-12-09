package org.example.repository;

import org.example.model.CurrentUser;
import org.example.model.SickerLeave;

import org.example.model.User;
import org.example.security.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users(username, password, enabled, firstname, lastname, email, age, role) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                user.getUsername(), user.getPassword(), false, user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getAge(), user.getRole());
    }

    public void addSickerLeave(SickerLeave sickerLeave, int id) {
        jdbcTemplate.update(
                "INSERT INTO sick_leave(firstname, lastname, email, age, startdate, enddate, reason, nameofhospital, addressofhospital, doctorname, registrationnumber, userid) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                sickerLeave.getFirstName(), sickerLeave.getLastName(), sickerLeave.getEmail(), sickerLeave.getAge(),
                sickerLeave.getStartDate(), sickerLeave.getEndDate(), sickerLeave.getReason(), sickerLeave.getHospitalName(),
                sickerLeave.getHospitalAddress(), sickerLeave.getDoctorName(), sickerLeave.getRegistrationNumber(),
                id
        );
    }

    public User getUser() {
        CurrentUser currentUser = jdbcTemplate.queryForObject("SELECT * FROM current_user_username",
                new BeanPropertyRowMapper<>(CurrentUser.class));
        assert currentUser != null;
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                new BeanPropertyRowMapper<>(User.class), currentUser.getUsername());
    }

    public SickerLeave getSickerLeave() {
        return jdbcTemplate.queryForObject("SELECT * FROM sick_leave", new BeanPropertyRowMapper<>());
    }

    public User getUserByUsername(String username) {
        jdbcTemplate.update("UPDATE current_user_username SET username=?", username);
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                new BeanPropertyRowMapper<>(User.class), username);
    }

}
