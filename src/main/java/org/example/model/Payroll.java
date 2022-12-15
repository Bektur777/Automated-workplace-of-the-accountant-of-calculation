package org.example.model;

public class Payroll {

    private int id;
    private double alimony;
    private double award;
    private double retention;
    private double salary;
    private double finalAccount;
    private int userId;

    public Payroll() {}

    public Payroll(int id, double alimony, double award, double retention, double salary, double finalAccount, int userId) {
        this.id = id;
        this.alimony = alimony;
        this.award = award;
        this.retention = retention;
        this.salary = salary;
        this.finalAccount = finalAccount;
        this.userId = userId;
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

}
