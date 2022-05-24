package com.sabkokura.sahayekhelath.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.sabkokura.sahayekhelath.ModelClasses.FAQsModelClass;
import com.sabkokura.sahayekhelath.R;

import java.util.ArrayList;

public class FAQsAdapter extends RecyclerView.Adapter<FAQsAdapter.ViewHolder> {
    ArrayList<FAQsModelClass> arrayList;
    Context context;

    public FAQsAdapter(Context context, ArrayList<FAQsModelClass> arrayList){
        this.arrayList = arrayList;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflate = inflater.inflate(R.layout.faqslistitem,null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FAQsModelClass modelClass = arrayList.get(position);

        holder.faqQuestion.setText(modelClass.getQuestion());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.showAnswer.setText(Html.fromHtml(modelClass.getAnswer(),Html.FROM_HTML_MODE_LEGACY));
        }

        boolean isExpanded = arrayList.get(position).isExpanded();

        if (isExpanded){
            holder.showAnswer.setVisibility(View.VISIBLE);
            holder.icon.setColorFilter(ContextCompat.getColor(context,R.color.black));
            holder.icon.setImageResource(R.drawable.icon_collapse);

        }
        else {
            holder.showAnswer.setVisibility(View.GONE);
            holder.icon.setColorFilter(ContextCompat.getColor(context,R.color.black));
            holder.icon.setImageResource(R.drawable.icon_expand);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView faqQuestion, showAnswer;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            faqQuestion = itemView.findViewById(R.id.mainQuestion);
            showAnswer = itemView.findViewById(R.id.answer);
            icon = itemView.findViewById(R.id.expandIcon);

            faqQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FAQsModelClass modelClass = arrayList.get(getAdapterPosition());
                    modelClass.setExpanded(!modelClass.isExpanded());
//                    icon.setBackgroundColor(Color.parseColor("000"));
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
