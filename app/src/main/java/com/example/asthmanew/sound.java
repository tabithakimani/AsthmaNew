package com.example.asthmanew;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class sound {

    public String sound_level;
    public String sound_level2;

    public sound(String sound_level, String sound_level2) {
        this.sound_level = sound_level;
        this.sound_level2 = sound_level2;
    }
    public sound(){

    }

    public String getSound_level2() {
        return sound_level2;
    }

    public void setSound_level2(String sound_level2) {
        this.sound_level2 = sound_level2;
    }

    public String getSound_level() {
        return sound_level;
    }

    public void setSound_level(String sound_level) {
        this.sound_level = sound_level;
    }


}
