package com.example.recycleviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModelJavaAdaptor extends RecyclerView.Adapter<ModelJavaAdaptor.ModelJavaAdaptorViewHolder> {

    ArrayList<ModelJava> list;

    public ModelJavaAdaptor(ArrayList<ModelJava> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ModelJavaAdaptorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.model_item_layout, parent, false);
        return new ModelJavaAdaptorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelJavaAdaptorViewHolder holder, int position) {
        ModelJava model = list.get(position);
        holder.name.setText(model.getName());
        holder.course.setText(model.getCourse());
        holder.progress.setText(model.getCompleted() + "%");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ModelJavaAdaptorViewHolder extends RecyclerView.ViewHolder {

        TextView name, course, progress;

        public ModelJavaAdaptorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            course = itemView.findViewById(R.id.course);
            progress = itemView.findViewById(R.id.progress);
        }
    }
}
