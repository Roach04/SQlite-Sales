package com.project.me.near.sqlite;

/**
 * Created by user on 2016-01-02.
 */
public class Gridata {
    String data;

    int image;

    public Gridata(String data, int image) {
        this.data = data;
        this.image = image;
    }

    public String getData() {
        return data;
    }

    public int getImage() {
        return image;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
