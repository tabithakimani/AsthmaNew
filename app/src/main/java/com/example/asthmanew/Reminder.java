package com.example.asthmanew;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Reminder {
    public String date;
    public String medicine;
    public String dose;
    public String name;
    public String sound_level;

    public String sex;


    public String getSound_level() {
        return sound_level;
    }

    public void setSound_level(String sound_level) {
        this.sound_level = sound_level;
    }

    public Reminder(String date, String medicine, String dose, String name, String sex, String sound_level) {
        this.date = date;
        this.medicine = medicine;
        this.dose = dose;
        this.name = name;
        this.sex = sex;
        this.sound_level = sound_level;
    }

    public Reminder() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}

