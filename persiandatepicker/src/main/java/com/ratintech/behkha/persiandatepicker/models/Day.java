package com.ratintech.behkha.persiandatepicker.models;

public class Day {

    private int number;
    private String day;

    public Day(int number, String day) {
        this.number = number;
        this.day = day;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNumberString(){
        if (this.number < 10)
            return "0" + this.number;
        else
            return String.valueOf(this.number);
    }

}
