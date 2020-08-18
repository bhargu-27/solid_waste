package com.sgh.apk1;

public class Member {
    public String fullname;
    public String email;
    public String userType;
    public String mobile;
    public Member(){}
    public Member(String fullname, String email, String userType, String mobile) {
        this.fullname = fullname;
        this.email = email;
        this.userType = userType;
        this.mobile = mobile;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public String getMobile() {
        return mobile;
    }
}
