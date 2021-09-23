package com.cuidar.app_cer.user_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    public void postToken(String token){
        this.editor.putString("cuidarToken", token);
        this.editor.commit();
    }

    public String getToken() {
        return this.preferences.getString("cuidarToken", "");
    }

    public void deleteToken() {
        this.editor.remove("cuidarToken");
        Log.d("o", "deleteToken: ");
        this.editor.commit();
    }

    public void postUserName(String name){
        this.editor.putString("cuidarUserName", name);
        this.editor.commit();
    }

    public String getUserName() {
        return this.preferences.getString("cuidarUserName", "");
    }

    public void deleteUserName() {
        this.editor.remove("cuidarUserName");
        this.editor.commit();
    }

    public void postUserId(int id){
        this.editor.putInt("cuidarUserId", id);
        this.editor.commit();
    }

    public int getUserId() {
        return this.preferences.getInt("cuidarUserId", 0);
    }
}
