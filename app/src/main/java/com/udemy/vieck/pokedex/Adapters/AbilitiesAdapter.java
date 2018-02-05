package com.udemy.vieck.pokedex.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.udemy.vieck.pokedex.Models.PokemonAbility;
import com.udemy.vieck.pokedex.R;

import java.util.List;

public class AbilitiesAdapter extends RecyclerView.Adapter<AbilitiesAdapter.AbilityViewHolder> {

    private Context context;

    private List<PokemonAbility> abilities;

    public AbilitiesAdapter(Context context, List<PokemonAbility> abilities) {
        this.context = context;
        this.abilities = abilities;
    }

    @Override
    public AbilityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_pokemon_ability, parent, false);
        return new AbilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AbilityViewHolder holder, int position) {
        PokemonAbility pokemonAbility = abilities.get(position);
        holder.abilityName.setText(pokemonAbility.ability.name);
        holder.abilitySlot.setText(Integer.toString(pokemonAbility.slot));
        holder.isHiddenCheckbox.setChecked(pokemonAbility.is_hidden);
    }

    @Override
    public int getItemCount() {
        return abilities.size();
    }

    public class AbilityViewHolder extends RecyclerView.ViewHolder {

        private TextView abilityName;
        private TextView abilitySlot;
        private CheckBox isHiddenCheckbox;

        public AbilityViewHolder(View itemView) {
            super(itemView);
            abilityName = itemView.findViewById(R.id.ability_name);
            abilitySlot = itemView.findViewById(R.id.ability_slot);
            isHiddenCheckbox = itemView.findViewById(R.id.ability_checkbox);
        }


    }

}
