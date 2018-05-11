package com.example.myapplication;

public class user {


    private int score;
    public boolean flag;

    user() {
        score = 0;
        flag = false;
    }
    public int getScore() {
        return score;
    }

    public void addscore() {

        if (flag) {
            score++;
            flag=false;
        }

    }


}
