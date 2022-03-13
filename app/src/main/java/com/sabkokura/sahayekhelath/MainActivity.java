package com.sabkokura.sahayekhelath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sabkokura.sahayekhelath.Activities.LoginActivity;
import com.sabkokura.sahayekhelath.Fragments.FragmentAppointment;
import com.sabkokura.sahayekhelath.Fragments.FragmentFaq;
import com.sabkokura.sahayekhelath.Fragments.FragmentHome;
import com.sabkokura.sahayekhelath.Fragments.FragmentHospital;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining views
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbarmenu);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawlayout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navitgation_open, R.string.navitgation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState ==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
            navigationView.setCheckedItem(R.id.nav_menu_home);
        }

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.loginsignup:{
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(getApplicationContext(),"Login Clicked",Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_menu_home:{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
                break;
            }
            case R.id.nav_menu_faq:{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFaq()).commit();
                break;
            }
            case R.id.nav_menu_hospitalList:{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHospital()).commit();
                break;
            }
            case R.id.nav_menu_appointment:{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAppointment()).commit();
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }
}