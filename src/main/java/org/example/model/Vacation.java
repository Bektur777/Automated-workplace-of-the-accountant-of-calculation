package org.example.model;

import java.sql.Date;
import java.time.LocalDate;

public class Vacation {

    private String numberPhone;
    private String statement;
    private Date startDate;
    private Date endDate;
    private boolean agreement;
    private int userId;

    public Vacation() {}

    public Vacation(String numberPhone, String statement, Date startDate, Date endDate, boolean agreement, int userId) {
        this.numberPhone = numberPhone;
        this.statement = statement;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agreement = agreement;
        this.userId = userId;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
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

    public boolean getAgreement() {
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

}
