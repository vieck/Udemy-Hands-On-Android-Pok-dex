package com.udemy.vieck.pokedex.Adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udemy.vieck.pokedex.Models.PokemonMove;
import com.udemy.vieck.pokedex.Models.PokemonStat;
import com.udemy.vieck.pokedex.R;

import java.util.List;

public class MovesAdapter extends RecyclerView.Adapter<MovesAdapter.MovesViewHolder> {

    private Context context;

    private List<PokemonMove> moves;

    public MovesAdapter(Context context, List<PokemonMove> moves) {
        this.context = context;
        this.moves = moves;
    }

    @Override
    public MovesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_pokemon_move, parent, false);
        return new MovesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovesViewHolder holder, int position) {
        PokemonMove pokemonMove = moves.get(position);
        holder.moveName.setText(pokemonMove.move.name);
    }

    @Override
    public int getItemCount() {
        return moves.size();
    }

    public class MovesViewHolder extends RecyclerView.ViewHolder {

        private TextView moveName;

        public MovesViewHolder(View itemView) {
            super(itemView);
            moveName = itemView.findViewById(R.id.move_name);
        }


    }

}
