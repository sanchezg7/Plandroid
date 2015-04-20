package com.example.plandroid.plandroid;

/**
 * Created by GerardoSLnv on 4/17/2015.
 */
public class Event {
    public Event() {

    }
    public Event(String n) {
        setName(n);
    }
    public Event(String n, String c, int m, int d, int y, int h, int min, String t) {
        setName(n);
        setLocation(c);
        setDate(m,d,y);
        setTime(h,min,t);
    }
    public void setName(String n) {
        name = n;
    }
    public void setLocation(String c) {
        location = c;
    }
    public void setDate(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }
    public void setTime(int h, int m, String t) {
        hour = h;
        minute = m;
        time = t;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public int getYear() {
        return year;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public String getTime() {
        return time;
    }

    private String name; //name of event
    private String location; //class, social, or work
    private int month;
    private int day;
    private int year;
    private int hour;
    private int minute;
    private String time; //for AM or PM
}
