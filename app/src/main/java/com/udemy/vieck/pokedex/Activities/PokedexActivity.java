package com.udemy.vieck.pokedex.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.udemy.vieck.pokedex.Models.Pokemon;
import com.udemy.vieck.pokedex.Models.PokemonResource;
import com.udemy.vieck.pokedex.Models.PokemonResources;
import com.udemy.vieck.pokedex.R;
import com.udemy.vieck.pokedex.Retrofit.PokedexAPIConverter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexActivity extends AppCompatActivity {

    private static String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
        getPokemon();
    }

    private void getPokemon() {
        PokedexAPIConverter.getAPIInstance().getAllPokemon(5,0).enqueue(new Callback<PokemonResources>() {
            @Override
            public void onResponse(Call<PokemonResources> call, Response<PokemonResources> response) {
                Toast.makeText(getApplicationContext(), response.body().count + " pokemon. You grabbed " + response.body().results.size(), Toast.LENGTH_LONG).show();
                for (PokemonResource pokemon : response.body().results) {
                    Log.d(TAG, pokemon.name);
                }
            }

            @Override
            public void onFailure(Call<PokemonResources> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                Toast.makeText(getApplicationContext(), "Issue getting results " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
