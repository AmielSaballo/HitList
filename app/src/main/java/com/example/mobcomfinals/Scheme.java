package com.example.mobcomfinals;

import java.io.Serializable;

public class Scheme implements Serializable {
    int id;
    String title;
    String desc;
    String status;

    //constructors
    public Scheme () {

    }

    public Scheme (String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public Scheme (int id, String title, String desc, String status) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.status = status;
    }

    //setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

