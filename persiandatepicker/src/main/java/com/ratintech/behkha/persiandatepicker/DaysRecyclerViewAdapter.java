package com.ratintech.behkha.persiandatepicker;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class DaysRecyclerViewAdapter extends RecyclerView.Adapter<DaysRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private YearMonth yearMonth;
    private ArrayList<Day> mDays;
    private PersianDatePicker.OnDaySelectListener mOnDaySelectListener;
    private int selectedPosition = -1;
    private int selectedItemBackgroundColor;
    private int selectedItemTextColor;
    private int defaultItemBackgroundColor;
    private int defaultItemTextColor;
    private Typeface typeface;
    private float elevation;
    private float radius;
    private boolean hasAnimation;

    public DaysRecyclerViewAdapter(Context mContext, ArrayList<Day> mDays, PersianDatePicker.OnDaySelectListener onDaySelectListener) {
        this.mContext = mContext;
        this.mDays = mDays;
        this.mOnDaySelectListener = onDaySelectListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mDay;
        TextView mWeekDay;
        CardView mRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mDay = itemView.findViewById(R.id.day);
            mWeekDay = itemView.findViewById(R.id.week_day);
            mRoot = itemView.findViewById(R.id.root);

            mRoot.setCardElevation(elevation);
            mRoot.setRadius(radius);

            if (typeface != null){
                mDay.setTypeface( typeface );
                mWeekDay.setTypeface(typeface);
            }
        }

        @Override
        public void onClick(View v) {
            int prevSelectPosition = selectedPosition;
            selectedPosition = getAdapterPosition();
            Day day = mDays.get( selectedPosition );
            if ( hasAnimation ) {
                notifyItemChanged(selectedPosition);
                notifyItemChanged(prevSelectPosition);
            } else
                notifyDataSetChanged();
            if (mOnDaySelectListener != null) {
                mOnDaySelectListener.onDaySelect( yearMonth , day );
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.day_item_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Day day = mDays.get(position);
        holder.mDay.setText(String.valueOf(day.getNum()));
        holder.mWeekDay.setText(day.getDay());

        if ( position == selectedPosition ){
            holder.mDay.setTextColor( mContext.getResources().getColor( this.selectedItemTextColor ) );
            holder.mWeekDay.setTextColor( mContext.getResources().getColor( this.selectedItemTextColor ) );
            holder.mRoot.setCardBackgroundColor( mContext.getResources().getColor( this.selectedItemBackgroundColor ) );
        }  else {
            holder.mRoot.setCardBackgroundColor( mContext.getResources().getColor( this.defaultItemBackgroundColor ) );
            holder.mDay.setTextColor( mContext.getResources().getColor( this.defaultItemTextColor ) );
            holder.mWeekDay.setTextColor( mContext.getResources().getColor( this.defaultItemTextColor ) );
        }
    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }

    public void setYearMonth(YearMonth yearMonth){
        this.yearMonth = yearMonth;
    }
    public void setSelectedItemBackgroundColor(int selectedItemBackgroundColor) {
        this.selectedItemBackgroundColor = selectedItemBackgroundColor;
    }
    public void setSelectedItemTextColor(int selectedItemTextColor) {
        this.selectedItemTextColor = selectedItemTextColor;
    }
    public void setTypeface(Typeface typeface){
        this.typeface = typeface;
    }
    public void setDefaultItemBackgroundColor(int defaultItemBackgroundColor) {
        this.defaultItemBackgroundColor = defaultItemBackgroundColor;
    }
    public void setDefaultItemTextColor(int defaultItemTextColor) {
        this.defaultItemTextColor = defaultItemTextColor;
    }
    public void setElevation(float elevation){
        this.elevation = elevation;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
    public void setAnimation(boolean animation){
        this.hasAnimation = animation;
    }
}
