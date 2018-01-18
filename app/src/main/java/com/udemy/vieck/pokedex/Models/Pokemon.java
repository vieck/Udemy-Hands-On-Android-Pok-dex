package com.udemy.vieck.pokedex.Models;

public class Pokemon {
   public int id;
   public String name;
   public int base_experience;
   public int height;
   public boolean is_default;
   public int order;
   public int weight;
   public PokemonSprite sprites;

   public class PokemonSprite {
      public String front_default;
      public String front_female;
      public String back_default;
      public String back_female;
   }

}
