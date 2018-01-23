package com.udemy.vieck.pokedex.Models;

import java.util.List;

public class Pokemon {
   public int id;
   public String name;
   public int base_experience;
   public int height;
   public boolean is_default;
   public int order;
   public int weight;
   public List<PokemonAbility> abilities;
   public PokemonSprite sprites;

}
