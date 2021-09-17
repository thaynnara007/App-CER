package com.cuidar.app_cer.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cuidar.app_cer.R;
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

    public static int getIcon (String iconName) {
        switch (iconName){
            case Constants.PLATE_ICON:
                return R.drawable.meal_vector;
            case Constants.HYGIENE_ICON:
                return R.drawable.hygine_vector;
            case Constants.HANGER_ICON:
                return R.drawable.hanger_vector;
            case Constants.APPLE_ICON:
                return R.drawable.apple_vector;
            case Constants.SOUP_ICON:
                return R.drawable.soup_vector;
            case Constants.DRINK_ICON:
                return R.drawable.liquid_vector;
            case Constants.BODY_ICON:
                return R.drawable.body_vector;
            case Constants.TOOTH_ICON:
                return R.drawable.tooth_vector;
            case Constants.SHIRT_ICON:
                return R.drawable.shiert_vector;
            case Constants.PANTS_ICON:
                return R.drawable.pants_vector;
            case Constants.CAT_ICON:
                return R.drawable.ic_cat_solid;
            case Constants.DOG_ICON:
                return R.drawable.ic_dog_solid;
            case Constants.BALL_ICON:
                return R.drawable.ic_futbol_solid;
            case Constants.BABBY_CARRIAGE_ICON:
                return R.drawable.ic_baby_carriage_solid;
            case Constants.BANDAID_ICON:
                return R.drawable.ic_band_aid_solid;
            case Constants.BED_ICON:
                return R.drawable.ic_bed_solid;
            case Constants.BOOK_ICON:
                return R.drawable.ic_book_solid;
            default:
                return R.drawable.ic_atlas_solid;
        }
    }
}
