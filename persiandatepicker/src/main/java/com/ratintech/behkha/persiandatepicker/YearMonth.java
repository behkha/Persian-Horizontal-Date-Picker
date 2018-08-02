package model;

import java.util.ArrayList;

public class YearMonth {

    private int year;
    private String month;
    private ArrayList<Day> days;

    public YearMonth(int year, String month, ArrayList<Day> days) {
        this.year = year;
        this.month = month;
        this.days = days;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }
}
