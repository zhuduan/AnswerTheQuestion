package com.team.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.model.Schedule;

import java.util.Arrays;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ScheduleConfig {

    PEEK_MEETING(1, "冲顶大会", "该游戏一天有3次", Arrays.asList(
            new Schedule("10:00:00", "10:30:00"),
            new Schedule("13:00:00", "13:30:00"),
            new Schedule("18:00:00", "18:30:00"))),
    MILLION_HERO(2, "百万英雄", "该游戏处于调试处理阶段,暂时全天都生效", Arrays.asList(
            new Schedule("9:00:00", "23:30:00")));

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("schedule")
    private List<Schedule> schedule;

    ScheduleConfig(int id, String name, String description, List<Schedule> schedule) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
    }
}
