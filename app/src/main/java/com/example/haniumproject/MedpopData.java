package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MedpopData {

    @SerializedName("user_Id")
    private String user_Id;

    @SerializedName("num")
    private String num;

    @SerializedName("medName")
    private String medName;

    @SerializedName("medTime")
    private String medTime;

    @SerializedName("medlist")
    private String medlist;

    public MedpopData(String user_Id, String num, String medName, String medTime, String medlist) {
        this.user_Id = user_Id;
        this.num = num;
        this.medName = medName;
        this.medTime = medTime;
        this.medlist = medlist;
    }
}
