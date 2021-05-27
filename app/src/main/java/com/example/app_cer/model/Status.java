package com.example.app_cer.model;

public class Status {

    private String status1;
    private String status2;
    private String status3;
    private String name;
    private int amountStatus3;
    private int amountStatus2;
    private int amountStatus1;
    private int image;

    public Status() {

    }

    public Status(String name, String status1, String status2, String status3, int amountStatus3, int amountStatus2, int amountStatus1, int image) {

        this.name = name;
        this.status1 = status1;
        this.status2 = status2;
        this.status3 = status3;
        this.amountStatus3 = amountStatus3;
        this.amountStatus2 = amountStatus2;
        this.amountStatus1 = amountStatus1;
        this.image = image;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getStatus3() {
        return status3;
    }

    public void setStatus3(String status3) {
        this.status3 = status3;
    }

    public int getAmountStatus3() {
        return amountStatus3;
    }

    public void setAmountStatus3(int amountStatus3) {
        this.amountStatus3 = amountStatus3;
    }

    public int getAmountStatus2() {
        return amountStatus2;
    }

    public void setAmountStatus2(int amountStatus2) {
        this.amountStatus2 = amountStatus2;
    }

    public int getAmountStatus1() {
        return amountStatus1;
    }

    public void setAmountStatus1(int amountStatus1) {
        this.amountStatus1 = amountStatus1;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
