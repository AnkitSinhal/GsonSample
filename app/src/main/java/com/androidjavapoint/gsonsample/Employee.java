package com.androidjavapoint.gsonsample;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Employee {
    @SerializedName("Id")
    public long id;

    @SerializedName("PersonalDetails")
    public PersonalDetails personalDetails;
}
