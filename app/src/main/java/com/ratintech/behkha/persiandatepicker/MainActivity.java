package com.ratintech.behkha.persiandatepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PersianDatePicker persianDatePicker = findViewById(R.id.persian_date_picker);
        persianDatePicker.setYearMonths( new Calendar( new PersianCalendar().getPersianLongDate()).getYearMonths() )
                .setDefaultItemBackgroundColor(R.color.defaultBackgroundColor)  // background color of non-selected item
                .setDefaultItemTextColor(R.color.defaultTextColor)  // text color of non-selected item
                .setSelectedItemBackgroundColor(R.color.colorPrimary) // background color of selected item
                .setSelectedItemTextColor(R.color.selectText)   // text color of selected item
                .setListener(new PersianDatePicker.OnDaySelectListener() {
                    @Override
                    public void onDaySelect(YearMonth yearMonth, Day day) {
                        // right your code here when item is selected
                    }
                })
                .setItemElevation( 4f )  // default is 0
                .setItemRadius( 2f )  // default is 0
                .hasAnimation(true) // Animation for item selection . default is false
                .load();

        setContentView(persianDatePicker);
    }



}
