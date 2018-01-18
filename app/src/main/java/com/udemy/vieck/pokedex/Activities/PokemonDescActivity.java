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

public class PokemonDescActivity extends AppCompatActivity {

    private static String TAG = "POKEMON_DESC_ACTIVITY";

    private int pokemonIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_desc);

       pokemonIndex = getIntent().getIntExtra("pokedexIndex", 1);

       getPokemon();
    }

    private void getPokemon() {
        PokedexAPIConverter.getAPIInstance().getPokemonById(pokemonIndex).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), "You grabbed " + response.body().name, Toast.LENGTH_LONG).show();

                } else {
                    Log.e(TAG, "Response body null");
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                Toast.makeText(getApplicationContext(), "Issue getting results " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
