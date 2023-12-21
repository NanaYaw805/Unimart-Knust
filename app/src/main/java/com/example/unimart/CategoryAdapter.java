package com.example.unimart;

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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<CategoryModel> CategoryModelList;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModelList) {
        this.context = context;
        CategoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoryModel categoryModel = CategoryModelList.get(position);
        holder.cat_name.setText(categoryModel.category_name);
        holder.cat_image.setImageResource(categoryModel.category_image);

        // Set OnClickListener for cat_name
        holder.cat_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivityBasedOnCategoryName(categoryModel.category_name);
            }
        });

        holder.cat_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivityBasedOnCategoryName(categoryModel.category_name);
            }
        });

    }

    @Override
    public int getItemCount() {
        return CategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cat_image;
        TextView cat_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_image = itemView.findViewById(R.id.card_category_view);
            cat_name = itemView.findViewById(R.id.category_text);
        }
    }

    // Method to launch activity based on category name
    private void launchActivityBasedOnCategoryName(String categoryName) {
        Intent intent = null;

        // Check the category name and set the appropriate intent
        if (categoryName.contains("Breakfast")) {
            intent = new Intent(context, BreakfastActivity.class);
        } else if (categoryName.contains("Lunch")) {
            intent = new Intent(context, LunchActivities.class);
        } else if (categoryName.contains("Supper")) {
            intent = new Intent(context, SupperActivity.class);
        } else if (categoryName.contains("Fruits")) {
            intent = new Intent(context, FruitsActivity.class);
        } else if (categoryName.contains("Vegetables")) {
            intent = new Intent(context, VegetableActivity.class);
        } else if (categoryName.contains("KFC")) {
            intent = new Intent(context, KFCActivity.class);
        } else if (categoryName.contains("Pizzaman/Chickenman")) {
            intent = new Intent(context, PizzamanActivity.class);
        } else if (categoryName.contains("Pice")) {
            intent = new Intent(context, PiceActivity.class);
        }

        // Launch the intent if not null
        if (intent != null) {
            context.startActivity(intent);
        }
    }
}
