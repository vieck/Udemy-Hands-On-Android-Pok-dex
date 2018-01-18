package com.udemy.vieck.pokedex.Activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udemy.vieck.pokedex.Models.Pokemon;
import com.udemy.vieck.pokedex.Models.PokemonResource;
import com.udemy.vieck.pokedex.Models.PokemonResources;
import com.udemy.vieck.pokedex.R;
import com.udemy.vieck.pokedex.Retrofit.PokedexAPIConverter;
import com.udemy.vieck.pokedex.databinding.ActivityPokemonDescBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDescActivity extends AppCompatActivity {

    private static String TAG = "POKEMON_DESC_ACTIVITY";

    private ActivityPokemonDescBinding binding;

    private int pokemonIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_desc);

        pokemonIndex = getIntent().getIntExtra("pokedexIndex", 1);

        getPokemon();
    }

    private void getPokemon() {
        PokedexAPIConverter.getAPIInstance().getPokemonById(pokemonIndex).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), "You grabbed " + response.body().name, Toast.LENGTH_LONG).show();
                    setViews(response.body());
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

    private void setViews(Pokemon pokemon) {
        Picasso.with(this).load(pokemon.sprites.back_default).into(binding.backDefaultImage);
        Picasso.with(this).load(pokemon.sprites.front_default).into(binding.frontDefaultImage);
        if (pokemon.sprites.front_female != null) {
            Picasso.with(this).load(pokemon.sprites.back_female).into(binding.backFemaleImage);
            Picasso.with(this).load(pokemon.sprites.front_female).into(binding.frontFemaleImage);
        } else {
            binding.frontFemaleImage.setVisibility(View.GONE);
            binding.backFemaleImage.setVisibility(View.GONE);
        }

    }
}
