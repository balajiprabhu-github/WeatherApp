package com.balajiprabhu.weatherapp.utils;

import android.content.Intent;

public class ActivityResult {

    int requestCode;
    int resultCode;
    Intent data;

    public ActivityResult(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public Intent getData() {
        return data;
    }

}
