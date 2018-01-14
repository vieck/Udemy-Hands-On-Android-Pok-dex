package com.udemy.vieck.pokedex.Retrofit;

import com.udemy.vieck.pokedex.Models.Pokemon;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexAPIConverter {
    private static PokedexAPI pokedexAPI;
    private static String baseURL = "https://pokeapi.co/api/v2/";
    public static String spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    public static PokedexAPI getAPIInstance() {
        if (pokedexAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            pokedexAPI = retrofit.create(PokedexAPI.class);
        }

        return pokedexAPI;
    }

}
