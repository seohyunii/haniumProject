package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MedpopwarningResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("s_medname")
    private String s_medname;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String gets_medname() {
        return s_medname;
    }
}
