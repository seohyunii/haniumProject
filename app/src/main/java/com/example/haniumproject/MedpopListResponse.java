package com.example.haniumproject;

import com.google.gson.annotations.SerializedName;

public class MedpopListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("length")
    private int length;

    @SerializedName("medNameList")
    private String[] medNameList;

    @SerializedName("medTimeList")
    private String[] medTimeList;

    @SerializedName("medTextList")
    private String[] medTextList;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getLength() {
        return length;
    }

    public String[] getmedNameList() {
        return medNameList;
    }

    public String[] getmedTimeList() {
        return medTimeList;
    }

    public String[] getMedTextList() { return medTextList; }

}
