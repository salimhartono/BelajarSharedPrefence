package com.example.belajarsharedprefence;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

    private String nama;
    private String email;
    private String pasword;
    private String kelamin;
    private String agrew;
    private boolean statusLogin;

    public boolean isStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(boolean statusLogin) {
        this.statusLogin = statusLogin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getAgrew() {
        return agrew;
    }

    public void setAgrew(String agrew) {
        this.agrew = agrew;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.email);
        dest.writeString(this.pasword);
        dest.writeString(this.kelamin);
        dest.writeString(this.agrew);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.nama = in.readString();
        this.email = in.readString();
        this.pasword = in.readString();
        this.kelamin = in.readString();
        this.agrew = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
