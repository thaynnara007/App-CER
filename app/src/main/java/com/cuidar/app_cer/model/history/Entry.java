package com.cuidar.app_cer.model.history;

import com.cuidar.app_cer.model.activity.Activity;

public class Entry {

    private int id;
    private int activityId;
    private int patientId;
    private String endTime;
    private Activity activity;


    public Entry(int id, int activityId, int patientId, String endTime) {
        this.id = id;
        this.activityId = activityId;
        this.patientId = patientId;
        this.endTime = endTime;
    }

    public Entry(int id, int activityId, int patientId, String endTime, Activity activity) {
        this.id = id;
        this.activityId = activityId;
        this.patientId = patientId;
        this.endTime = endTime;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public int getActivityId() {
        return activityId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getEndTime() {
        return endTime;
    }

    public Activity getActivity() {
        return activity;
    }
}
