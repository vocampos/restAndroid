package com.rest.ws;

import com.rest.modelo.Cotacao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServiceBitcoin {

    @GET("{moeda}/ticker")
    Call<Cotacao> buscarCotacao(@Path("moeda") String moeda);
}
