package com.dhill.hw1;

public class user {
    private int mUserId;

    private String mUserName;
    private String mPassword;
    public user(String mUserName, String mPassword,int mID) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
        this.mUserId=mID;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
