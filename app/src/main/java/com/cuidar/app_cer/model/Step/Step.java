package com.cuidar.app_cer.model.Step;

import java.io.Serializable;

public class Step implements Serializable, Comparable {

    private int id;
    private int activityId;
    private int number;
    private String name;
    private String description;
    private String congrats;
    private Image image;

    public Step(int id, int activityId, int number, String name, String description, Image image) {
        this.id = id;
        this.activityId = activityId;
        this.number = number;
        this.name = name;
        this.description = description;
        this.congrats = "";
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getActivityId() {
        return activityId;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCongrats() {
        return congrats;
    }

    public void setCongrats(String congrats){
        this.congrats = congrats;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Step){
            Step otherStep = (Step) o;

            return this.getNumber() - otherStep.getNumber();
        }
        else
            throw new Error("Object is not a Step");
    }
}
