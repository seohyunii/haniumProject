package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MeddelResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

