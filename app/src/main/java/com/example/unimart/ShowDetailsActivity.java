package com.example.unimart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ShowDetailsActivity extends AppCompatActivity {
    private TextView food_name, order_now;
    private ImageView food_image;
    private EditText description;

    Bundle bundle;
    String f_name, id, the_descriptions;
    int f_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        food_name = findViewById(R.id.food_name_show_details);
        order_now = findViewById(R.id.order_btn);
        food_image = findViewById(R.id.food_image_show_details);
        description = findViewById(R.id.food_customer_description);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            f_name = bundle.getString("TITLE");
            f_image = bundle.getInt("IMAGE");
            id = bundle.getString("ID");


            food_name.setText(f_name);
            food_image.setImageResource(f_image);
        }


        order_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        the_descriptions = description.getText().toString();

                        if (the_descriptions.isEmpty())
                        {
                            Toast.makeText(ShowDetailsActivity.this,"Give us details about the food",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            BuildAlertDialog();
                        }

                    }
                });


            }
        });
    }

    private void BuildAlertDialog() {
        // Create an AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title and message for the dialog
        builder.setTitle("Order Now");
        builder.setMessage("NB: Orders can't be cancelled");

        // Set positive button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(ShowDetailsActivity.this, DeliveryDetailsActivity.class);
                in.putExtra("FOOD_NAME", f_name);
                in.putExtra("ID", id);
                in.putExtra("DESCRIPTION", the_descriptions);
                in.putExtra("FOOD_IMAGE",f_image);
                startActivity(in);
            }
        });

        // Set negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

