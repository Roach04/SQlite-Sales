package com.project.me.near.sqlite;

/**
 * Created by user on 2016-06-09.
 */
public class Dbase {

    String names,email,phone;

    public Dbase(String names, String email, String phone) {
        this.names = names;
        this.email = email;
        this.phone = phone;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
