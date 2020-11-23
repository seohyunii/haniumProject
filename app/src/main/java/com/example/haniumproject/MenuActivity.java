package com.example.haniumproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    String user_id;
    ImageButton menu_med,menu_pharm,menu_back,menu_medinfo,qmark,menu_calc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");

        System.out.println(user_id + "!!!!!!!!");

        menu_med = (ImageButton) findViewById(R.id.menu_med);
        menu_pharm=(ImageButton) findViewById(R.id.menu_gps);
        menu_calc = (ImageButton) findViewById(R.id.menu_calc);
        menu_back = (ImageButton) findViewById(R.id.menu_back);
        menu_medinfo = (ImageButton) findViewById(R.id.menu_medinfo);

        qmark = (ImageButton) findViewById(R.id.qmark);

        menu_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        menu_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent medIntent = new Intent(MenuActivity.this, MedActivity.class);
                medIntent.putExtra("user_id", user_id);
                MenuActivity.this.startActivity(medIntent);
            }
        });
        menu_pharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(MenuActivity.this,PharmActivity.class);
                MenuActivity.this.startActivity(mIntent);
            }
        });
        menu_medinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent medinfoIntent = new Intent(MenuActivity.this, MedInfoActivity.class);
                medinfoIntent.putExtra("user_id", user_id);
                MenuActivity.this.startActivity(medinfoIntent);
            }
        });
        menu_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menu_calc_Intent = new Intent(MenuActivity.this, CalcActivity.class);
                menu_calc_Intent.putExtra("user_id", user_id);
                MenuActivity.this.startActivity(menu_calc_Intent);
            }
        });
        qmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Menu_q_Intent = new Intent(MenuActivity.this, Menu_q_Activity.class);
                Menu_q_Intent.putExtra("user_id", user_id);
                MenuActivity.this.startActivity(Menu_q_Intent);
            }
        });

    }
}
