package com.example.unimart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (savedInstanceState==null)
        {
            replaceFragment(new HomeFragment());
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation_view_id);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                int id = Item.getItemId();
                if (id == R.id.home_bmenu)
                {
                    replaceFragment(new HomeFragment());
                }

                else if (id == R.id.profile_bmenu)
                {
                    replaceFragment(new ProfileFragment());
                }

                else if (id == R.id.notification_bmenu)
                {
                    replaceFragment(new NotificationFragment());
                }

                else if (id == R.id.settings_bmenu)
                {
                    replaceFragment(new SettingsFragment());
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_frame_layout,fragment);
        fragmentTransaction.commit();
    }
}