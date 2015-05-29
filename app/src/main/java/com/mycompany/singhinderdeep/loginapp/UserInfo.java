package com.mycompany.singhinderdeep.loginapp;

/**
 * Created by singh.inderdeep on 09-05-2015.
 */
public class UserInfo {

    public String userName;
    public String userPass;


    public static final int BUNDLE_ORIGIN_LOGIN=1;
    public static final int BUNDLE_ORIGIN_UPDATE=2;
    public static final int BUNDLE_ORIGIN_DELETE=3;
    public static final String BUNDLE_ORIGIN_KEY="ORIGIN";
    public static final String BUNDLE_NAME_KEY="USER_NAME";
    public static final String BUNDLE_PASS_KEY="USER_PASS";

    public UserInfo(){

    }
    public UserInfo(String userName, String userPass){
        this.userName=userName;
        this.userPass=userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
