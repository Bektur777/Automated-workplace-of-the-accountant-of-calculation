package org.example.model;

import java.sql.Date;
import java.time.LocalDate;

public class Vacation {

    private String number;
    private String statement;
    private Date startOfDate;
    private Date endOfDate;
    private boolean agreement;
    private int userId;

    public Vacation() {}

    public Vacation(String number, String statement, Date startOfDate, Date endOfDate, boolean agreement, int userId) {
        this.number = number;
        this.statement = statement;
        this.startOfDate = startOfDate;
        this.endOfDate = endOfDate;
        this.agreement = agreement;
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getStartOfDate() {
        return startOfDate;
    }

    public void setStartOfDate(String startDate) {
        this.startOfDate = Date.valueOf(LocalDate.parse(startDate));
    }

    public Date getEndOfDate() {
        return endOfDate;
    }

    public void setEndOfDate(String endDate) {
        this.endOfDate = Date.valueOf(LocalDate.parse(endDate));
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "numberPhone='" + number + '\'' +
                ", statement='" + statement + '\'' +
                ", startDate=" + startOfDate +
                ", endDate=" + endOfDate +
                ", agreement=" + agreement +
                ", userId=" + userId +
                '}';
    }
}
