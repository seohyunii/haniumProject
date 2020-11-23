package com.example.haniumproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    EditText join_name;
    EditText join_password;
    EditText join_id;
    EditText robot_number;
    Button join_joinbtn;
    ProgressBar join_progress;
    private String getToken;
    ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        getToken=getIntent().getStringExtra("token");//메인 액티비티로부터 토큰값을 넘겨받음
        join_name = (EditText) findViewById(R.id.join_name);
        join_id = (EditText) findViewById(R.id.join_id);
        join_password = (EditText) findViewById(R.id.join_password);
        robot_number = (EditText) findViewById(R.id.robot_number);
        join_joinbtn = (Button) findViewById(R.id.join_joinbtn);
        join_progress = (ProgressBar) findViewById(R.id.join_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        join_joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptJoin();
            }
        });
    }

    private void attemptJoin() {
        join_name.setError(null);
        join_id.setError(null);
        join_password.setError(null);
        robot_number.setError(null);

        String name = join_name.getText().toString();
        String id = join_id.getText().toString();
        String password = join_password.getText().toString();
        String number = robot_number.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            join_password.setError("비밀번호를 입력해주세요.");
            focusView = join_id;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            join_password.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = join_password;
            cancel = true;
        }

        // 아이디의 유효성 검사
        if (id.isEmpty()) {
            join_id.setError("아이디를 입력해주세요.");
            focusView = join_id;
            cancel = true;
        }

        // 이름의 유효성 검사
        if (name.isEmpty()) {
            join_name.setError("이름을 입력해주세요.");
            focusView = join_name;
            cancel = true;
        }

        // 로봇 번호 유효성 검사
        if (name.isEmpty()) {
            robot_number.setError("로봇 번호를 입력해주세요.");
            focusView = robot_number;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(name, id, password, number,getToken));
            showProgress(true);
        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isPasswordValid(String password) { return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        join_progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
