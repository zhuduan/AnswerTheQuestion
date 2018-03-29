package com.team.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Schedule {

    @JsonProperty("startTime")
    private String startTime;   // hh-MM-ss (24hour)

    @JsonProperty("endTime")
    private String endTime;     // hh-MM-ss (24hour)

    public Schedule(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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
}
