package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("userName")
    private String userName;

    @SerializedName("userId")
    private String userId;

    @SerializedName("userPwd")
    private String userPwd;

    @SerializedName("robot_number")
    private String robot_number;

    @SerializedName("userToken")
    private String userToken;

    public JoinData(String userName, String userId, String userPwd, String robot_number,String userToken) {
        this.userName = userName;
        this.userId = userId;
        this.userPwd = userPwd;
        this.robot_number = robot_number;
        this.userToken=userToken;
    }
}
