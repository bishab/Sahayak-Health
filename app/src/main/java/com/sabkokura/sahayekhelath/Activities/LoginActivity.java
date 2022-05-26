package com.sabkokura.sahayekhelath.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sabkokura.sahayekhelath.APIs.RetrofitInstance;
import com.sabkokura.sahayekhelath.APIs.WebApi;
import com.sabkokura.sahayekhelath.MainActivity;
import com.sabkokura.sahayekhelath.R;
import com.sabkokura.sahayekhelath.Requests.LoginRequest;
import com.sabkokura.sahayekhelath.Responses.LoginResponses;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static final String LoggedInData = "UserData";
    public static final String isLoggeedIn = "isLoggedIn";
    public static final String Email = "UserEmail";
    public static final String DisplayName = "FirstName";

    Toolbar toolbar;
    EditText email, password;
    TextView loginButton, registerButton;
    SharedPreferences sharedPreferences;
    String user;
    String pass;

    ImageView alertSign;
    TextView alertTitle, alertDesc;
    Button alertButton;

    Dialog succesAlert;
    View LayoutAlertView;

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
        registerButton = (TextView) findViewById(R.id.registerHere);

        LayoutAlertView = getLayoutInflater().inflate(R.layout.alert_successfull,null);
        alertSign = (ImageView) LayoutAlertView.findViewById(R.id.alertImage);
        alertTitle = (TextView) LayoutAlertView.findViewById(R.id.alertResponse);
        alertDesc = (TextView) LayoutAlertView.findViewById(R.id.alertDescription);
        alertButton = (Button) LayoutAlertView.findViewById(R.id.alertButton);
        succesAlert = new Dialog(this);

        sharedPreferences = getSharedPreferences(LoggedInData, Context.MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = email.getText().toString().trim();
                pass = password.getText().toString().trim();

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

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });



    }
    public void loginUser(LoginRequest loginRequest){


        Call<LoginResponses> loginResponsesCall = RetrofitInstance.getService().loginUser(loginRequest);

        loginResponsesCall.enqueue(new Callback<LoginResponses>() {
            @Override
            public void onResponse(Call<LoginResponses> call, Response<LoginResponses> response) {
                if(response.code()==400){

                    alertSign.setImageResource(R.drawable.icon_user_exist);
                    alertTitle.setText("Invalid credentials!");
                    alertDesc.setText("either email or password is incorrect!");

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
//                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            succesAlert.dismiss();
                        }
                    });



                }
                else if(response.code()==200){



                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Email,user);
                    editor.putBoolean(isLoggeedIn,true);
                    editor.commit();

                    alertSign.setImageResource(R.drawable.icon_correct);
                    alertTitle.setText("Successful Login! ");
                    alertDesc.setText("You have successfully signed in");
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
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    });
//                    Toast.makeText(getApplicationContext(),"Logged in Succesfully", Toast.LENGTH_LONG).show();


                }
                else {
                    alertSign.setImageResource(R.drawable.icon_cancel);
                    alertTitle.setText("Oops!");
                    alertDesc.setText("Something went wrong!");

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
//                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            succesAlert.dismiss();
                        }
                    });

                }


            }

            @Override
            public void onFailure(Call <LoginResponses> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Logged is not Succesfully", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}