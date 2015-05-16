package com.mycompany.singhinderdeep.loginapp;

/**
 * Created by singh.inderdeep on 09-05-2015.
 */
public class UserInfo {

    String userName;
    String userPass;


    public static final int BUNDLE_ORIGIN_LOGIN=1;
    public static final int BUNDLE_ORIGIN_UPDATE=2;
    public static final int BUNDLE_ORIGIN_DELETE=3;
    public static final String BUNDLE_ORIGIN_KEY="origin";
    public static final String BUNDLE_NAME_KEY="user_name";
    public static final String BUNDLE_PASS_KEY="user_pass";

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
    public String getUserName(){
        return this.userName;
    }
    public String getUserPass(){
        return this.userPass;
    }
}
