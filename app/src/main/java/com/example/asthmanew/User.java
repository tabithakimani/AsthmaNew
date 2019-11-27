package com.example.asthmanew;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public String email;
    public String password;
    public Long phone;
    public String sex;
    public String sound_level;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getSound_level() {
        return sound_level;
    }

    public void setSound_level(String sound_level) {
        this.sound_level = sound_level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User(String email, String password, Long phone, String gender) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.sex = gender;
    }
}
