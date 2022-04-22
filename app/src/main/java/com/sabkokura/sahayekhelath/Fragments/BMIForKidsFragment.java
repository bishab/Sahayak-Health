package com.sabkokura.sahayekhelath.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.sabkokura.sahayekhelath.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BMIForKidsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BMIForKidsFragment extends Fragment {
    TextInputEditText kidsWeight, kidsHeight;
    float wt, ht;
    TextView bmiScore, bmiRemark;
    CardView result;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BMIForKidsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BMIForKidsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BMIForKidsFragment newInstance(String param1, String param2) {
        BMIForKidsFragment fragment = new BMIForKidsFragment();
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

        View view = inflater.inflate(R.layout.fragment_b_m_i_for_kids, container, false);

        kidsHeight = (TextInputEditText) view.findViewById(R.id.kidsHeight);
        kidsWeight = (TextInputEditText) view.findViewById(R.id.kidsWeight);
        result = (CardView) view.findViewById(R.id.cardResult);

        bmiScore = (TextView) view.findViewById(R.id.bmiScore);
        bmiRemark = (TextView) view.findViewById(R.id.bmiRemark);



        kidsWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0) {
                    wt = Float.parseFloat(charSequence.toString());
                    if (wt > 0 && ht > 0) {
                        calculateBMI(wt, ht);
                    }
                }
                else {
                    result.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        kidsHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0) {
                    ht = Float.parseFloat(charSequence.toString());
                    if (wt > 0 && ht > 0) {
                        calculateBMI(wt, ht);
                    }
                }
                else {
                    result.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        System.out.println("H: " + ht + "W: " + wt);





//        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

        return view;
    }
    public void calculateBMI(float wts, float hts){

        result.setVisibility(View.VISIBLE);
        hts = hts * hts;
        float BMI_SCORE = (wts / hts);
        if(BMI_SCORE<5){
            result.setCardBackgroundColor(getResources().getColor(R.color.underBMI));
            bmiScore.setText(""+String.format("%.2f",BMI_SCORE));
            bmiRemark.setText("Under Weight");
        }
        else if (BMI_SCORE>=5 && BMI_SCORE<84){
            result.setCardBackgroundColor(getResources().getColor(R.color.normalBMI));
            bmiScore.setText(""+String.format("%.2f",BMI_SCORE));
            bmiRemark.setText("Healthy Weight");


        }
        else if (BMI_SCORE>=85 && BMI_SCORE<95){
            result.setCardBackgroundColor(getResources().getColor(R.color.overBMI));
            bmiScore.setText(""+String.format("%.2f",BMI_SCORE));
            bmiRemark.setText("Over Weight");


        }
        else {
            result.setCardBackgroundColor(getResources().getColor(R.color.obseBMI));
            bmiScore.setText(""+String.format("%.2f",BMI_SCORE));
            bmiRemark.setText("Obese");


        }




    }

}