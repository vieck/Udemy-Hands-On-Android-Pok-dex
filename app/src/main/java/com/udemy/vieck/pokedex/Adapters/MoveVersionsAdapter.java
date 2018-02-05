package com.udemy.vieck.pokedex.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udemy.vieck.pokedex.Models.PokemonMove;
import com.udemy.vieck.pokedex.Models.PokemonMoveVersion;
import com.udemy.vieck.pokedex.R;

import java.util.List;

public class MoveVersionsAdapter extends RecyclerView.Adapter<MoveVersionsAdapter.MoveVersionsViewHolder> {

    private Context context;

    private List<PokemonMoveVersion> moveVersions;

    public MoveVersionsAdapter(Context context, List<PokemonMoveVersion> moveVersions) {
        this.context = context;
        this.moveVersions = moveVersions;
    }

    @Override
    public MoveVersionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_pokemon_move_version, parent, false);
        return new MoveVersionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoveVersionsViewHolder holder, int position) {
        PokemonMoveVersion moveVersion = moveVersions.get(position);
        holder.versionName.setText(moveVersion.version_group.name);
        holder.moveLearnMethod.setText(moveVersion.move_learn_method.name);
        holder.learnedLevel.setText(Integer.toString(moveVersion.level_learned_at));
    }

    @Override
    public int getItemCount() {
        return moveVersions.size();
    }

    public class MoveVersionsViewHolder extends RecyclerView.ViewHolder {

        private TextView versionName;
        private TextView moveLearnMethod;
        private TextView learnedLevel;

        public MoveVersionsViewHolder(View itemView) {
            super(itemView);
            versionName = itemView.findViewById(R.id.version_name);
            moveLearnMethod = itemView.findViewById(R.id.move_learn_method);
            learnedLevel = itemView.findViewById(R.id.learned_at);
        }


    }

}
