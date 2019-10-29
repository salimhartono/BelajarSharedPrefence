package com.example.belajarsharedprefence.model;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("address")
    private String address;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return
                "Location{" +
                        "address = '" + address + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }
}