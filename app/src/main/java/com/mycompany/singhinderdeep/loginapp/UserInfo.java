package com.mycompany.singhinderdeep.loginapp;

/**
 * Created by singh.inderdeep on 09-05-2015.
 */
public class UserInfo {

    String userName;
    String userPass;

    public UserInfo(){

    }
    public UserInfo(String userName, String userPass){
        this.userName=userName;
        this.userPass=userPass;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public void setUserPass(String userPass){
        this.userPass=userPass;
    }



}
