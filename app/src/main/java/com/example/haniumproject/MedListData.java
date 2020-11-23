package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MedListData {

    @SerializedName("user_Id")
    private String user_Id;

    public MedListData(String user_Id) {
        this.user_Id = user_Id;
    }
}
