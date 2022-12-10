package org.example.repository;

import org.example.model.CurrentUser;
import org.example.model.SickerLeave;

import org.example.model.User;
import org.example.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                user.getEmail(), user.getAge(), user.getRole().toLowerCase());
        User userWallet = jdbcTemplate.queryForObject("SELECT id FROM users WHERE username=?",
                new BeanPropertyRowMapper<>(User.class), user.getUsername());
        assert userWallet != null;
        addWalletToUser(userWallet.getId());
    }

    public void addWalletToUser(int id) {
        jdbcTemplate.update("INSERT INTO wallet(wallet, userId) VALUES(?, ?)", 0, id);
    }

    public void updateUser(User user, int id) {
        jdbcTemplate.update("UPDATE users SET username=?, password=?, " +
                "firstname=?, lastname=?, email=?, age=? WHERE id=?",
                user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getAge(), id);
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

    public Wallet getWalletUser(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM wallet WHERE userId=?",
                new BeanPropertyRowMapper<>(Wallet.class), id);
    }

    public User getUser() {
        CurrentUser currentUser = jdbcTemplate.queryForObject("SELECT * FROM current_user_username",
                new BeanPropertyRowMapper<>(CurrentUser.class));
        assert currentUser != null;
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                new BeanPropertyRowMapper<>(User.class), currentUser.getUsername());
    }

    public List<SickerLeave> getSickerLeave(int id) {
        return jdbcTemplate.query("SELECT * FROM sick_leave WHERE userid=?",
                new BeanPropertyRowMapper<>(SickerLeave.class), id);
    }

    public User getUserByUsername(String username) {
        jdbcTemplate.update("UPDATE current_user_username SET username=?", username);
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                new BeanPropertyRowMapper<>(User.class), username);
    }

}
