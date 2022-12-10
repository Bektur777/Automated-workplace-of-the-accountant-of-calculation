package org.example.model;

public class Wallet {
    private int id;
    private double wallet;
    private int userId;

    public Wallet() {}

    public Wallet(int id, double wallet, int userId) {
        this.id = id;
        this.wallet = wallet;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
