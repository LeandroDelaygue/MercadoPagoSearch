package com.mp.busquedamercadopago.interfaces;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Leandro 29/07/2020.
 */




public interface IIntegrationMercadoPago {



    @Headers({
            "Content-Type: application/json"
    })


    @GET("products/search?status=active&site_id=MLA")
    Call<JsonObject> getProducts( @Query("q") String search);

    @GET("sites/MLA/search")
    Call<JsonObject> getCategories(@Query("category") String category);

}
