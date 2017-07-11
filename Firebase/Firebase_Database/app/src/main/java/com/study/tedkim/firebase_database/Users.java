package com.study.tedkim.firebase_database;

/**
 * Created by tedkim on 2017. 7. 11..
 */

public class Users {

    public String userName;
    public String email;

    public Users(){

        userName = "";
        email = "";

    }

    public Users(String userName, String email){

        this.userName = userName;
        this.email = email;
    }
}
