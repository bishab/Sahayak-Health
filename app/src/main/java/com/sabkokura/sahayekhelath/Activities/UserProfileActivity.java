package com.sabkokura.sahayekhelath.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sabkokura.sahayekhelath.Fragments.FragmentHomes;
import com.sabkokura.sahayekhelath.Fragments.FragmentViewAppointment;
import com.sabkokura.sahayekhelath.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserProfileActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    TextView disfullName, disAdd, disEmail,disPhone, disDOB, disGender;
    String BASE_URL = "http://20.41.221.66:7000/patient/getreg/";
    CardView showProfile, showDetails;
    ProgressBar progressBar;
    TextView showFetching;
    ImageView profilePic;
    Button viewApp;

    public static final String LoggedInData = "UserData";
    public static final String isLoggeedIn = "isLoggedIn";
    public static final String Email = "UserEmail";
    public static final String DisplayName = "FirstName";
    public static final String DisplayGender = "Gender";
    SharedPreferences sharedPreferences;
    String getUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("User Profile");
        setSupportActionBar(toolbar);

        //Assigning views
        disfullName = (TextView) findViewById(R.id.userShowName);
        disAdd = (TextView) findViewById(R.id.userShowAdd);
        disEmail = (TextView) findViewById(R.id.userShowEmail);
        disPhone = (TextView) findViewById(R.id.userShowPhone);
        disDOB = (TextView) findViewById(R.id.userShowDOB);
        disGender = (TextView) findViewById(R.id.userShowGender);

        showProfile = (CardView) findViewById(R.id.userShowProfile);
        showDetails = (CardView) findViewById(R.id.userShowDetails);
        progressBar = (ProgressBar) findViewById(R.id.Userprogressbar);
        showFetching = (TextView) findViewById(R.id.userShowFetchingText);
        profilePic = (ImageView) findViewById(R.id.userShowImage);

        viewApp = (Button) findViewById(R.id.viewApp);

        sharedPreferences = getSharedPreferences(LoggedInData, Context.MODE_PRIVATE);
        getUserEmail = sharedPreferences.getString(Email,"");

        BASE_URL = BASE_URL+getUserEmail;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    progressBar.setVisibility(View.GONE);
                    showFetching.setVisibility(View.GONE);
                    disfullName.setVisibility(View.VISIBLE);
                    showProfile.setVisibility(View.VISIBLE);
                    showDetails.setVisibility(View.VISIBLE);

                    JSONObject object = response.getJSONObject(0);
                    if(object.getString("gender").equals("Female")){
                        profilePic.setImageResource(R.drawable.icon_user_female);
                    }
                    else {
                        profilePic.setImageResource(R.drawable.icon_user);
                    }

                    disfullName.setText(object.getString("first_name")+" "+object.getString("last_name"));
                    disEmail.setText(object.getString("email"));
                    disAdd.setText(object.getString("address"));
                    disPhone.setText(object.getString("contact_number"));
                    disDOB.setText(object.getString("date_of_birth"));
                    disGender.setText(object.getString("gender"));

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(DisplayName,object.getString("first_name"));
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("Throws exception");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Still getting Error");

            }
        });
        requestQueue.add(request);

        viewApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"You can check your Appointment in Appointment Section",Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentViewAppointment()).commit();

//                startActivity(new Intent(UserProfileActivity.this, FragmentViewAppointment.class));
            }
        });





    }
}