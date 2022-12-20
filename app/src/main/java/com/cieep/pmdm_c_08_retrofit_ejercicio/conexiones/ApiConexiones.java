package com.cieep.pmdm_c_08_retrofit_ejercicio.conexiones;

import com.cieep.pmdm_c_08_retrofit_ejercicio.modelos.Respuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiConexiones {

    @GET("/api/users?")
    Call<Respuesta> getUsers(@Query("page") String pagina);

}
