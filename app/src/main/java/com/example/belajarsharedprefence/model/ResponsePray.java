package com.example.belajarsharedprefence.model;

import com.google.gson.annotations.SerializedName;

public class ResponsePray {

    @SerializedName("debug")
    private Debug debug;

    @SerializedName("data")
    private Data data;

    @SerializedName("location")
    private Location location;

    @SerializedName("time")
    private Time time;

    @SerializedName("status")
    private String status;

    public Debug getDebug() {
        return debug;
    }

    public void setDebug(Debug debug) {
        this.debug = debug;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "ResponsePray{" +
                        "debug = '" + debug + '\'' +
                        ",data = '" + data + '\'' +
                        ",location = '" + location + '\'' +
                        ",time = '" + time + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}