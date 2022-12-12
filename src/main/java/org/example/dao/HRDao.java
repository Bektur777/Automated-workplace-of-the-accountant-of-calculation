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

    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserById(int id) {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?",
                    new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updateUser(User user, int id) {
        System.out.println(user);
        jdbcTemplate.update("UPDATE users SET username=?, password=?, enabled=?, firstname=?, lastname=?, email=?, age=?, " +
                        "family_status=?, number_of_children=?, place_of_birth=?, position_name=? WHERE id=?",
                user.getUsername(), user.getPassword(), user.getEnabled(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getAge(), user.getFamilyStatus(), user.getNumberOfChildren(), user.getPlaceOfBirth(),
                user.getPositionName(), id);
    }

}
