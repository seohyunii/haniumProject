package com.example.haniumproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText main_id;
    EditText main_password;
    ImageButton main_loginbtn;
    ImageButton main_joinbtn;
    ProgressBar main_progress;
    ServiceApi service;
    String id;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_id = (EditText) findViewById(R.id.main_id);
        main_password = (EditText) findViewById(R.id.main_password);
        main_loginbtn = (ImageButton) findViewById(R.id.main_loginbtn);
        main_joinbtn = (ImageButton) findViewById(R.id.main_joinbtn);
        main_progress = (ProgressBar) findViewById(R.id.main_progress);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        FirebaseInstanceId.getInstance().getInstanceId()//앱 최초실행시 토큰값 확인
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM Log", "getInstanceId failed", task.getException());
                            return;
                        }
                        token = task.getResult().getToken();
                        Log.d("FCM Log", "FCM 토큰: " + token);
                        //Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
        main_loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        main_joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
    }

    private void attemptLogin() {
        main_id.setError(null);
        main_password.setError(null);

        String id = main_id.getText().toString();
        String password = main_password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            main_id.setError("비밀번호를 입력해주세요.");
            focusView = main_id;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            main_password.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = main_password;
            cancel = true;
        }

        // 아이디의 유효성 검사
        if (id.isEmpty()) {
            main_id.setError("아이디를 입력해주세요.");
            focusView = main_id;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(id, password));
            showProgress(true);
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(MainActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                if (result.getCode() == 200) {
                    Intent loginIntent = new Intent(MainActivity.this, MenuActivity.class);
                    id = result.getUserId();
                    System.out.println("보낼아이디"+id);
                    loginIntent.putExtra("user_id", id);
                    System.out.println("보낼아이디"+result.getUserId());
                    MainActivity.this.startActivity(loginIntent);}
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        main_progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}







