package com.sabkokura.sahayekhelath.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.sabkokura.sahayekhelath.Activities.LoginActivity;
import com.sabkokura.sahayekhelath.Activities.RegisterActivity;
import com.sabkokura.sahayekhelath.MainActivity;
import com.sabkokura.sahayekhelath.R;
import com.sabkokura.sahayekhelath.Requests.AppointmentRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAppointment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAppointment extends Fragment {
    TextInputEditText fname, lname, email, address, contact, age;
    TextView eventDate, eventTime, makeAppointment;
    Spinner genderSpinner, hospitalNameSpinner, departmentNameSpinner;
    ImageView calendar, time;

    String BASE_URL_Details = "http://20.41.221.66:7000/patient/getreg/";
    String BASE_URL_Appointment = "http://20.41.221.66:7000/patient/postapp/";

    public static final String LoggedInData = "UserData";
    public static final String Email = "UserEmail";
    SharedPreferences sharedPreferences;
    public String getUserEmail;
    String pfname, plname, page, pemail, paddress, pgender, pcontact, hospitalName, hospitalDepartment, hospitalDate, hospitalTime;
    int intage;

    public static final String[] patientGender = {"Male", "Female", "Others"};
    public static final String[] availableHospitals = {"Gandaki Hospital", "Bhaktapur Cancer Hospital", "Manipal Teaching Hospital", "Nepal Cancer Hospital", "Bir Hospital"};
    public static final String[] availableDepartments = {"ENT", "Mental", "General", "Child Disease", "Physiotherapy", "Radiology"};

    DatePicker datePicker;
    TimePicker timePicker;
    Context context;


    Dialog showAppointmentDetailsDialog;
    View layoutAppointmentDetails;
    TextView appfullName, appEmail, appAddress, appContact, appGender, appAge, appHospital, appDepartment, appTime;
    Button appConfirm, appNo;

    Dialog succesAlert;
    View LayoutAlertView;
    ImageView alertSign;
    TextView alertTitle, alertDesc;
    Button alertButton;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentAppointment() {
        // Required empty public constructor
    }

    public FragmentAppointment(Context context) {
        this.context = context;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAppointment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAppointment newInstance(String param1, String param2) {
        FragmentAppointment fragment = new FragmentAppointment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        //assigning views

        fname = (TextInputEditText) view.findViewById(R.id.patientfName);
        lname = (TextInputEditText) view.findViewById(R.id.patientlName);
        address = (TextInputEditText) view.findViewById(R.id.patientAddress);
        contact = (TextInputEditText) view.findViewById(R.id.patientPhone);
        email = (TextInputEditText) view.findViewById(R.id.patientEmail);
        age = (TextInputEditText) view.findViewById(R.id.patientAge);

        genderSpinner = (Spinner) view.findViewById(R.id.patientgender);
        hospitalNameSpinner = (Spinner) view.findViewById(R.id.selectHospital);
        departmentNameSpinner = (Spinner) view.findViewById(R.id.selectDepartment);

        calendar = (ImageView) view.findViewById(R.id.selectEventDate);
        time = (ImageView) view.findViewById(R.id.selectEventTime);

        eventDate = (TextView) view.findViewById(R.id.eventDate);
        eventTime = (TextView) view.findViewById(R.id.eventTime);
        makeAppointment = (TextView) view.findViewById(R.id.makeAppointment);

        View LayoutCalenderView = getLayoutInflater().inflate(R.layout.datepicker, null);
        datePicker = (DatePicker) LayoutCalenderView.findViewById(R.id.datepicker);

        View LayoutCalenderViewTimer = getLayoutInflater().inflate(R.layout.timepicker, null);
        timePicker = (TimePicker) LayoutCalenderViewTimer.findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);

        sharedPreferences = getActivity().getSharedPreferences(LoggedInData, Context.MODE_PRIVATE);
        getUserEmail = sharedPreferences.getString(Email, "");
        BASE_URL_Details = BASE_URL_Details + getUserEmail;
//        BASE_URL_Appointment = BASE_URL_Appointment + getUserEmail;

        //Selecting default date and time
        eventDate.setText("" + getCurrentDate());
        eventTime.setText("" + getCurrentTime());
        hospitalDate = getCurrentDate();
        hospitalTime = getCurrentTime();

        layoutAppointmentDetails = getLayoutInflater().inflate(R.layout.appointment_details,null);
        appfullName = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmAppfullName);
        appEmail = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmAppemail);
        appContact = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmAppcontact);
        appAddress = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmAppaddress);
        appGender = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmAppgender);
        appAge = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmAppage);
        appHospital = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmApphospital);
        appDepartment = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmAppdepartment);
        appTime = (TextView) layoutAppointmentDetails.findViewById(R.id.confirmAppTime);

        appConfirm = (Button) layoutAppointmentDetails.findViewById(R.id.confirmButton);
        appNo = (Button) layoutAppointmentDetails.findViewById(R.id.confirmNo);

        showAppointmentDetailsDialog = new Dialog(context);

        LayoutAlertView = getLayoutInflater().inflate(R.layout.alert_successfull,null);
        alertSign = (ImageView) LayoutAlertView.findViewById(R.id.alertImage);
        alertTitle = (TextView) LayoutAlertView.findViewById(R.id.alertResponse);
        alertDesc = (TextView) LayoutAlertView.findViewById(R.id.alertDescription);
        alertButton = (Button) LayoutAlertView.findViewById(R.id.alertButton);
        succesAlert = new Dialog(context);



        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, patientGender);
        genderSpinner.setAdapter(genderAdapter);
