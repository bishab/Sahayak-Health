package com.sabkokura.sahayekhelath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sabkokura.sahayekhelath.Activities.LoginActivity;
import com.sabkokura.sahayekhelath.Activities.RegisterActivity;
import com.sabkokura.sahayekhelath.Activities.UserProfileActivity;
import com.sabkokura.sahayekhelath.Classes.IntroSlider;
import com.sabkokura.sahayekhelath.Fragments.BMIFragment;
import com.sabkokura.sahayekhelath.Fragments.FragmentAmbulance;
import com.sabkokura.sahayekhelath.Fragments.FragmentAppointment;
import com.sabkokura.sahayekhelath.Fragments.FragmentFaq;
import com.sabkokura.sahayekhelath.Fragments.FragmentHome;
import com.sabkokura.sahayekhelath.Fragments.FragmentHospital;
import com.sabkokura.sahayekhelath.Fragments.FragmentViewAppointment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    MenuItem getSelectedMenu;
    int selPanel = 0;
    Menu mymenu;
    String lan;
    boolean showChangeLangue=false;
    TextView showUserProfile;
    TextView showUserName;

    public static final String LoggedInData = "UserData";
    public static final String isLoggedIn = "isLoggedIn";
    public static final String Email = "UserEmail";
    public static final String DisplayName = "FirstName";

    String savedEmail;
    Boolean isUserLoggedIn;
    String userFirstName;
    SharedPreferences sharedPreferences;


    ImageView alertSign;
    TextView alertTitle, alertDesc;
    Button alertButton;

    Dialog succesAlert;
    View LayoutAlertView;

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

        sharedPreferences = getSharedPreferences(LoggedInData,Context.MODE_PRIVATE);
        isUserLoggedIn = sharedPreferences.getBoolean(isLoggedIn,false);
        savedEmail = sharedPreferences.getString(Email,"");
        userFirstName = sharedPreferences.getString(DisplayName,"User");


        LayoutAlertView = getLayoutInflater().inflate(R.layout.alert_successfull,null);
        alertSign = (ImageView) LayoutAlertView.findViewById(R.id.alertImage);
        alertTitle = (TextView) LayoutAlertView.findViewById(R.id.alertResponse);
        alertDesc = (TextView) LayoutAlertView.findViewById(R.id.alertDescription);
        alertButton = (Button) LayoutAlertView.findViewById(R.id.alertButton);
        succesAlert = new Dialog(this);

        System.out.println("Email: " + savedEmail + " IsLogged In :" + isUserLoggedIn + "Display Name: " + userFirstName);


        //App Intro Parts
        IntroSlider prefManager = new IntroSlider(getApplicationContext());
        if(prefManager.isFirstTimeLaunch()){
            prefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(MainActivity.this, IntroSlider.class));
            finish();
        }

        //View for providing function to userProfile

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = navigationView.getHeaderView(0);
        showUserProfile = (TextView) view.findViewById(R.id.viewProfile);
        showUserName = (TextView) view.findViewById(R.id.ProfileuserName);
        showUserName.setText(userFirstName);


        showUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavigationView navigationView = (NavigationView)FindViewById(Resource.Id.navigationView1);
//                View headerView = navigationView.GetHeaderView(0);
//                TextView navUsername = (TextView)headerView.FindViewById(Resource.Id.edituser);
//                navUsername.Text=("Your Text Here");
                if(isUserLoggedIn) startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
                else Toast.makeText(getApplicationContext(),"User is not Logged In",Toast.LENGTH_SHORT).show();
            }
        });


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
        MenuItem loggedIn = menu.findItem(R.id.loginsignup);
        if(isUserLoggedIn){
            loggedIn.setTitle("Log Out");
        }
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
                if(isUserLoggedIn){


                    alertSign.setImageResource(R.drawable.icon_sign_out);
                    alertTitle.setText("Logout");
                    alertDesc.setText("Are you sure you want to Logout?");

                    succesAlert.setContentView(LayoutAlertView);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(succesAlert.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.CENTER;

                    succesAlert.getWindow().setAttributes(lp);
                    succesAlert.show();
                    alertButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(Email,"");
                            editor.putBoolean(isLoggedIn,false);
                            editor.commit();
                            Toast.makeText(getApplicationContext(),"Logged Out Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                    });






                }
                else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
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

                        toolbar.setTitle("FAQs");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentFaq()).commit();
                        break;
            }
            case R.id.nav_menu_hospitalList:{
                toolbar.setTitle("Hospitals");
                invalidateOptionsMenu();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHospital()).commit();
                break;
            }
            case R.id.nav_menu_appointment:{
                toolbar.setTitle("Schedule an Appointment");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAppointment(this)).commit();
                break;
            }
            case R.id.nav_view_appointment:{
                toolbar.setTitle("View Appointment");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentViewAppointment(this)).commit();
                break;
            }
            case R.id.nav_menu_bmi:{
                toolbar.setTitle("BMI Calculator");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BMIFragment()).commit();
                break;
            }
            case R.id.nav_ambulance:{
                toolbar.setTitle("Ambulance List");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentAmbulance()).commit();

                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }
}