package com.cuidar.app_cer.model.history;

public class Entry {

    private int id;
    private int activityId;
    private int patientId;
    private String endTime;

    public Entry(int id, int activityId, int patientId, String endTime) {
        this.id = id;
        this.activityId = activityId;
        this.patientId = patientId;
        this.endTime = endTime;
    }

}
