package com.sgh.apk1;

public class Order {
    String name;
    String address;
    String quantity;
    String ID;
    String u_type;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order()
    {

    }

    public String getU_type() {
        return u_type;
    }

    public void setU_type(String u_type) {
        this.u_type = u_type;
    }

    public Order(String name, String address, String quantity, String id,String u_type) {
        this.name=name;
        this.address = address;
        this.quantity = quantity;
        this.ID=id;
        this.u_type=u_type;
    }

    public String getAddress() {
        return address;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getID() {
        return ID;
    }


}
