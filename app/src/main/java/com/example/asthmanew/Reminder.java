package com.example.asthmanew;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Reminder {
    public String date;
    public String medicine;
    public String dose;



    public Reminder() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Reminder(String date, String medicine, String dose) {
        this.date = date;
        this.medicine = medicine;
        this.dose = dose;
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