//        gender.setBackgroundColor(Color.parseColor("#E4E6EB"));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, BASE_URL_Details, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    JSONObject object = response.getJSONObject(0);
                    pfname = object.getString("first_name");
                    plname = object.getString("last_name");
                    pemail = object.getString("email");
                    paddress = object.getString("address");
                    pcontact = object.getString("contact_number");
                    page = object.getString("date_of_birth");
                    pgender = object.getString("gender");
                    System.out.println("Name: " + pfname + page);


                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                        LocalDate dob = LocalDate.parse(page, formatter);
                        LocalDate now = LocalDate.now();
                        intage = dob.until(now).getYears();
                    }

                    fname.setText(pfname);
                    lname.setText(plname);
                    email.setText(pemail);
                    address.setText(paddress);
                    contact.setText(pcontact);
                    age.setText("" + intage);

                    pfname = fname.getText().toString();
                    plname = lname.getText().toString();
                    pemail = email.getText().toString();
                    paddress = address.getText().toString();
                    page = age.getText().toString();
                    pcontact = contact.getText().toString();
                    pgender = genderSpinner.getSelectedItem().toString();

                    if (pgender.equals("Male")) {
                        genderSpinner.setSelection(0);
                    } else if (pgender.equals("Female")) {
                        genderSpinner.setSelection(1);
                    } else {
                        genderSpinner.setSelection(2);
                    }


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

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mmm/YYY");
//           LocalDate dob = LocalDate.parse(page, formatter);
//            System.out.println("Date of Birth is : "+dob);
//        }
        ArrayAdapter<String> hospitalAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item_hospital, availableHospitals);
        hospitalNameSpinner.setAdapter(hospitalAdapter);

        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item_hospital, availableDepartments);
        departmentNameSpinner.setAdapter(departmentAdapter);

        Dialog openCalendar = new Dialog(context);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView selectData = (TextView) LayoutCalenderView.findViewById(R.id.chooseDate);
                openCalendar.setContentView(LayoutCalenderView);
                openCalendar.show();
//
                selectData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        eventDate.setText("" + getCurrentDate());
                        hospitalDate = getCurrentDate().toString();
                        System.out.println("Date: " + getCurrentDate());
                        openCalendar.dismiss();

                    }
                });

            }
        });

        Dialog openTimer = new Dialog(context);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext().getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
                TextView selectData = (TextView) LayoutCalenderViewTimer.findViewById(R.id.chooseTime);
                openTimer.setContentView(LayoutCalenderViewTimer);
                openTimer.show();
