package com.udemy.vieck.pokedex.Retrofit;

import com.udemy.vieck.pokedex.Models.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokedexAPI {
    @GET("pokemon")
    Call<List<Pokemon>> getAllPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<List<Pokemon>> getPokemonById(@Path("id") int id, @Query("limit") int limit, @Query("offset") int offset);
}