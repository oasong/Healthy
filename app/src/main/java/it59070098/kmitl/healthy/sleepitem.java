package it59070098.kmitl.healthy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class sleepitem implements Serializable {

    private int id;
    private String dateString;
    private String startTime;
    private String endTime;
    private String duration;

    public sleepitem(String dateString, String startTime, String endTime, String duration) {
        this.dateString = dateString;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public sleepitem(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
