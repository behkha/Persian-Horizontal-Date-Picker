package com.ratintech.behkha.persiandatepicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class PersianDatePicker extends LinearLayout implements View.OnClickListener  {

    private ImageView leftArrow , rightArrow;
    private TextView mYearMonthText;
    private RecyclerView mDaysRecyclerView;
    private ArrayList<YearMonth> mYearMonths;
    private OnDaySelectListener mOnDaySelectListener;
    private Typeface typeface;
    private float elevation = 0f;
    private float radius = 0f;
    private int selectedItemBackgroundColor = R.color.colorPrimary;
    private int selectedItemBackgroundDrawable;
    private int selectedItemTextColor = R.color.colorAccent;
    private int defaultItemBackgroundColor = R.color.colorPrimary;
    private int defaultItemTextColor = R.color.colorAccent;
    private int mYearMonthIndex = 0;
    private boolean hasAnimation = false;

    public PersianDatePicker(Context context) {
        this(context,null);
    }
    public PersianDatePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public PersianDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PersianDatePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }
    private void init(Context context , AttributeSet attributeSet ){
        inflate(getContext() , R.layout.persian_date_picker_view, this);

        this.leftArrow = findViewById(R.id.left_arrow);
        this.rightArrow = findViewById(R.id.right_arrow);
        this.mYearMonthText = findViewById(R.id.year_month_text);
        this.mDaysRecyclerView = findViewById(R.id.days_recycler_view);

        leftArrow.setOnClickListener(this);
        rightArrow.setOnClickListener(this);
    }

    public PersianDatePicker setYearMonths(ArrayList<YearMonth> yearMonths){
        this.mYearMonths = yearMonths;
        return this;
    }
    public PersianDatePicker setTypeFace(Typeface typeface){
        this.mYearMonthText.setTypeface(typeface);
        this.typeface = typeface;
        return this;
    }
    public PersianDatePicker setTitleTextColor(@ColorRes int color){
        this.mYearMonthText.setTextColor(getResources().getColor(color));
        return this;
    }
    public PersianDatePicker setTitleTextSize(int size){
        this.mYearMonthText.setTextSize( size );
        return this;
    }
    public PersianDatePicker setArrowDrawable(@DrawableRes int drawable){
        this.leftArrow.setImageDrawable(getResources().getDrawable(drawable));
        this.rightArrow.setImageDrawable(getResources().getDrawable(drawable));
        return this;
    }
    public PersianDatePicker setSelectedItemBackgroundColor(@ColorRes int color){
        this.selectedItemBackgroundColor = color;
        return this;
    }
    public PersianDatePicker setSelectedItemBackgroundDrawable(@DrawableRes int drawable){
        this.selectedItemBackgroundDrawable = drawable;
        return this;
    }
    public PersianDatePicker setSelectedItemTextColor(@ColorRes int color){
        this.selectedItemTextColor = color;
        return this;
    }
    public PersianDatePicker setDefaultItemBackgroundColor(@ColorRes int color){
        this.defaultItemBackgroundColor = color;
        return this;
    }
    public PersianDatePicker setDefaultItemTextColor(@ColorRes int color){
        this.defaultItemTextColor = color;
        return this;
    }
    public PersianDatePicker setListener(OnDaySelectListener onDaySelectListener){
        mOnDaySelectListener = onDaySelectListener;
        return this;
    }
    public PersianDatePicker setItemElevation(float elevation){
        this.elevation = elevation;
        return this;
    }
    public PersianDatePicker setItemRadius(float radius){
        this.radius = radius;
        return this;
    }
    public PersianDatePicker hasAnimation(boolean hasAnimation){
        this.hasAnimation = hasAnimation;
        return this;
    }

    public void load(){
        if (this.mYearMonths.size() == 0)
            return;
        setupView(mYearMonthIndex);
    }
    private void setupView(int index){

        YearMonth yearMonth = this.mYearMonths.get(index);
        mYearMonthText.setText( getTitle( yearMonth ) );
        DaysRecyclerViewAdapter adapter = new DaysRecyclerViewAdapter( getContext() , yearMonth.getDays() , this.mOnDaySelectListener);
        adapter.setSelectedItemBackgroundColor( this.selectedItemBackgroundColor );
        adapter.setSelectedItemBackgroundDrawable( this.selectedItemBackgroundDrawable );
        adapter.setSelectedItemTextColor( this.selectedItemTextColor );
        adapter.setDefaultItemBackgroundColor( this.defaultItemBackgroundColor );
        adapter.setDefaultItemTextColor( this.defaultItemTextColor );
        adapter.setTypeface( this.typeface );
        adapter.setYearMonth(yearMonth);
        adapter.setElevation(this.elevation);
        adapter.setRadius(this.radius);
        adapter.setAnimation(this.hasAnimation);
        this.mDaysRecyclerView.setAdapter( adapter );
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , true);
        this.mDaysRecyclerView.setLayoutManager(linearLayoutManager);
        this.mDaysRecyclerView.scrollToPosition(0);

//        this.mDaysRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int visibleItemCount = linearLayoutManager.getChildCount();
//                int totalItemCount = linearLayoutManager.getItemCount();
//                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();
//
//                if ( visibleItemCount + pastVisibleItems + 1 >= totalItemCount ){
//                    gotoNextMonth();
//                }
//            }
//        });

    }

    private String getTitle(YearMonth yearMonth){
        return yearMonth.getYear() + " " + yearMonth.getMonth();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.left_arrow) {
            gotoNextMonth();

        } else if (i == R.id.right_arrow) {
            gotoPreviousMonth();

        }
    }
    private void gotoNextMonth(){
        if (mYearMonthIndex == mYearMonths.size() - 1)
            return;
        setupView(++mYearMonthIndex);
    }
    private void gotoPreviousMonth(){
        if (mYearMonthIndex == 0)
            return;
        setupView(--mYearMonthIndex);
    }
    public interface OnDaySelectListener {
        void onDaySelect(YearMonth yearMonth , Day day);
    }


}
