package com.example.app_cer.user_preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class ActivityData {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "cuidar.preferences";

    public ActivityData(Context context) {
        this.context = context;
        this.preferences =  context.getSharedPreferences(FILE_NAME, 0);
        this.editor = preferences.edit();
    }

    public void postStatus(String activityName, int status) {
        this.editor.putInt(activityName, status);
        this.editor.commit();
    }

    public int getStatus(String activityName) {
        return this.preferences.getInt(activityName, 0);
    }

    public boolean hasActivity(String actvityName) {
        int status = this.getStatus(actvityName);

        if (status == 0) return false;

        return true;
    }
}
