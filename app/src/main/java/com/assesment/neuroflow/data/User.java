package com.assesment.neuroflow.data;

public class User {
    String name;
    int score;
    String timestamp;

    public User() {
    }

    public User(String name, int score, String timestamp) {
        this.name = name;
        this.score = score;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
