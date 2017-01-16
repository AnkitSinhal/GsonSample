package com.androidjavapoint.gsonsample;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static String loadJsonFromAsset(Context context, String fileName) {
        String json;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
