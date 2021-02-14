package com.example.macpas;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
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
import static com.example.macpas.R.style.DefaultColor;
import static com.example.macpas.R.style.WhiteOnBlackTheme;
import static com.example.macpas.R.style.BlueOnYellowTheme;
import static com.example.macpas.R.style.YellowOnBlueTheme;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import static com.example.macpas.R.style.YellowOnBlueTheme;

//MACPAS v1.2.1
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    int speed = 3000;
    int currentTheme = 1;
    public StringBuilder currentDisplay = new StringBuilder("");
    //int currentFragment;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            if (extras.containsKey("toHomeSpeed")) {
                speed = extras.getInt("toHomeSpeed");
            }
            if(extras.containsKey("toHomeTheme")) {
                currentTheme = extras.getInt("toHomeTheme");
            }
            if(extras.containsKey("toHomeDisplay")) {
                currentDisplay = new StringBuilder(extras.getString("toHomeDisplay"));
                extras.remove("toHomeDisplay");
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //currentFragment = R.id.nav_home;
        displayView(R.id.nav_home);
    }

    public void setCurrentDisplay(String newCurrentDisplay) {
        currentDisplay = new StringBuilder(newCurrentDisplay);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // currentFragment = menuItem.getItemId();
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

            ft.replace(R.id.content_frame, fragment, "MY_FRAGMENT");
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        /*
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                if(currentFragment == R.id.nav_home) {
                    Context context = getApplicationContext();
                    CharSequence text = "TESTING";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    HomeFragment hf = (HomeFragment) getSupportFragmentManager().findFragmentByTag("HOME");
                    /*
                    if(hf.timer != null) {

                        hf.resetColour(hf.current);
                        hf.current = 0;
                        hf.scanning = false;
                        hf.timer.cancel();
                        hf.timer = null;
                    }
                }

            }
            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }
            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
        */
    }

    @Override
    public void onBackPressed() {
        Context context = getApplicationContext();
        CharSequence text = "Back Disabled";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
