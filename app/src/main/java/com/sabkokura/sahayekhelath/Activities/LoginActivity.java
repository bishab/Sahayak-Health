package com.sabkokura.sahayekhelath.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sabkokura.sahayekhelath.APIs.RetrofitInstance;
import com.sabkokura.sahayekhelath.APIs.WebApi;
import com.sabkokura.sahayekhelath.R;
import com.sabkokura.sahayekhelath.Requests.LoginRequest;
import com.sabkokura.sahayekhelath.Responses.LoginResponses;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText email, password;
    TextView loginButton;

    //
    WebApi webApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //setting up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

        //defining Views
        email = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.userPassword);
        loginButton = (TextView) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.length()>=0 && pass.length()>=0){
                    if (Patterns.EMAIL_ADDRESS.matcher(user).matches()){
//                        Toast.makeText(getApplicationContext(), "Congratulation", Toast.LENGTH_SHORT).show();
                        LoginRequest request = new LoginRequest(user, pass);
                        loginUser(request);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid Email Format", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void loginUser(LoginRequest loginRequest){


        Call<LoginResponses> loginResponsesCall = RetrofitInstance.getService().loginUser(loginRequest);

        loginResponsesCall.enqueue(new Callback<LoginResponses>() {
            @Override
            public void onResponse(Call<LoginResponses> call, Response<LoginResponses> response) {
//                Toast.makeText(getApplicationContext(),"Logged is Succesfully", Toast.LENGTH_SHORT).show();
//                String abc = response.body();
                LoginResponses responses = response.body();
                if(responses.getStatus().equals("user not registered")){
                    Toast.makeText(getApplicationContext(),"User is not Registered", Toast.LENGTH_LONG).show();
                }
                else if(responses.getStatus().equals("password incorrect")){
                    Toast.makeText(getApplicationContext(),"Username or password incorrect", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Logged in Succesfully", Toast.LENGTH_LONG).show();
                }


                System.out.println("OUTPUT " +response.message());
                System.out.println("OUTPUT " +responses.getStatus());
            }

            @Override
            public void onFailure(Call <LoginResponses> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Logged is not Succesfully", Toast.LENGTH_SHORT).show();

            }
        });


    }
}