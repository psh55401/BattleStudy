package com.example.myapplication5;

public class UserInfo {
    private String name;
    private String gender;
    private String point;
    private String subject;
    public UserInfo(String name, String gender, String point){
        this.name = name;
        this.gender = gender;
        this.point = point;

    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getGender(){
        return this.gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getPoint(){
        return this.point;
    }
    public void setPoint(String point){
        this.point = point;
    }
}
