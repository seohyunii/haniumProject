package com.example.haniumproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedActivity extends AppCompatActivity {

    Button med_btn1,med_btn2,med_btn3,med_btn4,med_btn5,med_btn6;
    ImageButton qmark_med;
    String user_id;
    ServiceApi service;
    ProgressBar med_progress;
    String[] medNameList;
    String[] medTimeList;
    String[] medname;
    String[] medtime;
    int i;
    ImageButton med_back;
    public static Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");

        service = RetrofitClient.getClient().create(ServiceApi.class);
        med_progress = (ProgressBar) findViewById(R.id.med_progress);
        med_back = (ImageButton) findViewById(R.id.med_back);
        qmark_med = (ImageButton) findViewById(R.id.qmark_med);

        mcontext = this;
        startmedlist(new MedListData(user_id));

        System.out.println(user_id + "!!!!!!!!");

        med_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        qmark_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Med_q_Intent = new Intent(MedActivity.this, Med_q_Activity.class);
                Med_q_Intent.putExtra("user_id", user_id);
                MedActivity.this.startActivity(Med_q_Intent);
            }
        });

        Button.OnClickListener btnListener = new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.med_btn1:
                        Intent pop1Intent = new Intent(MedActivity.this, MedpopActivity.class);
                        pop1Intent.putExtra("num","1");
                        pop1Intent.putExtra("user_id",user_id);
                        startActivityForResult(pop1Intent, 1);
                        break;
                    case R.id.med_btn2:
                        Intent pop2Intent = new Intent(MedActivity.this, MedpopActivity.class);
                        pop2Intent.putExtra("num","2");
                        pop2Intent.putExtra("user_id",user_id);
                        startActivityForResult(pop2Intent, 1);
                        break;
                    case R.id.med_btn3:
                        Intent pop3Intent = new Intent(MedActivity.this, MedpopActivity.class);
                        pop3Intent.putExtra("num","3");
                        pop3Intent.putExtra("user_id",user_id);
                        startActivityForResult(pop3Intent, 1);
                        break;
                    case R.id.med_btn4:
                        Intent pop4Intent = new Intent(MedActivity.this, MedpopActivity.class);
                        pop4Intent.putExtra("num","4");
                        pop4Intent.putExtra("user_id",user_id);
                        startActivityForResult(pop4Intent, 1);
                        break;
                    case R.id.med_btn5:
                        Intent pop5Intent = new Intent(MedActivity.this, MedpopActivity.class);
                        pop5Intent.putExtra("num","5");
                        pop5Intent.putExtra("user_id",user_id);
                        startActivityForResult(pop5Intent, 1);
                        break;
                    case R.id.med_btn6:
                        Intent pop6Intent = new Intent(MedActivity.this, MedpopActivity.class);
                        pop6Intent.putExtra("num","6");
                        pop6Intent.putExtra("user_id",user_id);
                        startActivityForResult(pop6Intent, 1);
                        break;
                }
            }
        };

        med_btn1 = (Button) findViewById(R.id.med_btn1);
        med_btn2 = (Button) findViewById(R.id.med_btn2);
        med_btn3 = (Button) findViewById(R.id.med_btn3);
        med_btn4 = (Button) findViewById(R.id.med_btn4);
        med_btn5 = (Button) findViewById(R.id.med_btn5);
        med_btn6 = (Button) findViewById(R.id.med_btn6);

        med_btn1.setOnClickListener(btnListener);
        med_btn2.setOnClickListener(btnListener);
        med_btn3.setOnClickListener(btnListener);
        med_btn4.setOnClickListener(btnListener);
        med_btn5.setOnClickListener(btnListener);
        med_btn6.setOnClickListener(btnListener);

    }

    private void showProgress(boolean show) {
        med_progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    // medtime 설정
    public void startmedlist(MedListData data) {
        service.userMedList(data).enqueue(new Callback<MedListResponse>() {
            @Override
            public void onResponse(Call<MedListResponse> call, Response<MedListResponse> response) {
                MedListResponse result = response.body();
                Toast.makeText(MedActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                medNameList = result.getmedNameList();
                medTimeList = result.getmedTimeList();

                System.out.println(medTimeList + "!!!!!!!!");

                    for (int i = 0; i < 6; i++) {
                        switch (i) {
                            case 0:
                                if (medNameList[0] == null) {
                                    med_btn1.setText("1번칸" + "\n");
                                } else {
                                    med_btn1.setText("1번칸" + "\n" + "약 이름 : " + medNameList[0] + "\n" + "약 주기 : " + medTimeList[0] + "시간");
                                }
                                break;
                            case 1:
                                if (medNameList[1] == null) {
                                    med_btn2.setText("2번칸" + "\n");
                                } else {
                                    med_btn2.setText("2번칸" + "\n" + "약 이름 : " + medNameList[1] + "\n" + "약 주기 : " + medTimeList[1] + "시간");
                                }
                                break;
                            case 2:
                                if (medNameList[2] == null) {
                                    med_btn3.setText("3번칸" + "\n");
                                } else {
                                    med_btn3.setText("3번칸" + "\n" + "약 이름 : " + medNameList[2] + "\n" + "약 주기 : " + medTimeList[2] + "시간");
                                }
                                break;
                            case 3:
                                if (medNameList[3] == null) {
                                    med_btn4.setText("4번칸" + "\n");
                                } else {
                                    med_btn4.setText("4번칸" + "\n" + "약 이름 : " + medNameList[3] + "\n" + "약 주기 : " + medTimeList[3] + "시간");
                                }
                                break;
                            case 4:
                                if (medNameList[4] == null) {
                                    med_btn5.setText("5번칸" + "\n");
                                } else {
                                    med_btn5.setText("5번칸" + "\n" + "약 이름 : " + medNameList[4] + "\n" + "약 주기 : " + medTimeList[4] + "시간");
                                }
                                break;
                            case 5:
                                if (medNameList[5] == null) {
                                    med_btn6.setText("6번칸" + "\n");
                                } else {
                                    med_btn6.setText("6번칸" + "\n" + "약 이름 : " + medNameList[5] + "\n" + "약 주기 : " + medTimeList[5] + "시간");
                                }
                                break;

                        }

                    }
                }


            @Override
            public void onFailure(Call<MedListResponse> call, Throwable t) {
                Toast.makeText(MedActivity.this, "리스트 불러오기 에러", Toast.LENGTH_SHORT).show();
                Log.e("리스트 불러오기 에러", t.getMessage());
                showProgress(false);
            }
        });
    }
}
