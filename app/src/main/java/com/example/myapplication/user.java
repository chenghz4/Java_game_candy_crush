package com.example.myapplication;

public class user {


    private int score;
    public boolean flag;
    public int count=0;
    public int countmax=10;
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
    public  boolean check(){
        if(count<countmax) return false;
        else return true;



    }
    public void clear(){
        score=0;
        count=0;
        countmax=10;
        flag=false;

    }


}
