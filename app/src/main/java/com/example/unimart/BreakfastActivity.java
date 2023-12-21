package com.example.unimart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class BreakfastActivity extends AppCompatActivity implements BreakfastOthersAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    SearchView searchView;
    BreakfastOthersAdapter breakfastOthersAdapter;
    ArrayList<HallModel> breakItems;

    String[] breakfast_food_names = new String[]
            {
                    "Milo, Bread/Egg",  "Lipton,Bread/Egg",  "Coffee", "Indomie, Egg/Chicken",
                    "Rice Water", "OATS", "Tom Brown, Bread/Egg",
                    "Bread/Egg", "OATS", "HAUSA KOKO", "MILO", "COFFEE", "HAUSA KOKO"
            };

    int[] breakfast_images = new int[]
            {
                    R.drawable.unity_hall_milo,  R.drawable.lipton,  R.drawable.coffeeb, R.drawable.contiindom, R.drawable.ricewater,
                    R.drawable.oatsb, R.drawable.tombrownb, R.drawable.bread, R.drawable.oats, R.drawable.hausakokob, R.drawable.milo,
                    R.drawable.coffeeb, R.drawable.hausa
            };

    String[] breakfast_food_times = new String[]
            {
                    "7:30am-11:30am", "7:30am-11:30am", "7:30am-11:30am", "7:30am-11:30am",
                    "7:30am-11:30am", "7:30am-11:30am", "7:30am-11:30am", "7:30am-11:30am",
                    "7:30am-11:30am", "7:30am-11:30am", "7:30am-11:30am", "7:30am-11:30am", "7:30am - 11:30am"
            };

    String[] breakfast_vendor_ids = new String[]
            {
                    "Odo Breakfast", "Odo Breakfast", "Odo Breakfast", "Odo Breakfast", "Odo Breakfast", "Odo Breakfast",
                    "Odo Breakfast", "Odo Breakfast", "Silver Lobster", "Silver Lobster", "Silver Lobster",
                    "Silver Lobster", "Unimart Original"
            };

    String[] location = new String[]
            {
                    "Unity Hall", "Unity Hall", "Unity Hall", "Unity Hall", "Unity Hall", "Unity Hall",
                    "Unity Hall", "Unity Hall", "Independence Hall", "Independence Hall", "Independence Hall",
                    "Independence Hall", "KNUST"
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        recyclerView = findViewById(R.id.breakfast_recycler);
        searchView = findViewById(R.id.search_view_breakfast);

        breakItems = new ArrayList<>();



        breakfastOthersAdapter = new BreakfastOthersAdapter(BreakfastActivity.this, breakItems);
        breakfastOthersAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(BreakfastActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(breakfastOthersAdapter);
        for (int j = 0; j < breakfast_food_names.length; j++) {
            HallModel categoryModel = new HallModel(breakfast_food_names[j], location[j], breakfast_food_times[j], breakfast_images[j], breakfast_vendor_ids[j]);
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
            Toast.makeText(BreakfastActivity.this, "Sorry! cannot find the food here", Toast.LENGTH_SHORT).show();
        } else {
            breakfastOthersAdapter.setFilteredList(breakfast_new_filter);

        }
        return true;
    }

    @Override
    public void onItemClick(HallModel hallModel) {
        Intent intent = new Intent(BreakfastActivity.this, ShowDetailsActivity.class);
        intent.putExtra("TITLE", hallModel.model_food_name);
        intent.putExtra("IMAGE", hallModel.advert_images);
        intent.putExtra("ID",hallModel.vendor_id);
        startActivity(intent);
    }
}