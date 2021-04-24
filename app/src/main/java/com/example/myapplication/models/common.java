package com.example.myapplication.models;

import android.util.Log;

import com.example.myapplication.PokeApi.PokemonService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class common {

    private static Retrofit retrofit;

    public static PokemonService conexionCuevana() {
        if (retrofit == null) {
            Log.i("CommonService", "Se creo la conexión con la página web");
            retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(PokemonService.class);
    }
}
