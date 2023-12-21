package com.example.unimart;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.VewHolder> {
    ArrayList<String> food_name_not,food_cost_not;
    ArrayList<Integer> food_image_not;
    Context context;
    public interface OnItemClickListener {
        void onCancelOrderClick(int position);
    }

    private final OnItemClickListener listener;

    public NotificationAdapter(ArrayList<String> food_name_not, ArrayList<String> food_cost_not, ArrayList<Integer> food_image_not, Context context,OnItemClickListener listener) {
        this.food_name_not = food_name_not;
        this.food_cost_not = food_cost_not;
        this.food_image_not = food_image_not;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotificationAdapter.VewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.notification_recycler,parent,false);
        return new VewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.VewHolder holder, int position) {

        holder.foodName.setText(String.valueOf(food_name_not.get(position)));
        try {
            holder.g_image.setImageResource(food_image_not.get(position));
        } catch (Exception e) {
            // Handle the exception, e.g., set a default image or show an error placeholder
            holder.g_image.setImageResource(R.drawable.logos);
        }

    }



    @Override
    public int getItemCount() {
        return food_name_not.size();
    }

    public static class VewHolder extends RecyclerView.ViewHolder {
        ImageView g_image;
        TextView foodName,delivery_complete;
        public VewHolder(@NonNull View itemView) {
            super(itemView);

            g_image = itemView.findViewById(R.id.notification_image);
            foodName = itemView.findViewById(R.id.food_name_notification);

        }
    }



}
