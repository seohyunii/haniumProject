package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MedInfoData {
    @SerializedName("senddate")
    String senddate;

    @SerializedName("user_id")
    String user_id;

    public MedInfoData(String senddate, String user_id) {
        this.senddate = senddate;
        this.user_id = user_id;
    }
}
