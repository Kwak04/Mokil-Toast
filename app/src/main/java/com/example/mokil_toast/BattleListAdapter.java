package com.example.mokil_toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BattleListAdapter extends RecyclerView.Adapter<BattleListAdapter.ViewHolder> {
    private BattleData battleData;

    BattleListAdapter(BattleData battleData) {
        this.battleData = battleData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.battle_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.class1.setText(battleData.results[position].class1);
        holder.class2.setText(battleData.results[position].class2);
        holder.score1.setText(battleData.results[position].score1);
        holder.score2.setText(battleData.results[position].score2);
    }

    @Override
    public int getItemCount() {
        return battleData.results.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView class1, class2, score1, score2;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            class1 = itemView.findViewById(R.id.tv_class1);
            class2 = itemView.findViewById(R.id.tv_class2);
            score1 = itemView.findViewById(R.id.tv_score1);
            score2 = itemView.findViewById(R.id.tv_score2);
        }
    }
}