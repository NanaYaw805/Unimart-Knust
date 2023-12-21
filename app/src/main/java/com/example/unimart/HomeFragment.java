package com.example.unimart;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    SearchView searchView;
    RecyclerView cat,univ,rep,que,uni_new;
    ArrayList<CategoryModel> categoryModelsList;
    ArrayList<HallModel> unity_hall_new_model_list,unity_hall_old_model_list,university_hall_model_list,republic_hall_model_list,
            queens_hall_model_list,independence_hall_model_list,off_campus_model_list;



    HallAdapter unity_hall_new_adapter,unity_hall_old_adapter, university_hall_adapter,queens_hall_adapter,republic_hall_adapter,
    independence_hall_adapter;

    CategoryAdapter categoryAdapter;

    String[] unity_hall_new_food_names = new String[]
            {
                "Fried Yam,fish/egg/chicken", "Plantain/Beans(Gobe)","Banku, okro stew","Indomie"
            };

    String[] unity_hall_new_food_times = new String[]
            {
                "1pm-4pm","9:30am-4:00pm","2pm-8pm","9:00am-3:00pm"
            };

    String[] unity_hall_new_vendor_ids = new String[]
            {
                    "Mama Lee","Adwoa Gobe","Rakho","Odo Breakfast"
            };

    int[] unity_food_new_images = new int[]
            {
                    R.drawable.friedyam,R.drawable.unity_hall_plantain,R.drawable.bankur,R.drawable.contiindom
            };



    String[] university_hall_food_names = new String[]
            {
                    "Plantain/Beans(Gobe)", "Bread and Egg","Kenkey/Fish","Fried Rice, Chicken"," Waakye, Chicken"
            };

    String[] university_hall_food_times = new String[]
            {
                    "10:00am-4:00pm","7:00pm-10:00pm","1:00pm - 4:00pm", "6:00pm - 9:30pm", "11:00am - 4:30pm"
            };

    String[] katanga_new_vendor_ids = new String[]
            {
                    "Store No. 5","Store No. 6","Store No. 11","Store No. 16","Store No. 7"
            };

    int[] university_hall_images = new int[]
            {
                    R.drawable.katg,R.drawable.breadegg,R.drawable.katken,
                    R.drawable.katfry, R.drawable.katwaakye
            };



    String[] queens_hall_food_names = new String[]
            {
                    "Indomie", "Plantain/Beans","Kenkey/Fish","Plantain/Beans","Fufu/Soup"
            };

    String[] queens_hall_food_times = new String[]
            {
                    "11:00am-5:30am","9:00am-4:00pm","6pm - 9pm", "9:30am - 3:30pm", "7pm - 10pm"
            };

    String[] queens_new_vendor_ids = new String[]
            {
                    " RM5","RM6","RM11","RM16","RM7"
            };

    int[] queens_hall_images = new int[]
            {
                    R.drawable.indomie,R.drawable.queengobe,R.drawable.kenkeyque,
                    R.drawable.unity_hall_plantain, R.drawable.unity_hall_fufu
            };



    String[] republic_hall_food_names = new String[]
            {
                    "Plantain/Beans", "Diehuo","Waakye"
            };

    String[] republic_hall_food_times = new String[]
            {
                    "10:00am-4:00pm","6:00pm-8:00pm","5pm - 8pm"
            };

    String[] republic_new_vendor_ids = new String[]
            {
                    "Sister Afia Gobe","Mama Zee's Kitchen","Hajia4Real Special Waakye"
            };

    int[] republic_hall_images = new int[]
            {
                    R.drawable.rg,R.drawable.repudie,R.drawable.unity_hall_indomie
            };
    String[] category_names = new String[]
            {
                "Breakfast","Lunch","Supper","Fruits","Vegetables"
            };

    int[] category_images = new int[]
            {
                R.drawable.breakfast_category_image, R.drawable.lunch_category_image, R.drawable.supper_category_image,
                    R.drawable.fruit_category_image,R.drawable.vegetable_category_image

            };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchView = (SearchView) view.findViewById(R.id.search_view_home); //initializing search_view
        cat = (RecyclerView) view.findViewById(R.id.rec_category);
        uni_new = (RecyclerView) view.findViewById(R.id.rec_unity_hall_new);
        univ = (RecyclerView) view.findViewById(R.id.rec_university_hall);
        rep = (RecyclerView) view.findViewById(R.id.rec_republic_hall);
        que = (RecyclerView) view.findViewById(R.id.rec_queens_hall);


        //initialize list
        categoryModelsList = new ArrayList<>();
        unity_hall_new_model_list = new ArrayList<>();
        university_hall_model_list = new ArrayList<>();
        queens_hall_model_list = new ArrayList<>();
        republic_hall_model_list = new ArrayList<>();
        independence_hall_model_list = new ArrayList<>();
        off_campus_model_list = new ArrayList<>();

        //initialize adapters
        unity_hall_new_adapter = new HallAdapter(getContext(),unity_hall_new_model_list);
        university_hall_adapter = new HallAdapter(getContext(),university_hall_model_list);
        queens_hall_adapter = new HallAdapter(getContext(),queens_hall_model_list);
        republic_hall_adapter = new HallAdapter(getContext(),republic_hall_model_list);
        independence_hall_adapter = new HallAdapter(getContext(),independence_hall_model_list);
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelsList);


        //set recycler_views

        //category_recycler_views
        cat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        cat.setHasFixedSize(true);
        cat.setAdapter(categoryAdapter);

        //unity_hall recycler(new)
        uni_new.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        uni_new.setHasFixedSize(true);
        uni_new.setAdapter(unity_hall_new_adapter);

        //university_hall recycler
        univ.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        univ.setHasFixedSize(true);
        univ.setAdapter(university_hall_adapter);

        //queens hall recycler
        que.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        que.setHasFixedSize(true);
        que.setAdapter(queens_hall_adapter);

        //republic hall recycler
        rep.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rep.setHasFixedSize(true);
        rep.setAdapter(republic_hall_adapter);

        //loop through category adapter items
        for(int j = 0; j < category_names.length; j++)
        {
            CategoryModel categoryModel = new CategoryModel(category_names[j],category_images[j]);
            categoryModelsList.add(categoryModel);
            categoryAdapter.notifyDataSetChanged();
        }


        //loop through unity_hall_new items
        for(int i = 0; i < unity_hall_new_food_names.length; i++)
        {
            HallModel hallModel = new HallModel(unity_hall_new_food_names[i],
                    "Unity Hall(New Canteen)",unity_hall_new_food_times[i],unity_food_new_images[i],unity_hall_new_vendor_ids[i]);
            unity_hall_new_model_list.add(hallModel);
            unity_hall_new_adapter.notifyDataSetChanged();
        }





        //loop through university_hall items
        for(int i = 0; i < university_hall_food_names.length; i++)
        {
            HallModel hallModel = new HallModel(university_hall_food_names[i],
                    "University Hall",university_hall_food_times[i],university_hall_images[i],katanga_new_vendor_ids[i]);
            university_hall_model_list.add(hallModel);
            university_hall_adapter.notifyDataSetChanged();
        }

        //loop through republic_hall items
        for(int i = 0; i < republic_hall_food_names.length; i++)
        {
            HallModel hallModel = new HallModel(republic_hall_food_names[i],
                    "Republic Hall",republic_hall_food_times[i],republic_hall_images[i],republic_new_vendor_ids[i]);
            republic_hall_model_list.add(hallModel);
            republic_hall_adapter.notifyDataSetChanged();
        }

        //loop through queens_hall items
        for(int i = 0; i < queens_hall_food_names.length; i++)
        {
            HallModel hallModel = new HallModel(queens_hall_food_names[i],
                    "Queens Hall",queens_hall_food_times[i],queens_hall_images[i],queens_new_vendor_ids[i]);
            queens_hall_model_list.add(hallModel);
            queens_hall_adapter.notifyDataSetChanged();
        }


        //search_view properties
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                filterList(text);
                return true;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    //unity_hall new filter
    private boolean filterList(String text) {
        ArrayList<HallModel> unity_new_filter = new ArrayList<>();
        ArrayList<HallModel> unity_old_filter = new ArrayList<>();
        ArrayList<HallModel> university_filter = new ArrayList<>();
        ArrayList<HallModel> queens_filter = new ArrayList<>();
        ArrayList<HallModel> republic_filter = new ArrayList<>();
        ArrayList<HallModel> independence_filter = new ArrayList<>();
        ArrayList<HallModel> off_campus_filter = new ArrayList<>();

        //unity hall new filter
        for(HallModel food_unity : unity_hall_new_model_list)
        {
            if(food_unity.model_food_name.toLowerCase().contains(text.toLowerCase()))
            {
                unity_new_filter.add(food_unity);
            }
        }



        //university hall filter
        for(HallModel food_university : university_hall_model_list)
        {
            if(food_university.model_food_name.toLowerCase().contains(text.toLowerCase()))
            {
                university_filter.add(food_university);
            }
        }

        //queens hall filter
        for(HallModel food_queens : queens_hall_model_list)
        {
            if(food_queens.model_food_name.toLowerCase().contains(text.toLowerCase()))
            {
                queens_filter.add(food_queens);
            }
        }

        //republic hall filter
        for(HallModel food_republic : republic_hall_model_list)
        {
            if(food_republic.model_food_name.toLowerCase().contains(text.toLowerCase()))
            {
                republic_filter.add(food_republic);
            }
        }

        //independence hall filter
        for(HallModel food_independence : independence_hall_model_list)
        {
            if(food_independence.model_food_name.toLowerCase().contains(text.toLowerCase()))
            {
                independence_filter.add(food_independence);
            }
        }

        //off_campus filter
        for(HallModel food_off_campus : off_campus_model_list)
        {
            if(food_off_campus.model_food_name.toLowerCase().contains(text.toLowerCase()))
            {
                off_campus_filter.add(food_off_campus);
            }
        }

        if (unity_new_filter.isEmpty() && unity_old_filter.isEmpty() && university_filter.isEmpty()
        && queens_filter.isEmpty() && republic_filter.isEmpty() && independence_filter.isEmpty()
                && off_campus_filter.isEmpty())
        {
            Toast.makeText(getContext(),"Sorry! cannot find the food here",Toast.LENGTH_SHORT).show();
        }
        else {
            independence_hall_adapter.setFilteredList(independence_filter);
            republic_hall_adapter.setFilteredList(republic_filter);
            queens_hall_adapter.setFilteredList(queens_filter);
            university_hall_adapter.setFilteredList(university_filter);
            unity_hall_new_adapter.setFilteredList(unity_new_filter);
        }
        return true;
    }



}