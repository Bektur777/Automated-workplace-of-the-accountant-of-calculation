package org.example.repository;

import org.example.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
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
        addPayRoll(userWallet.getId());
    }

    public void addWalletToUser(int id) {
        jdbcTemplate.update("INSERT INTO wallet(wallet, userId) VALUES(?, ?)", 0, id);
    }


    public void addPayRoll(int id) {
        jdbcTemplate.update("INSERT INTO payroll(user_id) VALUES(?)", id);
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
                sickerLeave.getStartDate(), sickerLeave.getEndDate(), sickerLeave.getReason(), sickerLeave.getNameOfHospital(),
                sickerLeave.getAddressOfHospital(), sickerLeave.getDoctorName(), sickerLeave.getRegistrationNumber(),
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
        checkUserSalary();
        System.out.println("Good");
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
        }

    }

    public List<Vacation> getVacationList(int id) {
        return jdbcTemplate.query("SELECT * FROM vacation WHERE user_id=?",
                new BeanPropertyRowMapper<>(Vacation.class), id);
    }

    public void createVacationList(Vacation vacation, int id) {
        jdbcTemplate.update("INSERT INTO vacation(number, statement, start_of_date, end_of_date, user_id) " +
                        "VALUES(?, ?, ?, ?, ?)",
                vacation.getNumber(), vacation.getStatement(), vacation.getStartOfDate(),
                vacation.getEndOfDate(), id);
    }

    public int getCountOfWeekdays(Date startDate, Date endDate) {
        LocalDate localDate = Date.valueOf(LocalDate.parse(String.valueOf(startDate))).toLocalDate();
        LocalDate localDate1 = Date.valueOf(LocalDate.parse(String.valueOf(endDate))).toLocalDate();

        int countWeekdays = 0;

        while (localDate.isBefore(localDate1)) {
            if (DayOfWeek.SATURDAY.equals(localDate.getDayOfWeek()) ||
                    DayOfWeek.SUNDAY.equals(localDate.getDayOfWeek())) {
                countWeekdays += 1;
            }
            localDate = localDate.plusDays(1);
        }

        return countWeekdays;
    }

    public void checkUserSalary() {
        User user = getUser();
        Payroll payroll = jdbcTemplate.queryForObject("SELECT * FROM payroll WHERE user_id=?",
                new BeanPropertyRowMapper<>(Payroll.class), user.getId());

        Position position = jdbcTemplate.queryForObject("SELECT * FROM position WHERE post=?",
                new BeanPropertyRowMapper<>(Position.class), user.getPositionName());

        assert payroll != null;
        LocalDate endDate = LocalDate.parse(String.valueOf(payroll.getEndDate()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.of(endDate.getYear(), endDate.getMonthValue()+1, endDate.getDayOfMonth());
        Date endLocalDate = Date.valueOf(localDate);

        long workDays = (endLocalDate.getTime() - payroll.getEndDate().getTime()) /
                (24 * 60 * 60 * 1000);

        System.out.println(workDays);

        assert position != null;
        double salary = payroll.getSalary() == 0 ? position.getSalary() : payroll.getSalary();
        if (Date.valueOf(formatter.format(LocalDate.now())).compareTo(payroll.getEndDate()) == 0) {
            jdbcTemplate.update("UPDATE wallet SET wallet=? WHERE userid=?",
                    payroll.getFinalAccount() + payroll.getAward() - payroll.getSalary() /
                            (workDays - getCountOfWeekdays(payroll.getEndDate(), endLocalDate)), payroll.getUserId());
            jdbcTemplate.update("UPDATE payroll SET alimony=?, award=?, retention=?, " +
                            "start_date=?, end_date=?, count_work_days=? WHERE id=?", 0, 0, 0, payroll.getEndDate(),
                    localDate, workDays,payroll.getId());
        } else {
            double alimony = 0;
            if (!user.getFamilyStatus()) {
                switch (user.getNumberOfChildren()) {
                    case 0 -> alimony = 0;
                    case 1 -> alimony = 0.25 * payroll.getSalary();
                    case 2 -> alimony = 0.33 * payroll.getSalary();
                    default -> alimony = 0.5 * payroll.getSalary();
                }
            }
            if (Date.valueOf(formatter.format(LocalDate.now())).compareTo(payroll.getToday()) != 0) {
                double retention = getRetention(payroll.getSalary());
                jdbcTemplate.update("UPDATE payroll SET alimony=?, retention=?, " +
                                "count_work_days=count_work_days-1, final_account=final_account-?, today=? WHERE id=?",
                        alimony, retention, retention, LocalDate.now(), payroll.getId());
            }
        }

    }

    public double getRetention(double salary) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        LocalTime d1 = LocalTime.parse(dateFormat.format(new java.util.Date(12600000)));
        LocalTime d2 = LocalTime.parse(dateFormat.format(new java.util.Date()));

        double retention = 0;

        long m1 = d1.get(ChronoField.MILLI_OF_DAY);
        long m2 = d2.get(ChronoField.MILLI_OF_DAY);

        long diff = m2 - m1;

        if (diff < 0) {
            retention = 0;
        }

        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long res = diffMinutes + diffHours*60;

        retention = salary/22/8/60*res;

        return retention;
    }

}
