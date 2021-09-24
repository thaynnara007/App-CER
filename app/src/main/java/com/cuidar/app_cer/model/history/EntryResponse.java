package com.cuidar.app_cer.model.history;

import java.util.HashMap;

public class EntryResponse {

    private String icon;
    private HashMap<String, Integer> activities;

    public EntryResponse(String icon, HashMap<String, Integer> activities) {
        this.icon = icon;
        this.activities = activities;
    }

    public String getIcon() {
        return icon;
    }

    public HashMap<String, Integer> getActivities() {
        return activities;
    }
}
