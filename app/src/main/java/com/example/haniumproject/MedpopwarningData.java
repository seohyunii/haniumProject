package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MedpopwarningData {

    @SerializedName("user_Id")
    private String user_Id;

    @SerializedName("medlist")
    private String medlist;

    public MedpopwarningData(String user_Id, String medlist) {
        this.user_Id = user_Id;
        this.medlist = medlist;
    }

}
