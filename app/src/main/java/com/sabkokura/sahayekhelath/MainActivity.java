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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sabkokura.sahayekhelath.Activities.LoginActivity;
import com.sabkokura.sahayekhelath.Classes.IntroSlider;
import com.sabkokura.sahayekhelath.Fragments.BMIFragment;
import com.sabkokura.sahayekhelath.Fragments.FragmentAppointment;
import com.sabkokura.sahayekhelath.Fragments.FragmentFaq;
import com.sabkokura.sahayekhelath.Fragments.FragmentHome;
import com.sabkokura.sahayekhelath.Fragments.FragmentHospital;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    MenuItem getSelectedMenu;
    int selPanel = 0;
    Menu mymenu;
    String lan;
    boolean showChangeLangue=false;

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

        //App Intro Parts
        IntroSlider prefManager = new IntroSlider(getApplicationContext());
        if(prefManager.isFirstTimeLaunch()){
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(MainActivity.this, IntroSlider.class));
            finish();
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navitgation_open, R.string.navitgation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState ==null) {
            showChangeLangue=false;
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu, menu);
        mymenu = menu;
        MenuItem item = menu.findItem(R.id.changeMyLanguage);
        if(item!=null && showChangeLangue==false){
            item.setVisible(false);
        }
        else {
//            item.setVisible(true);
        }



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {




//        if(showChangeLangue==true) {
//            MenuItem item1 = (MenuItem) findViewById(R.id.changeMyLanguage);
//            item1.setVisible(true);
//            this.invalidateOptionsMenu();
//        }
//        else {
//            MenuItem item1 = (MenuItem) findViewById(R.id.changeMyLanguage);
//            item1.setVisible(false);
//            this.invalidateOptionsMenu();
//        }
        switch (item.getItemId()){

            case R.id.loginsignup:{
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
//                Toast.makeText(getApplicationContext(),"Login Clicked",Toast.LENGTH_SHORT).show();
            }
//            case R.id.changeMyLanguage:{
//                if(item.getTitle().equals("En")){
//                    item.setTitle("नेपा");
//                }
//                else {
//                    item.setTitle("En");
//                }
//            }
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        getSelectedMenu = item;
        switch (item.getItemId()){
            case R.id.nav_menu_home:{
                showChangeLangue=false;
                invalidateOptionsMenu();
                toolbar.setTitle("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();
                break;
            }
            case R.id.nav_menu_faq:{

                    selPanel = 1;
                    if(mymenu!=null){
                        mymenu.findItem(R.id.changeMyLanguage).setVisible(true);
                        toolbar.setTitle("FAQs");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFaq()).commit();
                    }
                    lan = "En";
                    mymenu.findItem(R.id.changeMyLanguage).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if(menuItem.getTitle().equals("En")){
                                menuItem.setTitle("नेपा");
                                lan = "Nep";
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFaq()).commit();
                            }
                            else {
                                menuItem.setTitle("En");
                                lan = "En";
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFaq("Nep")).commit();
                            }
                            return true;
                        }
                    });
                    System.out.println("Default Language is: "+ lan);
//                    if(lan=="En"){
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFaq()).commit();
//                    }
//                    else {
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFaq("Nep")).commit();
//                    }
                    break;
            }
            case R.id.nav_menu_hospitalList:{
                toolbar.setTitle("Hospitals");
                invalidateOptionsMenu();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHospital()).commit();
                break;
            }
            case R.id.nav_menu_appointment:{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAppointment()).commit();
                break;
            }
            case R.id.nav_menu_bmi:{
                toolbar.setTitle("BMI Calculator");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BMIFragment()).commit();
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }
}