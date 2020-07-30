package com.mp.busquedamercadopago.services;

import com.google.gson.JsonObject;
import com.mp.busquedamercadopago.classes.ToStringConverterFactory;
import com.mp.busquedamercadopago.interfaces.IIntegrationMercadoPago;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Leandro on 14/02/2017.
 */

public class MercadoPagoServices implements IIntegrationMercadoPago {


    public MercadoPagoServices( ) {
    }



    public Retrofit CustomMPServices(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        Retrofit serviceMP   = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(ToStringConverterFactory.create())
                .client(okHttpClient)
                .build();

        return serviceMP;

    }


    @Override
    public Call<JsonObject> getProducts(String search) {
        return null;
    }

    @Override
    public Call<JsonObject> getCategories(String category) {
        return null;
    }
}
