package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MeddelData{

    @SerializedName("user_Id")
    private String user_Id;

    @SerializedName("num")
    private String num;

    public MeddelData(String user_Id, String num) {
        this.user_Id = user_Id;
        this.num = num;
    }
}