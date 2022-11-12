package com.example.projectreminder;

import java.util.Vector;
import java.time.*;

public class Todo {
    public String title, description;
    //LocalDate date;
    //LocalTime time;
    private LocalDateTime timing;

    // TODO: find appropriate class for location
    Object location;

    Todo(String title, String description, LocalDateTime timing) {
        this.title = title;
        this.description = description;
        this.timing = timing;
    }



    // get/set -ters
    public void setTitle(String t) { this.title = t; }
    public void setDescription(String d) { this.description = d; }

    public void setTiming(LocalDateTime timing) {
        this.timing = timing;
    }

    public LocalDateTime getTiming() {
        return this.timing;
    }

    public void setDate(LocalDate date) {
        this.timing = LocalDateTime.of(
                date,
                LocalTime.of(timing.getHour(), timing.getMinute(), timing.getSecond())
        );
    }

    public void setDate(int y, int m, int d) {
        this.setDate(LocalDate.of(y, m, d));
    }

    public void setTime(LocalTime time) {
        this.timing = LocalDateTime.of(
                LocalDate.of(timing.getYear(),timing.getMonth(), timing.getDayOfMonth()),
                time
        );
    }

    public void setTime(int h, int m, int s) {
        this.setTime(LocalTime.of(h, m, s));
    }
}
