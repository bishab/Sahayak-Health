package com.sabkokura.sahayekhelath.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sabkokura.sahayekhelath.R;

public class RegisterActivity extends AppCompatActivity {
    Spinner spinnerMaleFemale;
    Toolbar toolbar;
    EditText firstName, lastName, gender, email, password, confirmPassword, conatacNumber;
    TextView dateOfBirth, register, measuerPassword;
    View passwordLine;
    ImageView calenderIcon;
    DatePicker datePicker;
    Boolean passwordMatch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Setting up Toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Register Here");
        setSupportActionBar(toolbar);

        //assigning Views
        spinnerMaleFemale = (Spinner) findViewById(R.id.malefemaleSpinner);
        firstName = (EditText) findViewById(R.id.userFirstName);
        lastName = (EditText) findViewById(R.id.userLastName);
        email = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.userPassword);
        confirmPassword = (EditText) findViewById(R.id.userConfirmPassword);
        conatacNumber = (EditText) findViewById(R.id.userContactNumber);

        dateOfBirth = (TextView) findViewById(R.id.userDateOfBirth);
        register = (TextView) findViewById(R.id.registerButton);
        measuerPassword = (TextView) findViewById(R.id.displayMeasuredPassword);
        passwordLine = (View) findViewById(R.id.measurePassword);

        calenderIcon = (ImageView) findViewById(R.id.calendarIcon);
        View LayoutCalenderView = getLayoutInflater().inflate(R.layout.datepicker, null);
        datePicker = (DatePicker) LayoutCalenderView.findViewById(R.id.datepicker);

        //Data for Spinner
        String[] sex = {"Male","Female","Other"};
        // Declaring an Adapter and initializing it to the data pump
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, sex);
        spinnerMaleFemale.setAdapter(arrayAdapter);
        spinnerMaleFemale = (Spinner) findViewById(R.id.malefemaleSpinner);

        //Checking Password Strength
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0&& charSequence.length()<=6){
                    measuerPassword.setVisibility(View.VISIBLE);
                    measuerPassword.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.smallPassword));
                    passwordLine.setVisibility(View.VISIBLE);
                    passwordLine.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.smallPassword));
                }
                else if (charSequence.length()>6&& charSequence.length()<=9){
                    measuerPassword.setVisibility(View.VISIBLE);
                    measuerPassword.setText("Medium");
                    measuerPassword.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.mediumPassword));
                    passwordLine.setVisibility(View.VISIBLE);
                    passwordLine.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.mediumPassword));
                }
                else if(charSequence.length()>6){
                    measuerPassword.setVisibility(View.VISIBLE);
                    measuerPassword.setText("Strong");
                    measuerPassword.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.strongPassword));
                    passwordLine.setVisibility(View.VISIBLE);
                    passwordLine.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.strongPassword));

                }
                else{
                    measuerPassword.setVisibility(View.INVISIBLE);
                    passwordLine.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals(password.getText().toString())){
                    System.out.println("Password Equal:" + charSequence);
                    passwordMatch = true;
                    confirmPassword.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            getResources().getDrawable(R.drawable.verify, null), null);
                }
                else {
                    confirmPassword.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            null, null);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        Dialog openCalendar = new Dialog(this);
        dateOfBirth.setText(getCurrentDate());

        calenderIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView selectData = (TextView) LayoutCalenderView.findViewById(R.id.chooseDate);
                openCalendar.setContentView(LayoutCalenderView);
                openCalendar.show();

                selectData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dateOfBirth.setText(""+getCurrentDate());
                        System.out.println("Date: "+getCurrentDate());
                        openCalendar.dismiss();

                    }
                });

            }
        });
        //Validating and saving data
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName, lName, gender, eMail, dOB, phNumber, pass, conPass;
                fName = firstName.getText().toString().trim();
                lName = lastName.getText().toString().trim();
                pass = password.getText().toString().trim();
                conPass = confirmPassword.getText().toString().trim();
                gender = spinnerMaleFemale.getSelectedItem().toString().trim();
                eMail = email.getText().toString().trim();
                dOB = dateOfBirth.getText().toString().trim();
                phNumber = conatacNumber.getText().toString().trim();

                if(fName.length()>0 && lName.length()>0 && lName.length()>0 && pass.length()>0 && conPass.length()>0 && eMail.length()>0
                        && dOB.length()>0 && phNumber.length()>0){
                    if(Patterns.EMAIL_ADDRESS.matcher(eMail).matches()){
                        if(passwordMatch==true){
                            if (phNumber.length()==10){
                                Toast.makeText(getApplicationContext(),"Ready to Proceed",Toast.LENGTH_LONG).show();

                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_LONG).show();

                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Password Doesn't Match",Toast.LENGTH_LONG).show();

                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Invalid Email Format",Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(),"Fill All Fields",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        builder.append((datePicker.getMonth() + 1)+"/");//month is 0 based
        builder.append(datePicker.getDayOfMonth()+"/");
        builder.append(datePicker.getYear());
        return builder.toString();
    }
}