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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sabkokura.sahayekhelath.ModelClasses.AmbulanceListModelsClass;
import com.sabkokura.sahayekhelath.ModelClasses.HospitalListModelsClass;
import com.sabkokura.sahayekhelath.R;

import java.util.ArrayList;

public class AmbulanceListAdapter extends RecyclerView.Adapter<AmbulanceListAdapter.ViewHolder> {
    Context context;
    ArrayList<AmbulanceListModelsClass> arrayList;
    public AmbulanceListAdapter(Context context, ArrayList<AmbulanceListModelsClass> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflate = inflater.inflate(R.layout.ambulancelistitem,null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AmbulanceListAdapter.ViewHolder holder, int position) {
        AmbulanceListModelsClass modelsClass = arrayList.get(position);

        holder.ambName.setText(modelsClass.getName());
        holder.ambPhone.setText(modelsClass.getPh_number());
        holder.ambadd.setText(modelsClass.getAddress());

        boolean isExpanded = arrayList.get(position).isExpanded();
//        System.out.println("Display Expand: " + isExpanded);
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

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView ambName, ambadd, ambPhone;
        LinearLayout touchToExpand, moreDetails;
        ImageView makeCall;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ambName = itemView.findViewById(R.id.ambulanceName);
            ambadd = itemView.findViewById(R.id.ambulanceAddress);
            ambPhone = itemView.findViewById(R.id.ambulancePhone);
            touchToExpand = itemView.findViewById(R.id.minDetailsLayout);
            moreDetails = itemView.findViewById(R.id.moreDetails);
            makeCall = itemView.findViewById(R.id.makeCall);

            touchToExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AmbulanceListModelsClass modelsClass = arrayList.get(getAdapterPosition());
                    modelsClass.setExpanded(!modelsClass.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            makeCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AmbulanceListModelsClass modelsClass = arrayList.get(getAdapterPosition());
                    String phNumber = "tel:"+modelsClass.getPh_number();
//                    phNumber = phNumber.substring(0,14);
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
