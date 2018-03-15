package com.rest.ws;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitConfig {

    private static final Retrofit retrofit
             = new Retrofit.Builder()
            .baseUrl("https://www.mercadobitcoin.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static ServiceBitcoin getServiceBitcoin() {
        return retrofit.create(ServiceBitcoin.class);
    }
}
