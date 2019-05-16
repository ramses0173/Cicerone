package com.example.cicerone;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_search:
                            selectedFragment = new SearchEventMainFragment();
                            return true;
                        case R.id.navigation_add_event:
                            selectedFragment = new AddEventMainFragment();
                            return true;
                        case R.id.navigation_event:
                            selectedFragment = new MyEventMainFragment();
                            return true;
                        case R.id.navigation_profile:
                            selectedFragment = new ProfileMainFragment();
                            return true;
                    }
                    return false;
                }
            };

}
