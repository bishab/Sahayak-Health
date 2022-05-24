package com.sabkokura.sahayekhelath.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sabkokura.sahayekhelath.Adapters.AppointmentAdapter;
import com.sabkokura.sahayekhelath.Adapters.FAQsAdapter;
import com.sabkokura.sahayekhelath.ModelClasses.AmbulanceListModelsClass;
import com.sabkokura.sahayekhelath.ModelClasses.AppointmentViewModelsClass;
import com.sabkokura.sahayekhelath.ModelClasses.FAQsModelClass;
import com.sabkokura.sahayekhelath.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentViewAppointment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentViewAppointment extends Fragment {

    Context context;
    ArrayList<AppointmentViewModelsClass> arrayList;
    ProgressBar progressBar;
    AppointmentViewModelsClass hospitalListModelsClass;
    TextView loadingText;
    RecyclerView recyclerView;
    public static String BASE_URL = "http://20.41.221.66:7000/patient/getapp/";


    public static final String LoggedInData = "UserData";
    public static final String isLoggeedIn = "isLoggedIn";
    public static final String Email = "UserEmail";
    public static final String DisplayName = "FirstName";
    public static final String DisplayGender = "Gender";
    SharedPreferences sharedPreferences;
    String getUserEmail;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentViewAppointment() {
        // Required empty public constructor
    }
    public FragmentViewAppointment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentViewAppointment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentViewAppointment newInstance(String param1, String param2) {
        FragmentViewAppointment fragment = new FragmentViewAppointment();
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
        View view = inflater.inflate(R.layout.fragment_view_appointment, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarAppointment);
        loadingText = (TextView) view.findViewById(R.id.loadingTextAppointment);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewAppointment);

        arrayList = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        loadingText.setVisibility(View.VISIBLE);

        sharedPreferences = context.getSharedPreferences(LoggedInData, Context.MODE_PRIVATE);
        getUserEmail = sharedPreferences.getString(Email,"");
        BASE_URL = BASE_URL+getUserEmail;

        RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                System.out.println("Length is : "+response.length());
                String fname, lname, hosp, hospdep, date, time, age, gender, address, contact, email;

                for(int i =0; i<response.length(); i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        fname = obj.getString("firstname");
                        lname = obj.getString("lastname");
                        hosp = obj.getString("hospital");
                        hospdep = obj.getString("department");
                        date = obj.getString("date");
                        time = obj.getString("time");
                        gender = obj.getString("gender");
                        age = obj.getString("age");
                        email = obj.getString("patient_email");
                        address = obj.getString("patient_address");
                        contact = obj.getString("contact_number");
                        System.out.println(fname);

                        arrayList.add(new AppointmentViewModelsClass(fname,lname,age,email,address, gender, contact, hosp, hospdep, date, time ));
                        progressBar.setVisibility(View.GONE);
                        loadingText.setVisibility(View.GONE);



                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                        loadingText.setVisibility(View.GONE);
                    }
//                    catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }

                }
//                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewAppointment);
                AppointmentAdapter adapter = new AppointmentAdapter(getContext().getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Getting Error");

            }
        });
        queue.add(jsonArrayRequest);




        return view;
    }
}