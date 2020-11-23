package com.example.haniumproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Menu_q_Activity extends AppCompatActivity {


    Adapter adapter;
    ViewPager viewPager;
    ImageButton menu_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_qmark);

        viewPager = (ViewPager) findViewById(R.id.view2);
        menu_back = (ImageButton) findViewById(R.id.menu_back);

        menu_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new Adapter(this);
        viewPager.setAdapter(adapter);
    }

}

