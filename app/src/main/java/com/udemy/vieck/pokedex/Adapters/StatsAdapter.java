package com.udemy.vieck.pokedex.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.udemy.vieck.pokedex.Models.PokemonAbility;
import com.udemy.vieck.pokedex.Models.PokemonStat;
import com.udemy.vieck.pokedex.R;

import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {

    private Context context;

    private List<PokemonStat> stats;

    public StatsAdapter(Context context, List<PokemonStat> stats) {
        this.context = context;
        this.stats = stats;
    }

    @Override
    public StatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_pokemon_stat, parent, false);
        return new StatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatsViewHolder holder, int position) {
        PokemonStat pokemonStat = stats.get(position);
        holder.statName.setText(pokemonStat.stat.name);
        holder.statValue.setText(Integer.toString(pokemonStat.base_stat));
        holder.statEffort.setText(Integer.toString(pokemonStat.effort));
    }

    @Override
    public int getItemCount() {
        return stats.size();
    }

    public class StatsViewHolder extends RecyclerView.ViewHolder {

        private TextView statName;
        private TextView statValue;
        private TextView statEffort;

        public StatsViewHolder(View itemView) {
            super(itemView);
            statName = itemView.findViewById(R.id.stat_name);
            statValue = itemView.findViewById(R.id.stat_value);
            statEffort = itemView.findViewById(R.id.stat_effort);
        }


    }

}
