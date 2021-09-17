package com.cuidar.app_cer.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cuidar.app_cer.user_preferences.ActivityData;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Response;

public class Util {
    public static void showToast(Context context, String message, Integer length){
        Integer toastLength = length == null ? Toast.LENGTH_LONG : length;

        Toast.makeText(
                context, message, toastLength
        ).show();
    }

    public static void whenNotSuccessful(Response response, Context context, String tagMsg){
        try {
            JSONObject error = new JSONObject(response.errorBody().string());
            String errorMsg = error.getString("error");

            Log.d("ERROR", tagMsg + " " + errorMsg);
            Log.d("ERROR", tagMsg + " CODE: " + response.code());

            Util.showToast(context, errorMsg, null);
        } catch (Exception e) {
            Log.d("ERROR", "ERROR: " + e.getMessage());
        }
    }

    public static String getAccessToken(Context context){
        ActivityData dataFile = new ActivityData(context);

        String accessToken = "Bearer " +  dataFile.getToken();

        return accessToken;
    }

    public static Boolean isValid (String date, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try {
            sdf.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }
}