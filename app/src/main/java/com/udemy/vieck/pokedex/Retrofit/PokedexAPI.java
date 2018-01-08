package com.udemy.vieck.pokedex.Retrofit;

import com.udemy.vieck.pokedex.Models.Pokemon;
import com.udemy.vieck.pokedex.Models.PokemonResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokedexAPI {
    @GET("pokemon")
    Call<PokemonResource> getAllPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<PokemonResource> getPokemonById(@Path("id") int id, @Query("limit") int limit, @Query("offset") int offset);
}
