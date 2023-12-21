package com.example.unimart;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HallAdapter extends RecyclerView.Adapter<HallAdapter.ViewHolder>{
    Context context;
    ArrayList<HallModel> hallAdapterViews;


    public HallAdapter(Context context, ArrayList<HallModel> hallAdapterViews) {
        this.context = context;
        this.hallAdapterViews = hallAdapterViews;
    }

    @NonNull
    @Override
    public HallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_halls,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HallAdapter.ViewHolder holder, int position) {
        HallModel hallModel = hallAdapterViews.get(position);
        holder.food_name.setText(hallModel.model_food_name);
        holder.food_image.setImageResource(hallModel.advert_images);
        holder.food_availability.setText(hallModel.model_food_available_time);
        holder.food_location.setText(hallModel.model_food_location);
        holder.vendor_ids.setText(hallModel.vendor_id);


        holder.food_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                in.putExtra("TITLE", hallModel.model_food_name);
                in.putExtra("IMAGE", hallModel.advert_images);
                in.putExtra("ID", hallModel.vendor_id);
                startActivity(context, in, null);
            }
        });

        holder.food_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                in.putExtra("TITLE", hallModel.model_food_name);
                in.putExtra("IMAGE", hallModel.advert_images);
                in.putExtra("ID", hallModel.vendor_id);
                startActivity(context, in, null);
            }
        });

        holder.food_availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                in.putExtra("TITLE", hallModel.model_food_name);
                in.putExtra("IMAGE", hallModel.advert_images);
                in.putExtra("ID", hallModel.vendor_id);
                startActivity(context, in, null);
            }
        });

        holder.food_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                in.putExtra("TITLE", hallModel.model_food_name);
                in.putExtra("IMAGE", hallModel.advert_images);
                in.putExtra("ID", hallModel.vendor_id);
                startActivity(context, in, null);
            }
        });

        holder.vendor_ids.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                in.putExtra("TITLE", hallModel.model_food_name);
                in.putExtra("IMAGE", hallModel.advert_images);
                in.putExtra("ID", hallModel.vendor_id);
                startActivity(context, in, null);
            }
        });



    }



    @Override
    public int getItemCount() {
        return hallAdapterViews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView food_image;
        TextView food_name,food_location,food_availability,vendor_ids;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            food_image = itemView.findViewById(R.id.food_image_view);
            food_name = itemView.findViewById(R.id.food_name_view);
            food_location = itemView.findViewById(R.id.location_view);
            food_availability = itemView.findViewById(R.id.available_time_view);
            vendor_ids = itemView.findViewById(R.id.vendor_id_view);


        }
    }

    public void setFilteredList(ArrayList<HallModel> filteredList){
        this.hallAdapterViews = filteredList;
        notifyDataSetChanged();
    }
}
