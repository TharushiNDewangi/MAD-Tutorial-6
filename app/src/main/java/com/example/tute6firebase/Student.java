package com.example.tute6firebase;

public class Student {

    private String id;
    private String name;
    private String address;
    private Integer contact;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getContact() {
        return contact;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }
}

