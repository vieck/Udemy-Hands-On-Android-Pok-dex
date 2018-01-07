package com.udemy.vieck.pokedex.Retrofit;

import retrofit2.Retrofit;

public class PokedexAPIConverter {
    private static PokedexAPI pokedexAPI;
    private static String baseURL = "https://pokeapi.co/api/v2/";

    public static PokedexAPI getAPIInstance() {
        if (pokedexAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .build();
        }
    }

}
