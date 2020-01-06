package com.example.myapplication5;

public class RoomInfo {private String uid;
    private String subject;

    public RoomInfo() {}
    public RoomInfo(String uid, String subject) {
        this.uid = uid;
        this.subject = subject;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }

    public void setsubject(String subject) {
        this.subject = subject;
    }

    public String getuid() {
        return uid;
    }

    public String getsubject() {
        return subject;
    }
}
