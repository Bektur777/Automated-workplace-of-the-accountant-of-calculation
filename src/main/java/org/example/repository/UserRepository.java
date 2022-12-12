package org.example.repository;

import org.example.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users(username, password, enabled, firstname, lastname, email, age, " +
                        "family_status, number_of_children, start_of_date, place_of_birth, position_name) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                user.getUsername(), user.getPassword(), false, user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getAge(), user.getFamilyStatus(), user.getNumberOfChildren(), LocalDate.now(), user.getPlaceOfBirth(),
                user.getPositionName());
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
        updateCurrentUser(user.getUsername());
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
        updateCurrentUser(username);
        checkVacationList();
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=?",
                new BeanPropertyRowMapper<>(User.class), username);
    }

    public void updateCurrentUser(String username) {
        jdbcTemplate.update("UPDATE current_user_username SET username=?", username);

    }

    public void checkVacationList() {
        User user = getUser();
        LocalDate oldDate = LocalDate.parse(String.valueOf(user.getStartOfDate()));
        LocalDate now = LocalDate.parse(String.valueOf(LocalDate.now()));
        Period diff = Period.between(oldDate, now);

        if (diff.getMonths() >= 11 || diff.getYears() == 1) {
            System.out.println(diff.getMonths());
            jdbcTemplate.update("UPDATE users SET vacation=true WHERE id=?",
                    user.getId());
        } else {
            System.out.println("До отпуска не хватает " + (11 - diff.getMonths()) + " месяца");
        }
    }

    public List<Vacation> getVacationList(int id) {
        return jdbcTemplate.query("SELECT * FROM vacation WHERE user_id=?",
                new BeanPropertyRowMapper<>(Vacation.class), id);
    }

    public void createVacationList(Vacation vacation, int id) {
        jdbcTemplate.update("INSERT INTO vacation(number, statement, start_of_date, end_of_date, user_id) " +
                        "VALUES(?, ?, ?, ?, ?)",
                vacation.getNumberPhone(), vacation.getStatement(), vacation.getStartDate(),
                vacation.getEndDate(), id);
    }

}
