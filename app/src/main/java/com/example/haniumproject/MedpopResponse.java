package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MedpopResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("medName")
    private String medName;

    @SerializedName("medTime")
    private String medTime;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getmedName() {
        return medName;
    }

    public String getmedTime() {
        return medTime;
    }


}
