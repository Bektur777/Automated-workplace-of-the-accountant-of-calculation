package org.example.dao;

import org.example.model.Payroll;
import org.example.model.SickerLeave;
import org.example.model.User;
import org.example.model.Vacation;
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

    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserById(int id) {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?",
                    new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updateUser(User user, int id) {
        jdbcTemplate.update("UPDATE users SET username=?, password=?, enabled=?, firstname=?, lastname=?, email=?, age=?, " +
                        "family_status=?, number_of_children=?, place_of_birth=?, position_name=? WHERE id=?",
                user.getUsername(), user.getPassword(), user.getEnabled(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getAge(), user.getFamilyStatus(), user.getNumberOfChildren(), user.getPlaceOfBirth(),
                user.getPositionName(), id);
    }

    public void deleteUserById(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    public List<Vacation> getVacationStatementList() {
        return jdbcTemplate.query("SELECT * FROM vacation WHERE agreement = false",
                new BeanPropertyRowMapper<>(Vacation.class));
    }

    public List<Vacation> getVacationList() {
        return jdbcTemplate.query("SELECT * FROM vacation WHERE agreement = true",
                new BeanPropertyRowMapper<>(Vacation.class));
    }

    public List<SickerLeave> getSickerLeaveList() {
        return jdbcTemplate.query("SELECT * FROM sick_leave",
                new BeanPropertyRowMapper<>(SickerLeave.class));
    }

    public void updateVacationUser(Vacation vacation, int id) {
        jdbcTemplate.update("UPDATE vacation SET agreement=? WHERE user_id=?",
                vacation.isAgreement(), id);
    }

    public Vacation getUserVacationStatementById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM vacation WHERE user_id=?",
                new BeanPropertyRowMapper<>(Vacation.class), id);
    }

    public Payroll getUserPayroll(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM payroll WHERE user_id=?",
                new BeanPropertyRowMapper<>(Payroll.class), id);
    }

    public void updateUserAward(Payroll payroll, int id) {
        jdbcTemplate.update("UPDATE payroll SET award=? WHERE user_id=?", payroll.getAward(), id);
    }

}
