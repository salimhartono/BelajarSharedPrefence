package com.example.belajarsharedprefence.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("Sunset")
    private String sunset;

    @SerializedName("Asr")
    private String asr;

    @SerializedName("Isha")
    private String isha;

    @SerializedName("Fajr")
    private String fajr;

    @SerializedName("Dhuhr")
    private String dhuhr;

    @SerializedName("Maghrib")
    private String maghrib;

    @SerializedName("method")
    private List<String> method;

    @SerializedName("TengahMalam")
    private String tengahMalam;

    @SerializedName("Sunrise")
    private String sunrise;

    @SerializedName("DuapertigaMalam")
    private String duapertigaMalam;

    @SerializedName("SepertigaMalam")
    private String sepertigaMalam;

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public List<String> getMethod() {
        return method;
    }

    public void setMethod(List<String> method) {
        this.method = method;
    }

    public String getTengahMalam() {
        return tengahMalam;
    }

    public void setTengahMalam(String tengahMalam) {
        this.tengahMalam = tengahMalam;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getDuapertigaMalam() {
        return duapertigaMalam;
    }

    public void setDuapertigaMalam(String duapertigaMalam) {
        this.duapertigaMalam = duapertigaMalam;
    }

    public String getSepertigaMalam() {
        return sepertigaMalam;
    }

    public void setSepertigaMalam(String sepertigaMalam) {
        this.sepertigaMalam = sepertigaMalam;
    }

    @Override
    public String toString() {
        return
                "Data{" +
                        "sunset = '" + sunset + '\'' +
                        ",asr = '" + asr + '\'' +
                        ",isha = '" + isha + '\'' +
                        ",fajr = '" + fajr + '\'' +
                        ",dhuhr = '" + dhuhr + '\'' +
                        ",maghrib = '" + maghrib + '\'' +
                        ",method = '" + method + '\'' +
                        ",tengahMalam = '" + tengahMalam + '\'' +
                        ",sunrise = '" + sunrise + '\'' +
                        ",duapertigaMalam = '" + duapertigaMalam + '\'' +
                        ",sepertigaMalam = '" + sepertigaMalam + '\'' +
                        "}";
    }
}