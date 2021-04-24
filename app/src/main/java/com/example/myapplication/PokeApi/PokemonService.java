package com.example.myapplication.PokeApi;

import com.example.myapplication.Model.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {

    @GET("pokemon")
    Call<PokemonRespuesta> getPokemons(@Query("limit") int limit,@Query("offset") int offset);
}
