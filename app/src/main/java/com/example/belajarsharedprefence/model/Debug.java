package com.example.belajarsharedprefence.model;

import com.google.gson.annotations.SerializedName;

public class Debug {

    @SerializedName("sunrise")
    private String sunrise;

    @SerializedName("sunset")
    private String sunset;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return
                "Debug{" +
                        "sunrise = '" + sunrise + '\'' +
                        ",sunset = '" + sunset + '\'' +
                        "}";
    }
}