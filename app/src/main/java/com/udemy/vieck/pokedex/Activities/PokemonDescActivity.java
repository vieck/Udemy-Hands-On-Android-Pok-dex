package com.udemy.vieck.pokedex.Activities;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.squareup.picasso.Picasso;
import com.udemy.vieck.pokedex.Adapters.AbilitiesAdapter;
import com.udemy.vieck.pokedex.Adapters.MovesAdapter;
import com.udemy.vieck.pokedex.Adapters.StatsAdapter;
import com.udemy.vieck.pokedex.Models.Pokemon;
import com.udemy.vieck.pokedex.Models.PokemonAbility;
import com.udemy.vieck.pokedex.Models.PokemonMove;
import com.udemy.vieck.pokedex.Models.PokemonResource;
import com.udemy.vieck.pokedex.Models.PokemonResources;
import com.udemy.vieck.pokedex.Models.PokemonStat;
import com.udemy.vieck.pokedex.R;
import com.udemy.vieck.pokedex.Retrofit.PokedexAPIConverter;
import com.udemy.vieck.pokedex.databinding.ActivityPokemonDescBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDescActivity extends AppCompatActivity {

    private static String TAG = "POKEMON_DESC_ACTIVITY";

    private ActivityPokemonDescBinding binding;

    private int pokemonIndex;

    private AbilitiesAdapter abilitiesAdapter;
    private StatsAdapter statsAdapter;
    private MovesAdapter movesAdapter;

    SkeletonScreen abilitiesSkeleton, statsSkeleton, movesSkeleton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_desc);

        pokemonIndex = getIntent().getIntExtra("pokedexIndex", 1);

        skeletonScreenSetup();

        getPokemon();
    }

    private void skeletonScreenSetup() {
        LinearLayoutManager abilityManager = new LinearLayoutManager(this);
        LinearLayoutManager statsManager = new LinearLayoutManager(this);
        LinearLayoutManager moveManager = new LinearLayoutManager(this);

        binding.recyclerAbility.setLayoutManager(abilityManager);
        binding.recyclerStats.setLayoutManager(statsManager);
        binding.recyclerMoves.setLayoutManager(moveManager);

        abilitySkeletonSetup();
        statsSkeletonSetup();
        movesSkeletonSetup();

    }

    private void abilitySkeletonSetup() {
        abilitiesAdapter = new AbilitiesAdapter(this, new ArrayList<PokemonAbility>());

        abilitiesSkeleton = Skeleton.bind(binding.recyclerAbility)
                .adapter(abilitiesAdapter)
                .shimmer(true)
                .angle(20)
                .frozen(true)
                .load(R.layout.layout_default_item_skeleton)
                .show();
    }

    private void statsSkeletonSetup() {
        statsAdapter = new StatsAdapter(this, new ArrayList<PokemonStat>());

        statsSkeleton = Skeleton.bind(binding.recyclerStats)
                .adapter(statsAdapter)
                .shimmer(false)
                .frozen(true)
                .load(R.layout.layout_default_item_skeleton)
                .show();
    }

    private void movesSkeletonSetup() {
        movesAdapter = new MovesAdapter(this, new ArrayList<PokemonMove>());

        movesSkeleton = Skeleton.bind(binding.recyclerMoves)
                .adapter(movesAdapter)
                .shimmer(false)
                .frozen(true)
                .load(R.layout.layout_default_item_skeleton)
                .show();
    }

    private void getPokemon() {
        PokedexAPIConverter.getAPIInstance().getPokemonById(pokemonIndex).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), "You grabbed " + response.body().name, Toast.LENGTH_LONG).show();

                    createToolbar(response.body());
                    setViews(response.body());
                    setAbilityAdapter(response.body().abilities);
                    setStatAdapter(response.body().stats);
                    setMoveAdapter(response.body().moves);

                    binding.scrollView.scrollTo(0, 0 ); // Skeleton view causes scrollview to lock
                } else {
                    Log.e(TAG, "Response body null");
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                displayNetworkErrorSnackbar();
            }
        });
    }

    private void createToolbar(Pokemon pokemon) {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(pokemon.name);
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

    private void setAbilityAdapter(List<PokemonAbility> abilities) {
        abilitiesAdapter = new AbilitiesAdapter(this, abilities);
        binding.recyclerAbility.setAdapter(abilitiesAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerAbility.getContext(),
                LinearLayoutManager.VERTICAL);

        binding.recyclerAbility.addItemDecoration(dividerItemDecoration);
    }

    private void setStatAdapter(List<PokemonStat> stats) {
        statsAdapter = new StatsAdapter(this, stats);
        binding.recyclerStats.setAdapter(statsAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerStats.getContext(),
                LinearLayoutManager.VERTICAL);

        binding.recyclerStats.addItemDecoration(dividerItemDecoration);
    }

    private void setMoveAdapter(List<PokemonMove> moves) {
        movesAdapter = new MovesAdapter(this, moves);

        binding.recyclerMoves.setAdapter(movesAdapter);
        binding.recyclerMoves.setNestedScrollingEnabled(false);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerMoves.getContext(),
                LinearLayoutManager.VERTICAL);

        binding.recyclerMoves.addItemDecoration(dividerItemDecoration);
    }

    private void displayNetworkErrorSnackbar() {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), R.string.pokemon_details_error, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Refresh", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPokemon();
            }
        });
        snackbar.show();
    }
}
