package com.example.unimart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BreakfastOthersAdapter extends RecyclerView.Adapter<BreakfastOthersAdapter.ViewHolder> {
    Context context;
    ArrayList<HallModel> hallModelArrayList;

    public BreakfastOthersAdapter(Context context, ArrayList<HallModel> hallModelArrayList) {
        this.context = context;
        this.hallModelArrayList = hallModelArrayList;
    }

    public interface OnItemClickListener {
        void onItemClick(HallModel hallModel);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.breakfast_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HallModel hallModel = hallModelArrayList.get(position);
        holder.f.setText(hallModel.model_food_name);
        holder.l.setText(hallModel.model_food_location);
        holder.t.setText(hallModel.model_food_available_time);
        holder.v.setText(hallModel.vendor_id);
        holder.i.setImageResource(hallModel.advert_images);

        // Set click listener for each item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(hallModel);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return hallModelArrayList.size();
    }

    public void setFilteredList(ArrayList<HallModel> breakfastNewFilter) {
        this.hallModelArrayList = breakfastNewFilter;
            notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView i;
        TextView f,l,v,t;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            i = itemView.findViewById(R.id.break_image);
            f = itemView.findViewById(R.id.breakfast_food_name_view);
            v= itemView.findViewById(R.id.vendor_id_viewxx);
            l = itemView.findViewById(R.id.location_breakfast_view);
            t = itemView.findViewById(R.id.time_available_view);

            // Set click listener for the entire item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Call the onItemClick method when an item is clicked
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(hallModelArrayList.get(position));
                        }
                    }
                }
            });

        }
    }
}
