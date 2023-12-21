package com.example.unimart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class SupperActivity extends AppCompatActivity implements BreakfastOthersAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    SearchView searchView;
    BreakfastOthersAdapter breakfastOthersAdapter;
    ArrayList<HallModel> breakItems;

    String[] supper_food_names = new String[]
            {
                    "Fried Rice,Chicken/Meat",  "Jollof Rice,Chicken/Meat",  "Assorted Fried Rice,Chicken",
                    "Assorted Jollof Rice,Chicken", "Indomie, Egg/Chicken", "Fried Yam, fish/chicken",
                    "Banku , Fish/Meat", "Abete3, Fish,Meat", "Waakye Special",
                    "Plantain/Beans(Gobe)", "Kenkey ,Okro,fish,pepper", "Rice balls",
                    "Fufu,meat/fish", "Plain Rice,Chicken/Meat","Fried Rice, Chicken,Meat",
                    "Jollof Rice, Chicken/Meat","Plantain/Beans(Gobe)","Ampesi, Garden Egg Stew",
                    "Waakye with fish/chicken","Red Red, egg/meat/fish","Banku with Tilapia",
                    "Assorted Spaghetti","Plantain/Beans(Gobe)", "Fried Yam, fish/chicken/sausage",
                    "Fufu, Meat/Fish","Rice balls, Meat/Fish", "Abetee, Meat,Fish",
                    "Kelewele, groundnut and meat"
            };

    int[] supper_images = new int[]
            {
                    R.drawable.friedrice,  R.drawable.jjjl,  R.drawable.afriedl,
                    R.drawable.ajl, R.drawable.unity_hall_indomie, R.drawable.friedyam,
                    R.drawable.bankul, R.drawable.abete3l, R.drawable.waakyel,
                    R.drawable.unity_hall_plantain, R.drawable.kenkeyl, R.drawable.riceballl,
                    R.drawable.fuful,R.drawable.plainricel,R.drawable.friedrice,
                    R.drawable.jjjjl, R.drawable.gobel, R.drawable.ampesil,
                    R.drawable.katwaakye,R.drawable.redredl,R.drawable.bankutilapial,
                    R.drawable.assortedspagghettil,R.drawable.unity_hall_plantain, R.drawable.friedyaml,
                    R.drawable.fuful,R.drawable.riceballl,R.drawable.abete3l,
                    R.drawable.keleweleone
            };


    String[] supper_vendor_ids = new String[]
            {
                    "Enie foods", "Enie Foods", "Abi Kitchen",

                    "Mobmat Restaurant", "His Majesty", "Fried Yam Special",

                    "Emmi Kitchen", "Emmi Kitchen", "Waakye Special",

                    "Queens Gobe", "Kat Kenkey", "Rakho",

                    "Rakho","Abi Kitchen","Mobmat Restaurant",

                    "Mobmat Restaurant","Adwoa Gobe","Mobmat Restaurant", // 6

                    "Katanga Waakye","Mobmat Restaurant","Mobmat Restaurant",

                    "Mobmat Restaurant","Katanga Gobe","Mama Lee",
                    "Mobmat Restaurant","Sambra Kitchen","Unimart Original",
                    "Unimart Original"

            };

    String[] location = new String[]
            {
                    "Queens Hall(RM39)","Queens Hall(RM39)", "Queens Hall(RM38)",

                    "Queens Hall", "Unity Hall", "Queens Hall(RM19)",

                    "Queens Hall(RM21)", "Queens Hall(RM21)", "Queens Hall(RM23)",

                    "Queens Hall(RM23)", "Katanga (store No: 11)", "Unity Hall",

                    "Unity Hall","Queens Hall(RM38)", "Queens Hall",

                    "Queens Hall","Unity Hall", "Queens Hall",

                    "Katanga (store No: 7)","Queens Hall","Queens Hall",  //7

                    "Queens Hall", "Katanga (store No: 5)","Unity Hall",

                    "Queens Hall", "Katanga","KNUST",

                    "KNUST"
            };

    String[] supper_food_times = new String[]
            {
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm", "5:00pm-10:00pm", "5:00pm-10:00pm",
                    "5:00pm-10:00pm"

            };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supper);

        recyclerView = findViewById(R.id.supper_recycler);
        searchView = findViewById(R.id.search_view_supper);

        breakItems = new ArrayList<>();



        breakfastOthersAdapter = new BreakfastOthersAdapter(SupperActivity.this, breakItems);
        breakfastOthersAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(SupperActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(breakfastOthersAdapter);
        for (int j = 0; j < supper_food_names.length; j++) {
            HallModel categoryModel = new HallModel(supper_food_names[j], location[j], supper_food_times[j], supper_images[j], supper_vendor_ids[j]);
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
            Toast.makeText(SupperActivity.this, "Sorry! cannot find the food here", Toast.LENGTH_SHORT).show();
        } else {
            breakfastOthersAdapter.setFilteredList(breakfast_new_filter);

        }
        return true;
    }

    @Override
    public void onItemClick(HallModel hallModel) {
        Intent intent = new Intent(SupperActivity.this, ShowDetailsActivity.class);
        intent.putExtra("TITLE", hallModel.model_food_name);
        intent.putExtra("IMAGE", hallModel.advert_images);
        intent.putExtra("ID",hallModel.vendor_id);
        startActivity(intent);
    }
}