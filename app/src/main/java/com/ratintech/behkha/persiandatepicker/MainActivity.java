package com.ratintech.behkha.persiandatepicker;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import model.Calendar;
import model.Day;
import model.YearMonth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersianDatePicker datePicker = findViewById(R.id.persian_date_picker);
        datePicker.setYearMonths(new Calendar(new PersianCalendar().getPersianLongDate()).getYearMonths())
                .setTitleTextColor( R.color.colorPrimaryDark )
                .setTypeFace( get_iran_sans_fa_num_font(getApplicationContext()) )
                .setDefaultItemBackgroundColor( R.color.defaultBackgroundColor )
                .setDefaultItemTextColor( R.color.defaultTextColor )
                .setSelectedItemTextColor( R.color.selectText )
                .setSelectedItemBackgroundColor( R.color.colorPrimary )
                .setTitleTextSize( 16 )
                .setItemRadius( 4 )
                .setListener(new PersianDatePicker.OnDaySelectListener() {
                    @Override
                    public void onDaySelect(YearMonth yearMonth, Day day) {
                        Toast.makeText(getApplicationContext() , day.getDay() + " " + day.getNum() + " " + yearMonth.getMonth() + " " + yearMonth.getYear()  , Toast.LENGTH_SHORT).show();
                    }
                }).load();

    }

    public static Typeface get_iran_sans_fa_num_font(Context context)
    {
        return Typeface.createFromAsset( context.getAssets() , "fonts/IRANSansMobile(FaNum).ttf" );
    }

}
