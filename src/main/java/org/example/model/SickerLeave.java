package org.example.model;

import java.time.LocalDate;
import java.sql.Date;

public class SickerLeave {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String hospitalName;
    private String hospitalAddress;
    private String doctorName;
    private String registrationNumber;
    private int userId;

    public SickerLeave() {}

    public SickerLeave(int id, String firstName, String lastName, String email, int age, Date startDate, Date endDate, String reason, String hospitalName, String hospitalAddress, String doctorName, String registrationNumber, int userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.doctorName = doctorName;
        this.registrationNumber = registrationNumber;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
