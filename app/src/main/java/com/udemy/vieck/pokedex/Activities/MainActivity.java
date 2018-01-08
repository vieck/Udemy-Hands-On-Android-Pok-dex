package com.udemy.vieck.pokedex.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.udemy.vieck.pokedex.Models.Pokemon;
import com.udemy.vieck.pokedex.Models.PokemonResource;
import com.udemy.vieck.pokedex.R;
import com.udemy.vieck.pokedex.Retrofit.PokedexAPI;
import com.udemy.vieck.pokedex.Retrofit.PokedexAPIConverter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPokemon();
    }

    private void getPokemon() {
        PokedexAPIConverter.getAPIInstance().getAllPokemon(20,0).enqueue(new Callback<PokemonResource>() {
            @Override
            public void onResponse(Call<PokemonResource> call, Response<PokemonResource> response) {
                for (Pokemon pokemon : response.body().results) {
                    Log.d(TAG, pokemon.name);
                }
            }

            @Override
            public void onFailure(Call<PokemonResource> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
