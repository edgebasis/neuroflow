package com.assesment.neuroflow.data;

public class User implements Comparable<User> {
    private String name;
    private int score;
    private Long timestamp;
    private int image;
    private int gender;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public User() {
    }

    public User(String name, int score, long timestamp) {
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    @Override
    public String toString() {
        return "Name : " + this.name + ", score : " + this.score + ", timestamp " + this.timestamp
                +", gender : " + this.gender + ", image" + this.image;
    }

    @Override
    public int compareTo(User user) {
        return (this.getTimestamp() < user.getTimestamp() ? -1 :
                this.getTimestamp() == user.getTimestamp() ? 0:1);
    }
}