//
                selectData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        eventTime.setText("" + getCurrentTime());
                        System.out.println("Date: " + getCurrentTime());
                        hospitalTime = getCurrentTime();
                        openTimer.dismiss();

                    }
                });

            }
        });



        makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pfname = fname.getText().toString();
                plname = lname.getText().toString();
                pemail = email.getText().toString();
                paddress = address.getText().toString();
                page = age.getText().toString();
                pcontact = contact.getText().toString();
                pgender = genderSpinner.getSelectedItem().toString();


                hospitalName = hospitalNameSpinner.getSelectedItem().toString().trim();
                hospitalDepartment = departmentNameSpinner.getSelectedItem().toString().trim();

                if (pfname.length() > 0 && plname.length() > 0 && paddress.length() > 0 && page.length() > 0 && pcontact.length() > 0 &&
                        paddress.length()>0 && pemail.length() > 0 && pgender.length() > 0) {
                    if (Patterns.EMAIL_ADDRESS.matcher(pemail).matches()) {
                        if (pcontact.length() == 10) {
                            AppointmentRequest appointmentRequest = new AppointmentRequest(pfname, plname, page, pemail, paddress, pgender, hospitalName,
                                    hospitalDepartment, hospitalDate, hospitalTime, null);


                            appfullName.setText(pfname + " " + plname);
                            appEmail.setText(pemail);
                            appContact.setText(pcontact);
                            appAddress.setText(paddress);
                            appGender.setText(pgender);
                            appAge.setText(page);
                            appHospital.setText(hospitalName);
                            appDepartment.setText(hospitalDepartment);
                            appTime.setText(hospitalDate + " at " + hospitalTime);

                            showAppointmentDetailsDialog.setContentView(layoutAppointmentDetails);
                            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                            lp.copyFrom(showAppointmentDetailsDialog.getWindow().getAttributes());
                            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                            lp.gravity = Gravity.CENTER;

                            showAppointmentDetailsDialog.getWindow().setAttributes(lp);
                            showAppointmentDetailsDialog.show();
                            appConfirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    makeMyAppointment(appointmentRequest);
                                }
                            });
                            appNo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    showAppointmentDetailsDialog.dismiss();
                                }
                            });

                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid Contact Details!", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Invalid email address!", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public String getCurrentDate() {
        StringBuilder builder = new StringBuilder();
        builder.append((datePicker.getMonth() + 1) + "/");//month is 0 based
        builder.append(datePicker.getDayOfMonth() + "/");
        builder.append(datePicker.getYear());
        return builder.toString();
    }

    public String getCurrentTime() {
        String currentTime = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
        return currentTime;
    }

    public void makeMyAppointment(AppointmentRequest appointmentRequest) {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL_Appointment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("msg").equals("appointment successful")){

                        alertSign.setImageResource(R.drawable.icon_appointment);
                        alertTitle.setText("Appointment Scheduled");
                        alertDesc.setText("Your appointment has been successfully booked!");

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
                                startActivity(new Intent(getContext().getApplicationContext(), MainActivity.class));
                            }
                        });

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

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getParams() {

//                String pfname, plname, page, pemail, paddress, pgender, pcontact, hospitalName, hospitalDepartment, hospitalDate, hospitalTime;
                Map<String, String> param = new HashMap<String, String>();
                param.put("firstname", pfname);
                param.put("lastname",plname);
                param.put("age",page);
                param.put("patient_email",pemail);
                param.put("patient_address",paddress);
                param.put("gender",pgender);
                param.put("contact_number",pcontact);
                param.put("hospital",hospitalName);
                param.put("department",hospitalDepartment);
                param.put("date",hospitalDate);
                param.put("time",hospitalTime);
                System.out.println("Details: " + pfname+plname+page+paddress+pemail+pgender+pcontact+hospitalDate+hospitalTime+hospitalName+hospitalDepartment);


                return param;

            }

        };
        queue.add(request)

        ;


    }

}