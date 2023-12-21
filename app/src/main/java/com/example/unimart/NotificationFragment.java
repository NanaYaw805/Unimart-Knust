package com.example.unimart;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class NotificationFragment extends Fragment implements NotificationAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    NotificationAdapter notificationAdapter;
    private ArrayList<String> the_food_names_n,the_food_cost_x;
    private ArrayList<Integer> images_n;
    DatabaseHelper DB;
    FirebaseAuth mAuth;

    String users;

    TextView clearorderhistory;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);

        recyclerView = view.findViewById(R.id.notification_recycler);

        DB = new DatabaseHelper(getContext());
        the_food_names_n = new ArrayList<>();
        the_food_cost_x = new ArrayList<>();
        images_n = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
      users = mAuth.getCurrentUser().getEmail();
      clearorderhistory = view.findViewById(R.id.clearorder);
    clearorderhistory.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseHelper db = new DatabaseHelper(getContext());
            db.deleteMyOrder();
            Toast.makeText(getContext(),"History cleared",Toast.LENGTH_SHORT).show();
           FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.home_frame_layout, new HomeFragment());

            // Add the transaction to the back stack
            ft.addToBackStack(null);

            // Commit the transaction
            ft.commit();

        }
    });

        // Inflate the layout for this fragment

        displaydata();
        notificationAdapter = new NotificationAdapter(the_food_names_n,the_food_cost_x,images_n,getContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(notificationAdapter);
        recyclerView.setHasFixedSize(true);
        return view;
    }

    private void displaydata() {
        Cursor cursor = DB.getData();
        if (cursor.getCount()==0)
        {
            //do nothing
        }
        else{
            while(cursor.moveToNext())
            {
                the_food_names_n.add(cursor.getString(1));
                the_food_cost_x.add(cursor.getString(2));
                images_n.add(cursor.getInt(3));
            }

        }
    }

    @Override
    public void onCancelOrderClick(int position) {
        // Handle the cancel order click here
        String food = the_food_names_n.get(position);

        // Call the deleteOrder method from DatabaseHelper
        boolean isDeleted = DB.deleteOrder(food);

        if (isDeleted) {
            // Update the dataset and notify the adapter
            the_food_names_n.remove(position);
            the_food_cost_x.remove(position);
            images_n.remove(position);
            notificationAdapter.notifyItemRemoved(position);

            // Show a toast or any UI indication
            Toast.makeText(getContext(), "Order canceled successfully", Toast.LENGTH_SHORT).show();
        } else {

            the_food_names_n.remove(position);
            the_food_cost_x.remove(position);
            images_n.remove(position);
            notificationAdapter.notifyItemRemoved(position);
            // Handle deletion failure
            Toast.makeText(getContext(), "Failed to cancel order", Toast.LENGTH_SHORT).show();
        }
    }


}