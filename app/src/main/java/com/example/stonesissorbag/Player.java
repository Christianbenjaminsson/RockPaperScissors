package com.example.stonesissorbag;

// Den här klassen kommer behövas om man bygger ut spelet
public class Player {

    private String userName;
    private int userId;

    public Player(String userName, int userId) {

        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return userName;
    }
}
