package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("userId")
    String userId;

    @SerializedName("userPwd")
    String userPwd;

    public LoginData(String userId, String userPwd) {
        this.userId = userId;
        this.userPwd = userPwd;
    }
}
