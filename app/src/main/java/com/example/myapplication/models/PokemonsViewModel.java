package com.example.myapplication.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.Pokemon;
import com.example.myapplication.Model.PokemonRespuesta;
import com.example.myapplication.PokeApi.PokemonService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonsViewModel extends ViewModel {

    MutableLiveData<PokemonRespuesta> respuestaMutableLiveData;
    PokemonService pokemonService = common.conexionCuevana();

    public PokemonsViewModel() {
        respuestaMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<PokemonRespuesta> getRespuestaMutableLiveData() {
        return respuestaMutableLiveData;
    }

    public void makeApiCall() {
        Call<PokemonRespuesta> pokemonServiceCall = pokemonService.getPokemons(100,0);
        pokemonServiceCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        respuestaMutableLiveData.postValue(response.body());
                    }
                } else {
                    Log.e("ERROR RESPONSE", "on response" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {

                Log.e("ON FAILURE", "on failure" + t.getMessage());
            }
        });
    }
}
