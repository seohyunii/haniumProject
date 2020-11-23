package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MedInfoResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("medTimeApp")
    private String[] medTimeApp;

    @SerializedName("medNameApp")
    private String[] medNameApp;

    @SerializedName("length")
    private int length;


    public int getCode() {
        return code;
    }
    public String[] medTimeApp() {
        return medTimeApp;
    }
    public String[] medNameApp() {
        return medNameApp;
    }

    public int getLength() {
        return length;
    }
}
