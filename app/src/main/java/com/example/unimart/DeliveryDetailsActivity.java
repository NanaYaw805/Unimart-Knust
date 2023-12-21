package com.example.unimart;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class DeliveryDetailsActivity extends AppCompatActivity {
    EditText receiverName, receiverPhoneNumber;
    Spinner spinner;
    Button order;
    Bundle extra;
    String location;
    String userEmail;
    DatabaseReference databaseReference;
    DocumentReference userDocument;
    CollectionReference usersCollection;
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    Date currentDate;
    SimpleDateFormat dateFormat;
    String formattedDate, id;

    FirebaseUser user;
    ProgressBar progressBar;
    DatabaseHelper DB;
    int image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);

        // In your activity or fragment where you want to create the Firebase Database reference


        databaseReference = FirebaseDatabase.getInstance().getReference();
        DB = new DatabaseHelper(this);
        progressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userEmail = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        currentDate = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        formattedDate = dateFormat.format(currentDate);
        id = databaseReference.push().getKey();


// Specify the collection reference
        if (id != null) {
            usersCollection = firestore.collection(userEmail); // Use a specific collection name
            userDocument = usersCollection.document(id);
        }


        extra = getIntent().getExtras();

        receiverName = findViewById(R.id.delivery_name);
        order = findViewById(R.id.sendToFirebase);
        receiverPhoneNumber = findViewById(R.id.delivery_number);
        spinner = findViewById(R.id.spinner_location);
        ArrayAdapter<CharSequence> location_array_adapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        location_array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(location_array_adapter);
        spinner.setSelection(0, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = (String) parent.getItemAtPosition(position);
                if (position == 0) {
                    //do nothing
                } else if (selectedValue.equals("Can't find my location")) {
                    location = createEditText();
                } else {
                    location = selectedValue;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToFirebaseDatabase();
            }
        });
    }

    private void sendToFirebaseDatabase() {
        progressBar.setVisibility(View.VISIBLE);
        String user_name = receiverName.getText().toString();
        String user_phone = receiverPhoneNumber.getText().toString();
        String user_location = location;
        HashMap<String, Object> allExtras = new HashMap<>();


        if (extra != null) {
            if (!(user_name ==null && user_phone ==null && user_location==null && userEmail == null)) {

                String food_name, description,id;


                food_name = extra.getString("FOOD_NAME");
                description = extra.getString("DESCRIPTION");
                image = extra.getInt("FOOD_IMAGE");
                id = extra.getString("ID");


                allExtras.put("FOOD_NAME", food_name);
                allExtras.put("RECEIVER_NAME", user_name);
                allExtras.put("RECEIVER_PHONE", user_phone);
                allExtras.put("RECEIVER_LOCATION", user_location);
                allExtras.put("DATE", formattedDate);
                allExtras.put("USER_EMAIL", userEmail);
                allExtras.put("DESCRIPTIONS",description);
                allExtras.put("ID",id);




                userDocument.set(allExtras).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(DeliveryDetailsActivity.this, "Order Successful", Toast.LENGTH_LONG).show();
                            DB.insertObjectData(userEmail,food_name,image);

                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(DeliveryDetailsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                progressBar.setVisibility(View.INVISIBLE);

            }
        }

    }


    private String createEditText() {
        LinearLayout linearLayout = findViewById(R.id.the_linear); // Change this to your actual layout

        // Create EditText
        EditText editTexttt = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int) getResources().getDimension(R.dimen.your_height_in_dp));
        layoutParams.setMargins(8, 16, 8, 8);
        editTexttt.setPadding(8, 8, 8, 8);
        editTexttt.setLayoutParams(layoutParams);
        int edittext_id_id = 1805;
        editTexttt.setId(edittext_id_id);
        editTexttt.setBackgroundResource(R.drawable.sky_border);
        editTexttt.setHint("Let us know the name of your hall , hostel or location here");


        // Add EditText to your layout
        linearLayout.addView(editTexttt);
        return editTexttt.getText().toString();
    }
}


