package com.example.mokil_toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {
    private ClassData classData;

    ClassListAdapter(ClassData classData) {
        this.classData = classData;
    }

    @NonNull
    @Override
    public ClassListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassListAdapter.ViewHolder holder, int position) {
        String classText = classData.results[position].class_number + "반";
        String winText = "이긴 횟수: " + classData.results[position].win;
        holder.classNumber.setText(classText);
        holder.win.setText(winText);
    }

    @Override
    public int getItemCount() {
        return classData.results.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView classNumber, win;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            classNumber = itemView.findViewById(R.id.tv_class);
            win = itemView.findViewById(R.id.tv_win);
        }
    }
}
