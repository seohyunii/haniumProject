package com.example.haniumproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Med_Adapter extends PagerAdapter {
    private int[] images = {R.drawable.med_qmark_1 , R.drawable.med_qmark_2};
    private LayoutInflater inflater;
    private Context context;

    public Med_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_med_slider, container , false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView3);
        imageView.setImageResource(images[position]);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container,int position, Object object){
        container.invalidate();
    }
}
