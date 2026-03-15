package com.dev.simpsonapi.db;

public class Character {
    private int id;
    private String name;
    private int age;
    private String birthdate;
    private String image;
    private String occupation;
    private String status;

    public Character(int id, String name, int age, String birthdate, String image, String occupation, String status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthdate = birthdate;
        this.image = image;
        this.occupation = occupation;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirtdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
