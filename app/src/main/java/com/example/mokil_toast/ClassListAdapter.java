package com.example.mokil_toast;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {
    private ClassData classData;
    private OnItemTouchListener listener;

    ClassListAdapter(ClassData classData) {
        this.classData = classData;
    }

    public void setOnItemTouchListener(OnItemTouchListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassListAdapter.ViewHolder holder, final int position) {
        String classText = classData.results[position].class_number + "반";
        String winText = "이긴 횟수: " + classData.results[position].win;
        holder.classNumber.setText(classText);
        holder.win.setText(winText);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClassInfoActivity.class);
                int classNumberValue = Integer.parseInt(classData.results[position].class_number);
                int winValue = Integer.parseInt(classData.results[position].win);

                intent.putExtra("classNumber", classNumberValue);
                intent.putExtra("win", winValue);

                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ClassListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_list_item, parent, false);
        return new ViewHolder(v);
    }

    public interface OnItemTouchListener {
        void onItemClick(View v, int position);
    }

    @Override
    public int getItemCount() {
        return classData.results.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView classNumber, win;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);

            classNumber = itemView.findViewById(R.id.tv_class);
            win = itemView.findViewById(R.id.tv_win);
        }
    }
}
