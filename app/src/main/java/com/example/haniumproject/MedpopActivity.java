package com.example.haniumproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedpopActivity extends AppCompatActivity {

    ProgressBar pop_progress;
    ServiceApi service;
    String num;
    String user_id;
    String name;
    String time;
    EditText mednametext;
    EditText medtimetext;
    RadioButton med_radio;
    String[] medNameList;
    String[] medTimeList;
    String[] medTextList;
    String medlist;
    public static Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pop);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        mednametext = (EditText) findViewById(R.id.mednameText);
        medtimetext = (EditText) findViewById(R.id.medtimeText);
        med_radio = (RadioButton) findViewById(R.id.med_radio);
        ImageButton pop_cancle = (ImageButton) findViewById(R.id.pop_cancle);
        ImageButton pop_del = (ImageButton) findViewById(R.id.pop_del);
        ImageButton pop_save = (ImageButton) findViewById(R.id.pop_save);
        TextView textmed = (TextView) findViewById(R.id.textmed);
        pop_progress = (ProgressBar) findViewById(R.id.pop_progress);


        Intent intent = getIntent();
        num = intent.getStringExtra("num");
        user_id = intent.getStringExtra("user_id");

        textmed.setText(num + "번째 약 등록 정보");
        System.out.println(user_id + "!!!!!!!!");

        mcontext = this;

        startmedpoplist(new MedpopListData(user_id, num));

        Spinner med_spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter med_Adapter = ArrayAdapter.createFromResource(this,
                R.array.med_array, android.R.layout.simple_spinner_item);
        med_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        med_spinner.setAdapter(med_Adapter);

        med_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object i = parent.getItemAtPosition(position);
                medlist = i.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pop_cancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                ((MedActivity)MedActivity.mcontext).startmedlist(new MedListData(user_id));
            }
        });

        pop_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attemptmed();
            }
        });

        pop_del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startmeddel(new MeddelData(user_id, num));
            }
        });

    }

    private void attemptmed() {

        mednametext.setError(null);
        medtimetext.setError(null);

        System.out.println(medlist);
        name = mednametext.getText().toString();
        time = medtimetext.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 약 이름의 유효성 검사
        if (name.isEmpty()) {
            mednametext.setError("약 이름을 입력하세요");
            focusView = mednametext;
            cancel = true;
        }

        // 약 주기의 유효성 검사
        if (time.isEmpty()) {
            medtimetext.setError("약 주기를 입력하세요");
            focusView = medtimetext;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startmedwarning(new MedpopwarningData(user_id, medlist));
            showProgress(true);
        }
    }

    //바깥레이어 클릭시 안닫히게
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    // 안드로이드 백버튼 막기
    @Override
    public void onBackPressed(){
        return;
    }

    private void showProgress(boolean show) {
        pop_progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    // medtime 등록
    public void startmedtime(MedpopData data) {
        service.userMed(data).enqueue(new Callback<MedpopResponse>() {
            @Override
            public void onResponse(Call<MedpopResponse> call, Response<MedpopResponse> response) {
                MedpopResponse result = response.body();
                Toast.makeText(MedpopActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    startmedpoplist(new MedpopListData(user_id, num));
                    System.out.println("약 등록");
                }else if(result.getCode() == 100){
                    Toast.makeText(MedpopActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MedpopResponse> call, Throwable t) {
                Toast.makeText(MedpopActivity.this, "약 등록 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("약 등록 에러 발생", t.getMessage());
                System.out.println("약 등록 에러");
                showProgress(false);
            }
        });
    }

    // med 종류 비교 및 경고
    private void startmedwarning(MedpopwarningData data) {
        service.userMedpopwarning(data).enqueue(new Callback<MedpopwarningResponse>() {
            @Override
            public void onResponse(Call<MedpopwarningResponse> call, Response<MedpopwarningResponse> response) {
                MedpopwarningResponse result = response.body();
                //Toast.makeText(MedpopActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                //System.out.println(result.getMessage());

                if(result.getMessage() == null){
                    startmedtime(new MedpopData(user_id, num, name, time, medlist));
                }else{
                    Intent pop1Intent = new Intent(MedpopActivity.this, MedpopWarningActivity.class);
                    pop1Intent.putExtra("num",num);
                    pop1Intent.putExtra("user_id",user_id);
                    pop1Intent.putExtra("message",result.getMessage());
                    pop1Intent.putExtra("s_medname",result.gets_medname());
                    pop1Intent.putExtra("name",name);
                    pop1Intent.putExtra("time",time);
                    pop1Intent.putExtra("medlist",medlist);
                    startActivityForResult(pop1Intent, 1);
                }
            }

            @Override
            public void onFailure(Call<MedpopwarningResponse> call, Throwable t) {
                Toast.makeText(MedpopActivity.this, "약 종류 괜찮음", Toast.LENGTH_SHORT).show();
                Log.e("약 종류 괜찮음", t.getMessage());
                System.out.println("약 종류 괜찮음");
                showProgress(false);
            }
        });
    }

    // medList pop출력
    private void startmedpoplist(MedpopListData data) {
        service.userMedpopList(data).enqueue(new Callback<MedpopListResponse>() {
            @Override
            public void onResponse(Call<MedpopListResponse> call, Response<MedpopListResponse> response) {
                MedpopListResponse result = response.body();
                Toast.makeText(MedpopActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                medNameList = result.getmedNameList();
                medTimeList = result.getmedTimeList();
                medTextList = result.getMedTextList();

                if (result.getCode() == 200) {
                    med_radio.setText("약 이름 : " + medNameList[0] + "\n"+ "약 주기 : " + medTimeList[0] +"\n" +"  종류   :" +medTextList[0]);
                }else{
                    if (medNameList[0] == null) {
                        med_radio.setText("");
                    }
                }
            }

            @Override
            public void onFailure(Call<MedpopListResponse> call, Throwable t) {
                Toast.makeText(MedpopActivity.this, "리스트 불러오기 에러", Toast.LENGTH_SHORT).show();
                Log.e("리스트 불러오기 에러", t.getMessage());
                showProgress(false);
            }
        });
    }


    // med 정보 삭제
    private void startmeddel(MeddelData data) {
        service.userMeddel(data).enqueue(new Callback<MeddelResponse>() {
            @Override
            public void onResponse(Call<MeddelResponse> call, Response<MeddelResponse> response) {
                MeddelResponse result = response.body();
                Toast.makeText(MedpopActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    startmedpoplist(new MedpopListData(user_id, num));
                }
            }

            @Override
            public void onFailure(Call<MeddelResponse> call, Throwable t) {
                Toast.makeText(MedpopActivity.this, "약 정보 삭제 에러", Toast.LENGTH_SHORT).show();
                Log.e("약 정보 삭제 에러 ", t.getMessage());
                showProgress(false);
            }
        });
    }
}
