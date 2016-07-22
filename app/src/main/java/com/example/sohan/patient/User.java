package com.example.sohan.patient;

/**
 * Created by Sohan on 5/28/2016.
 */
public class User {
    String  name, height, weight, password;
    int id,age;

    public User(String name, int age, String height, String weight, String password){
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.password = password;
    }
}
