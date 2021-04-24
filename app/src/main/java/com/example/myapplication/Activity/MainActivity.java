package com.example.myapplication.Activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.PokemonAdapter;
import com.example.myapplication.Model.PokemonRespuesta;
import com.example.myapplication.R;
import com.example.myapplication.models.PokemonsViewModel;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    private PokemonAdapter pokemonAdapter;
    private RecyclerView recyclerView;
    private int offset;
    private boolean aptoParaCargar;
    private PokemonsViewModel pokemonsViewModel;
    private PokemonRespuesta respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confView();
    }

    private void confView(){
        pokemonsViewModel = ViewModelProviders.of(this).get(PokemonsViewModel.class);
        recyclerView = findViewById(R.id.recyclerview);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pokemonAdapter);

        pokemonsViewModel.getRespuestaMutableLiveData().observe(this, new Observer<PokemonRespuesta>() {
            @Override
            public void onChanged(PokemonRespuesta pokemonRespuesta) {
                if(pokemonRespuesta!=null){
                    respuesta = pokemonRespuesta;
                    pokemonAdapter.setResults(pokemonRespuesta.getResults());
                }else{
                    Log.e("ERROR OBSERVE","NO POKEMONES");
                }
            }
        });

        pokemonsViewModel.makeApiCall();

    }
}