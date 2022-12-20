package org.example.model;

import java.sql.Date;

public class User {

    private int id;
    private String username;
    private String password;
    private Boolean enabled = false;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private boolean familyStatus = false;
    private int numberOfChildren;
    private Date startOfDate;
    private String placeOfBirth;
    private String positionName;
    private boolean vacation;

    public User() {
    }

    public User(int id, String username, String password, Boolean enabled, String firstName, String lastName, String email, int age, boolean familyStatus, int numberOfChildren, Date startOfDate, String placeOfBirth, String positionName, boolean vacation) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.familyStatus = familyStatus;
        this.numberOfChildren = numberOfChildren;
        this.startOfDate = startOfDate;
        this.placeOfBirth = placeOfBirth;
        this.positionName = positionName;
        this.vacation = vacation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public boolean getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(boolean familyStatus) {
        this.familyStatus = familyStatus;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Date getStartOfDate() {
        return startOfDate;
    }

    public void setStartOfDate(Date startOfDate) {
        this.startOfDate = startOfDate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public boolean getVacation() {
        return vacation;
    }

    public void setVacation(boolean vacation) {
        this.vacation = vacation;
    }

}
