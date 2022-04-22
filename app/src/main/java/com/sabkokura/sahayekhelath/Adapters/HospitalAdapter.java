package com.sabkokura.sahayekhelath.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sabkokura.sahayekhelath.ModelClasses.FAQsModelClass;
import com.sabkokura.sahayekhelath.ModelClasses.HospitalListModelsClass;
import com.sabkokura.sahayekhelath.R;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {
    Context context;
    ArrayList<HospitalListModelsClass> arrayList;

    public HospitalAdapter(Context context, ArrayList<HospitalListModelsClass> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflate = inflater.inflate(R.layout.hospitalslistitem,null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HospitalListModelsClass modelClass = arrayList.get(position);

        holder.hosName.setText(modelClass.getHospitalName());
        holder.hosPhone.setText(modelClass.getHospitalPhone());
        holder.hosadd.setText(modelClass.getHospitalAddress());
        holder.hosBeds.setText(modelClass.getHospitalBeds());
        holder.hosVentilator.setText(modelClass.getHospitalVentilators());

        boolean isExpanded = arrayList.get(position).isExpanded();
        System.out.println("Display Expand: " + isExpanded);
        if(isExpanded){
            holder.moreDetails.setVisibility(View.VISIBLE);
        }
        else {
            holder.moreDetails.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hosName, hosadd, hosPhone, hosBeds, hosVentilator;
        LinearLayout touchToExpand, moreDetails;
        ImageView makeCall;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hosName = itemView.findViewById(R.id.hospitalName);
            hosadd = itemView.findViewById(R.id.hospitalAddress);
            hosPhone = itemView.findViewById(R.id.hospitalPhone);
            hosBeds = itemView.findViewById(R.id.hospitalBeds);
            hosVentilator = itemView.findViewById(R.id.hospitalVentilators);
            touchToExpand = itemView.findViewById(R.id.minDetailsLayout);
            moreDetails = itemView.findViewById(R.id.moreDetails);
            makeCall = itemView.findViewById(R.id.makeCall);

            touchToExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HospitalListModelsClass modelsClass = arrayList.get(getAdapterPosition());
                    modelsClass.setExpanded(!modelsClass.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            makeCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HospitalListModelsClass modelsClass = arrayList.get(getAdapterPosition());
                    String phNumber = "tel:"+modelsClass.getHospitalPhone();
                    phNumber = phNumber.substring(0,14);
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(phNumber));
                    context.getApplicationContext().startActivity(intent);

//                    Toast.makeText(context.getApplicationContext(),phNumber,Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
