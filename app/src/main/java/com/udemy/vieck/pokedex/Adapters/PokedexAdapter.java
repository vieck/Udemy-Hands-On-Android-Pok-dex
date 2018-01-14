package com.udemy.vieck.pokedex.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udemy.vieck.pokedex.Models.PokemonResource;
import com.udemy.vieck.pokedex.R;

import java.util.List;


public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder> {

    private Context context;
    private List<PokemonResource> pokemonResources;

    public PokedexAdapter(Context context, List<PokemonResource> pokemonResources) {
        this.context = context;
        this.pokemonResources = pokemonResources;
    }

    @Override
    public PokedexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_pokedex, parent, false);
        return new PokedexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokedexViewHolder holder, int position) {
        PokemonResource pokemonResource = pokemonResources.get(position);

        Picasso.with(context).load(pokemonResource.spriteURL).into(holder.pokemonImageView);

        holder.pokemonNameText.setText(pokemonResource.name);
    }

    @Override
    public int getItemCount() {
        return pokemonResources.size();
    }

    public class PokedexViewHolder extends RecyclerView.ViewHolder {

        private ImageView pokemonImageView;
        private TextView pokemonNameText;

        public PokedexViewHolder(View itemView) {
            super(itemView);
            pokemonImageView = (ImageView) itemView.findViewById(R.id.pokemon_image);
            pokemonNameText = (TextView) itemView.findViewById(R.id.pokemon_name);
        }
    }
}
