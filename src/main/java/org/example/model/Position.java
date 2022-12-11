package org.example.model;

public class Position {

    private int id;
    private String post;
    private double salary;

    public Position() {}

    public Position(int id, String post, double salary) {
        this.id = id;
        this.post = post;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
