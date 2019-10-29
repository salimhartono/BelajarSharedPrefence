package com.example.belajarsharedprefence.model;

import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("date")
    private String date;

    @SerializedName("offset")
    private int offset;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("time")
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return
                "Time{" +
                        "date = '" + date + '\'' +
                        ",offset = '" + offset + '\'' +
                        ",timezone = '" + timezone + '\'' +
                        ",time = '" + time + '\'' +
                        "}";
    }
}