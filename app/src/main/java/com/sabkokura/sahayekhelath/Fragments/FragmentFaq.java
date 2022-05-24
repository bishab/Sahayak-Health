package com.sabkokura.sahayekhelath.Fragments;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.sabkokura.sahayekhelath.APIs.WebApi;
import com.sabkokura.sahayekhelath.Adapters.FAQsAdapter;
import com.sabkokura.sahayekhelath.ModelClasses.FAQsModelClass;
import com.sabkokura.sahayekhelath.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFaq#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFaq extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FAQsModelClass> arrayList;
    public final String BASE_URL = "https://nyc-cto.github.io/coronavirus-answers/answers.json";
    ProgressBar progressBar;
    TextView loading;
    String lan;
    String question, answer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public FragmentFaq(String lan){
        this.lan = lan;
    }
    public FragmentFaq() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFaq.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFaq newInstance(String param1, String param2) {
        FragmentFaq fragment = new FragmentFaq();
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
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        loading = (TextView) view.findViewById(R.id.loadingText);

        arrayList = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);

        

        RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                System.out.println("Length is : "+response.length());

                for(int i =0; i<response.length(); i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        question =  obj.get("title").toString();
                        answer =  obj.get("content").toString();

//                        answer = java.net.URLDecoder.decode(answer,"UTF-8");
                        answer = answer.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                        answer = answer.replaceAll("\\+", "%2B");
                        answer = URLDecoder.decode(answer,"utf-8");
                        arrayList.add(new FAQsModelClass(question,null,answer,null));
                        progressBar.setVisibility(View.GONE);
                        loading.setVisibility(View.GONE);



                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                        loading.setVisibility(View.GONE);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                FAQsAdapter adapter = new FAQsAdapter(getContext().getApplicationContext(), arrayList);
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