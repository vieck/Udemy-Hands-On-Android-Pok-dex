package com.udemy.vieck.pokedex.Activities;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.udemy.vieck.pokedex.Adapters.PokedexAdapter;
import com.udemy.vieck.pokedex.Models.Pokemon;
import com.udemy.vieck.pokedex.Models.PokemonResource;
import com.udemy.vieck.pokedex.Models.PokemonResources;
import com.udemy.vieck.pokedex.R;
import com.udemy.vieck.pokedex.Retrofit.PokedexAPIConverter;
import com.udemy.vieck.pokedex.databinding.ActivityPokedexBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexActivity extends AppCompatActivity {

    private static String TAG = "MAIN_ACTIVITY";

    private ActivityPokedexBinding binding;

    private PokedexAdapter pokedexAdapter;

    private SkeletonScreen skeletonScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokedex);

        skeletonScreenSetup(new ArrayList<PokemonResource>());

        getPokemon();

        swipeRefreshSetup();
    }

    private void skeletonScreenSetup(List<PokemonResource> pokemonResources) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.recyclerPokedex.setLayoutManager(layoutManager);

        pokedexAdapter = new PokedexAdapter(this, pokemonResources);
        skeletonScreen = Skeleton.bind(binding.recyclerPokedex)
                .adapter(pokedexAdapter)
                .shimmer(true)
                .angle(20)
                .frozen(false)
                .duration(1200)
                .load(R.layout.card_pokedex_skeleton)
                .show();
    }

    private void showSkeletonScreen() {
        skeletonScreen.show();
    }

    private void updateAdapter(List<PokemonResource> pokemonResources) {
        skeletonScreen.hide();
        pokedexAdapter = new PokedexAdapter(PokedexActivity.this, pokemonResources);
        binding.recyclerPokedex.setAdapter(pokedexAdapter);
    }

    private void getPokemon() {
        PokedexAPIConverter.getAPIInstance().getAllPokemon(5, 0).enqueue(new Callback<PokemonResources>() {
            @Override
            public void onResponse(Call<PokemonResources> call, Response<PokemonResources> response) {
                skeletonScreen.hide();
                if (response.body() != null) {
                    displayPokemonSnackbar(response.body().count + " pokemon. You grabbed " + response.body().results.size());

                    for (PokemonResource pokemonResource : response.body().results) {
                        pokemonResource.pokedexNumber = Integer.parseInt(pokemonResource.url.substring(pokemonResource.url.lastIndexOf('/') - 1, pokemonResource.url.lastIndexOf('/')));
                        pokemonResource.spriteURL = PokedexAPIConverter.spriteURL + pokemonResource.pokedexNumber + ".png";
                    }
                    List<PokemonResource> pokemonResources = response.body().results;
                    updateAdapter(pokemonResources);

                } else {
                    Log.e(TAG, "Response body null");
                }
            }

            @Override
            public void onFailure(Call<PokemonResources> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                Toast.makeText(getApplicationContext(), "Issue getting results " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                displayEmptyStateView();
                displayRefreshSnackbar();
            }
        });
    }

    private void swipeRefreshSetup() {
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showSkeletonScreen();
                getPokemon();
                stopSwipeRefresh();
            }
        });
    }

    private void stopSwipeRefresh() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    private void displayPokemonSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void displayRefreshSnackbar() {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), R.string.pokemon_error, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Refresh", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPokemon();
            }
        });
        snackbar.show();
    }

    private void displayEmptyStateView() {
        binding.swipeRefreshLayout.setVisibility(View.GONE);
        binding.recyclerPokedex.setVisibility(View.GONE);
        binding.emptyStateContainer.setVisibility(View.VISIBLE);
    }

    private void hideEmptyStateView() {
        binding.swipeRefreshLayout.setVisibility(View.VISIBLE);
        binding.recyclerPokedex.setVisibility(View.VISIBLE);
        binding.emptyStateContainer.setVisibility(View.GONE);
    }
}
