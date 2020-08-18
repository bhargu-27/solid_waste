package com.sgh.apk1;

public class Notify {
    private String dId;
    private String area,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Notify() {
        // This is default constructor.
    }

    public String getdId() {

        return dId;
    }

    public void setdId(String name) {

        this.dId = name;
    }

    public String getArea() {

        return area;
    }

    public void setArea(String name) {

        this.area = name;
    }
}