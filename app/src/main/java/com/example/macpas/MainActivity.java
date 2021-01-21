package com.example.macpas;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;

//MACPAS v1.2.0
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    int speed = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            if (extras.containsKey("toHomeSpeed")) {
                speed = extras.getInt("toHomeSpeed");
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displayView(R.id.nav_home);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        displayView(menuItem.getItemId());

        return true;
    }

    public void displayView(int viewId) {

        Fragment fragment = null;


        switch (viewId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;

            case R.id.nav_tutorial:
                fragment = new TutorialFragment();
                break;

            case R.id.nav_settings:
                fragment = new SettingsFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }
}
