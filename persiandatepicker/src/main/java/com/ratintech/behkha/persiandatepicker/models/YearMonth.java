package com.ratintech.behkha.persiandatepicker.models;

import java.util.ArrayList;

public class YearMonth {

    private String[] persianMonthNames = new String[]{"فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
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

    public String getMonthNumber(){
        int index = -1;
        for (int i = 0; i < persianMonthNames.length; i++) {
            if (persianMonthNames[i].equals(this.month))
                index = i;
        }
        index++;
        if ( 0 < index && index < 10)
            return "0" + index;
        else
            return String.valueOf(index);
    }
}
