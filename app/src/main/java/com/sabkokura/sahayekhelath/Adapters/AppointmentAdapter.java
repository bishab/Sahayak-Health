package com.sabkokura.sahayekhelath.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sabkokura.sahayekhelath.ModelClasses.AmbulanceListModelsClass;
import com.sabkokura.sahayekhelath.ModelClasses.AppointmentViewModelsClass;
import com.sabkokura.sahayekhelath.ModelClasses.FAQsModelClass;
import com.sabkokura.sahayekhelath.R;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder>{

    ArrayList<AppointmentViewModelsClass> arrayList;
    Context context;

    public AppointmentAdapter(Context context, ArrayList<AppointmentViewModelsClass> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflate = inflater.inflate(R.layout.appointment_view,null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {
        AppointmentViewModelsClass modelsClass = arrayList.get(position);
        holder.fullname.setText(modelsClass.getFirstName()+" "+modelsClass.getLastName());
        holder.hosName.setText(modelsClass.getHospital());
        holder.hosDept.setText(modelsClass.getDepartment());
        holder.time.setText(modelsClass.getDate()+ " at "+ modelsClass.getTime());
        holder.age.setText(modelsClass.getAge());
        holder.email.setText(modelsClass.getEmail());
        holder.gender.setText(modelsClass.getGender());
        holder.address.setText(modelsClass.getAddress());
        holder.number.setText(modelsClass.getNumber());

        boolean isExpanded = arrayList.get(position).isExpanded();
//        System.out.println("Display Expand: " + isExpanded);
        if(isExpanded){
            holder.moreDetails.setVisibility(View.VISIBLE);
            holder.icon.setColorFilter(ContextCompat.getColor(context,R.color.black));
            holder.icon.setImageResource(R.drawable.icon_collapse);
        }
        else {
            holder.moreDetails.setVisibility(View.GONE);
            holder.icon.setColorFilter(ContextCompat.getColor(context,R.color.black));
            holder.icon.setImageResource(R.drawable.icon_expand);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hosName, hosDept, time,fullname, age, email, address, gender, number;
        ImageView icon;
        LinearLayout touchToExpand;
        CardView moreDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hosName = itemView.findViewById(R.id.viewAppHospitalName);
            hosDept = itemView.findViewById(R.id.viewAppDepartment);
            time = itemView.findViewById(R.id.viewAppTime);
            fullname = itemView.findViewById(R.id.appViewFullName);
            age = itemView.findViewById(R.id.appViewAge);
            email = itemView.findViewById(R.id.appViewEmail);
            address = itemView.findViewById(R.id.appViewAdderss);
            gender = itemView.findViewById(R.id.appViewGender);
            number = itemView.findViewById(R.id.appViewContact);

            icon = itemView.findViewById(R.id.expandIcon);

            touchToExpand = itemView.findViewById(R.id.minDetailsLayout);
            moreDetails = itemView.findViewById(R.id.moreDetails);

            touchToExpand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppointmentViewModelsClass modelsClass = arrayList.get(getAdapterPosition());
                    modelsClass.setExpanded(!modelsClass.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });



        }
    }
}
