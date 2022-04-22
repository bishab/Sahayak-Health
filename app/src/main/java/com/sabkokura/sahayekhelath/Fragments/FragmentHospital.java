package com.sabkokura.sahayekhelath.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sabkokura.sahayekhelath.Adapters.FAQsAdapter;
import com.sabkokura.sahayekhelath.Adapters.HospitalAdapter;
import com.sabkokura.sahayekhelath.ModelClasses.HospitalListModelsClass;
import com.sabkokura.sahayekhelath.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHospital#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHospital extends Fragment {
    ArrayList<HospitalListModelsClass> arrayList;
    ProgressBar progressBar;
    HospitalListModelsClass hospitalListModelsClass;
    TextView loadingText;
    RecyclerView recyclerView;
    private final String BASE_URL = "https://corona.askbhunte.com/api/v1/hospitals";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHospital() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHospital.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHospital newInstance(String param1, String param2) {
        FragmentHospital fragment = new FragmentHospital();
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
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        loadingText = (TextView) view.findViewById(R.id.loadingText);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);



        arrayList = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        loadingText.setVisibility(View.VISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject innerArray = jsonArray.getJSONObject(i);
                        String hosName = innerArray.get("name").toString();
                        String hosAdd = innerArray.get("address").toString();
                        String hosPhone = innerArray.get("phone").toString();
                        JSONObject capacity = innerArray.getJSONObject("capacity");
                        String hosBed = capacity.get("beds").toString();
                        String hosVentilators = capacity.get("ventilators").toString();

                        if (hosAdd.length()==0){
                            hosAdd="Not Available";
                        }
                        if (hosPhone.length()==0){
                            hosPhone="Not Available";
                        }
                        if (hosBed.length()==0){
                            hosBed="Not Available";
                        }
                        if (hosVentilators.length()==0){
                            hosVentilators="Not Available";
                        }

                        arrayList.add(new HospitalListModelsClass(hosName, hosAdd, hosPhone, hosBed, hosVentilators));



                        progressBar.setVisibility(View.GONE);
                        loadingText.setVisibility(View.GONE);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HospitalAdapter adapter = new HospitalAdapter(getContext().getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);


        return view;
    }
}