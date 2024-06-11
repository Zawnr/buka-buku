package com.example.buka_buku.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String email;
    private String studentId;
    private String phoneNumber;

    public User(String name, String email, String studentId, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
