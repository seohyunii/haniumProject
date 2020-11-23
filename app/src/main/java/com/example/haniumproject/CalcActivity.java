package com.example.haniumproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CalcActivity extends AppCompatActivity {

    ArrayAdapter<CharSequence> adsex_pin2, adage_pin3, adlist_pin1;
    String choice_do="";
    String choice_se="";
    ImageButton menu_back;
    ImageButton btn_refresh;
    String num;
    ImageView result_calc;
    String user_id;
    Spinner spinner_sex, spinner_age, spinner_list;
    String sex_text, age_text, list_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        Intent intent = getIntent();
        num = intent.getStringExtra("num");
        user_id = intent.getStringExtra("user_id");

        menu_back = (ImageButton) findViewById(R.id.menu_back);
        result_calc = (ImageView) findViewById(R.id.calc_result);
        btn_refresh = (ImageButton) findViewById(R.id.btn_refresh);

        final Spinner spinner_sex = (Spinner) findViewById(R.id.spinner_sex);
        final Spinner spinner_list = (Spinner) findViewById(R.id.spinner_list);


        // 1번 : 약 종류 먼저 입력
        adlist_pin1 = ArrayAdapter.createFromResource(this, R.array.medlist_array, android.R.layout.simple_spinner_dropdown_item);
        spinner_list.setAdapter(adlist_pin1);
        spinner_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // 아래 if문의 약 종류가 입력됐으면 성별에 -> 남/여
                if(adlist_pin1.getItem(i).equals("비타민A") || adlist_pin1.getItem(i).equals("비타민B6") || adlist_pin1.getItem(i).equals("비타민C")
                        || adlist_pin1.getItem(i).equals("마그네슘")|| adlist_pin1.getItem(i).equals("비타민B6") || adlist_pin1.getItem(i).equals("철분")
                        || adlist_pin1.getItem(i).equals("티아민") || adlist_pin1.getItem(i).equals("리보플래빈") || adlist_pin1.getItem(i).equals("니아신")) {

                    choice_do = adlist_pin1.getItem(i).toString();

                    adsex_pin2 = ArrayAdapter.createFromResource(CalcActivity.this, R.array.sex_array, android.R.layout.simple_spinner_dropdown_item);
                    adsex_pin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_sex.setAdapter(adsex_pin2);
                    adsex_pin2.notifyDataSetChanged();

                    spinner_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adsex_pin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                    // // 아래 else if문의 약 종류가 입력됐으면 성별에 -> 상관없음
                }else{
                    choice_do = adlist_pin1.getItem(i).toString();

                    adsex_pin2 = ArrayAdapter.createFromResource(CalcActivity.this, R.array.sex_empty_array, android.R.layout.simple_spinner_dropdown_item);
                    adsex_pin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_sex.setAdapter(adsex_pin2);
                    adsex_pin2.notifyDataSetChanged();
                    spinner_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_se = adsex_pin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }

                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

                btn_refresh.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(choice_do.equals("비타민A") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.vita_men);
                        }
                        if(choice_do.equals("비타민A") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.vita_w);
                        }

                        if(choice_do.equals("비타민B6") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.vitab6_men);
                        }
                        if(choice_do.equals("비타민B6") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.vitab6_women);
                        }

                        if(choice_do.equals("비타민C") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.vitac_men);
                        }
                        if(choice_do.equals("비타민C") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.vitac_women);
                        }

                        if(choice_do.equals("마그네슘") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.mag_men);
                        }
                        if(choice_do.equals("마그네슘") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.mag_women);
                        }

                        if(choice_do.equals("철분") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.chul_men);
                        }
                        if(choice_do.equals("철분") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.chul_women);
                        }

                        if(choice_do.equals("티아민") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.tia_men);
                        }
                        if(choice_do.equals("티아민") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.tia_women);
                        }

                        if(choice_do.equals("리보플래빈") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.repo_men);
                        }
                        if(choice_do.equals("리보플래빈") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.repo_women);
                        }

                        if(choice_do.equals("니아신") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.nia_men);
                        }
                        if(choice_do.equals("니아신") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.nia_women);
                        }
                        if(choice_do.equals("엽산") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.san_men);
                        }
                        if(choice_do.equals("엽산") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.san_women);
                        }
                        if(choice_do.equals("칼슘") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.cal_men);
                        }
                        if(choice_do.equals("칼슘") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.cal_women);
                        }
                        if(choice_do.equals("인") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.in_men);
                        }
                        if(choice_do.equals("인") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.in_women);
                        }
                        if(choice_do.equals("아연") && choice_se.equals("남자")){
                            result_calc.setImageResource(R.drawable.yeon_men);
                        }
                        if(choice_do.equals("아연") && choice_se.equals("여자")){
                            result_calc.setImageResource(R.drawable.yeon_women);
                        }
                        if(choice_do.equals("비타민E")){
                            result_calc.setImageResource(R.drawable.vitae_all);
                        }
                        if(choice_do.equals("비타민D")){
                            result_calc.setImageResource(R.drawable.vitad_all);
                        }

            }
        });


        menu_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
