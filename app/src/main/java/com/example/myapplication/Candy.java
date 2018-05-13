package com.example.myapplication;
import java.util.Random;
public class Candy {

    private int type;
    public void set_type() {
        Random rand = new Random();
        type = 1 + rand.nextInt(5);//6 type
    }

    public int get_type() {
        return type;
    }
}