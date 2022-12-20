package com.cieep.pmdm_c_08_retrofit_ejercicio.conexiones;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {

    private static final String BASE_URL = "https://reqres.in";

    public static Retrofit getConexion() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
