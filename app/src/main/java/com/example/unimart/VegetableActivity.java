package com.example.unimart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class VegetableActivity extends AppCompatActivity implements BreakfastOthersAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    SearchView searchView;
    BreakfastOthersAdapter breakfastOthersAdapter;
    ArrayList<HallModel> breakItems;

    String[] fruits_food_names = new String[]
            {
                    "Yam",  "Pepper",  "Lettuce", "Cabbage", "Plantain(Ripe)","Plantain(green)","Onion",
                    "Ginger","Garlic","Okro"
            };

    int[] fruits_images = new int[]
            {
                    R.drawable.yamimages,  R.drawable.pepper,  R.drawable.lettuce, R.drawable.carbage, R.drawable.plantaingreen,
                    R.drawable.plantain,R.drawable.onion,R.drawable.ginger,R.drawable.garlic,R.drawable.okro

            };

    String[] fruits_food_times = new String[]
            {
                    "9:30am-8:00pm", "9:30am-8:00pm", "9:30am-8:00pm", "9:30am-8:00pm",
                    "9:30am-8:00pm", "9:30am-8:00pm", "9:30am-8:00pm", "9:30am-8:00pm",
                    "9:30am-8:00pm", "9:30am-8:00pm"

            };

    String[] fruits_vendor_ids = new String[]
            {
                    "Unimart Original",  "Unimart Original",  "Unimart Original",  "Unimart Original",  "Unimart Original",
                    "Unimart Original",  "Unimart Original",  "Unimart Original",  "Unimart Original",  "Unimart Original",
            };

    String[] location = new String[]
            {
                    "KNUST","KNUST","KNUST","KNUST","KNUST","KNUST",
                    "KNUST","KNUST","KNUST","KNUST","KNUST","KNUST"
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable);

        recyclerView = findViewById(R.id.vegetable_recycler);
        searchView = findViewById(R.id.search_view_vegetable);

        breakItems = new ArrayList<>();



        breakfastOthersAdapter = new BreakfastOthersAdapter(VegetableActivity.this, breakItems);
        breakfastOthersAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(VegetableActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(breakfastOthersAdapter);
        for (int j = 0; j < fruits_food_names.length; j++) {
            HallModel categoryModel = new HallModel(fruits_food_names[j], location[j], fruits_food_times[j], fruits_images[j], fruits_vendor_ids[j]);
            breakItems.add(categoryModel);
            breakfastOthersAdapter.notifyDataSetChanged();
        }

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

    }

    private boolean filterList(String text) {
        ArrayList<HallModel> breakfast_new_filter = new ArrayList<>();


        //unity hall new filter
        for (HallModel breakfast : breakItems) {
            if (breakfast.model_food_name.toLowerCase().contains(text.toLowerCase())) {
                breakfast_new_filter.add(breakfast);
            }
        }

        if (breakfast_new_filter.isEmpty()) {
            Toast.makeText(VegetableActivity.this, "Sorry! cannot find the food here", Toast.LENGTH_SHORT).show();
        } else {
            breakfastOthersAdapter.setFilteredList(breakfast_new_filter);

        }
        return true;
    }

    @Override
    public void onItemClick(HallModel hallModel) {
        Intent intent = new Intent(VegetableActivity.this, ShowDetailsActivity.class);
        intent.putExtra("TITLE", hallModel.model_food_name);
        intent.putExtra("IMAGE", hallModel.advert_images);
        intent.putExtra("ID",hallModel.vendor_id);
        startActivity(intent);
    }
}