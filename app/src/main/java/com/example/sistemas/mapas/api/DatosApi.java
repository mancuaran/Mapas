package com.example.sistemas.mapas.api;

import com.example.sistemas.mapas.models.Cai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sistemas on 27/10/17.
 */

public interface DatosApi {

    @GET("wat4-bzdx.json")
    Call<List<Cai>> obtenerListaCai();
}

