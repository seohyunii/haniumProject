package com.example.haniumproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MedpopWarningActivity extends AppCompatActivity {

    TextView warning;
    TextView warning2;
    TextView warning3;
    ImageButton w_save;
    ImageButton w_close;
    String num;
    String user_id;
    String name;
    String time;
    String medlist;
    String message;
    String mednum;
    String medname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_warning);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        num = intent.getStringExtra("num");
        name = intent.getStringExtra("name");
        time = intent.getStringExtra("time");
        medlist = intent.getStringExtra("medlist");
        message = intent.getStringExtra("message");
        medname = intent.getStringExtra("s_medname");

        warning2 = (TextView) findViewById(R.id.warning2);
        warning3 = (TextView) findViewById(R.id.warning3);
        w_save = (ImageButton) findViewById(R.id.w_save);
        w_close = (ImageButton) findViewById(R.id.w_close);


        w_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                ((MedpopActivity)MedpopActivity.mcontext).startmedtime(new MedpopData(user_id, num, name, time, medlist));
            }
        });

        w_close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /*warning.setText("약 이름 : " + name +"\n"+ "약 주기 : " + time + "시간" +"\n"+ "약 종류 : " + medlist);*/

        if(medname == null){
            warning2.setText("");
        }else {
            warning2.setText("현재 약통에 " + medname + "\n" + " (이)가 저장되어 있습니다.");
            warning2.setGravity(Gravity.CENTER);
        }

        warning3.setText(message);
        warning3.setGravity(Gravity.CENTER);


    }
}
