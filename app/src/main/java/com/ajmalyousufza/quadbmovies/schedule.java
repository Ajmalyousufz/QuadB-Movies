package com.ajmalyousufza.quadbmovies;

public class schedule {

    String time;
    String days;

    public schedule(String time, String days) {
        this.time = time;
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
