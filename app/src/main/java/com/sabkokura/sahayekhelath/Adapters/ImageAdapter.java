package com.sabkokura.sahayekhelath.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sabkokura.sahayekhelath.R;

public class ImageAdapter extends PagerAdapter {
    public Context context;
    LayoutInflater inflater;


    int[] myimages = new int[]{
            R.drawable.home_ambulance,
            R.drawable.home_appointment,
            R.drawable.home_faqs
    };

    public String[] image_title = {
            "24 x 7 Ambulance Information",
            "Appointment Made Easy!",
            "Confused? Visit FAQs for Answers!"
    };
    public String[] image_desc = {
            "On the Ambulance tab, you can find the contact details of the Ambulance service of different places. You will no longer need to search for urgent contact details of ambulance in an emergency.",
            "Now you can book an appointment for your doctor easily without visiting the clinic in person. Just fill a form and you're good to go!",
            "Find the frequently asked questions for Covid-19 in our FAQs section. You will no longer get confused over the common issues and problems of Covid-19 anymore."
    };

    public ImageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return myimages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home_slider,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimg);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);
//        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(myimages[position]);
        txttitle.setText(image_title[position]);
        description.setText(image_desc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

}
