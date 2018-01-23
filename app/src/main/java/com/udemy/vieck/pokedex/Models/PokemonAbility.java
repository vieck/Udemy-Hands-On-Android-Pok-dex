package com.udemy.vieck.pokedex.Models;

public class PokemonAbility {
    public boolean is_hidden;
    public int slot;
    public Ability ability;

    public class Ability {
        public String url;
        public String name;
    }
}