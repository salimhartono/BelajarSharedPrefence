package com.example.belajarsharedprefence.servie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCLieent  {
    public static Retrofit getJadwal(){
        return new Retrofit.Builder()
                .baseUrl("https://time.siswadi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static BaseApiService getJadwalService(){
        return getJadwal().create(BaseApiService.class);
    }
}
