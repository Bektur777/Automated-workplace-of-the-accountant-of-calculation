package org.example.model;

import java.sql.Date;
import java.time.LocalDate;

public class Payroll {

    private int id;
    private double alimony;
    private double award;
    private double retention;
    private double salary;
    private double finalAccount;
    private int userId;
    private Date startDate;
    private Date endDate;
    private int countWorkDays;
    private Date today;

    public Payroll() {}

    public Payroll(int id, double alimony, double award, double retention, double salary, double finalAccount, int userId, Date startDate, Date endDate, int countWorkDays, Date today) {
        this.id = id;
        this.alimony = alimony;
        this.award = award;
        this.retention = retention;
        this.salary = salary;
        this.finalAccount = finalAccount;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countWorkDays = countWorkDays;
        this.today = today;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAlimony() {
        return alimony;
    }

    public void setAlimony(double alimony) {
        this.alimony = alimony;
    }

    public double getAward() {
        return award;
    }

    public void setAward(double award) {
        this.award = award;
    }

    public double getRetention() {
        return retention;
    }

    public void setRetention(double retention) {
        this.retention = retention;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getFinalAccount() {
        return finalAccount;
    }

    public void setFinalAccount(double finalAccount) {
        this.finalAccount = finalAccount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = Date.valueOf(LocalDate.parse(startDate));
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = Date.valueOf(LocalDate.parse(endDate));
    }

    public int getCountWorkDays() {
        return countWorkDays;
    }

    public void setCountWorkDays(int countWorkDays) {
        this.countWorkDays = countWorkDays;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getToday() {
        return today;
    }

}
