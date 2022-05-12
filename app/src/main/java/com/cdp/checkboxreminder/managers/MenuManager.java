package com.cdp.checkboxreminder.managers;

import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cdp.checkboxreminder.R;
import com.cdp.checkboxreminder.activity.MainActivity;

import java.util.Date;

public  class MenuManager {
    static View menu;
    MainActivity mainActivity;

     LinearLayout menuLayout;
     TextView sign;
     EditText nameEdit;
     EditText descriptionEdit;
     CalendarView calendarView;
     ScrollView scrollView;

    int year;
    int month;
    int day;

    public MenuManager(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        menu = mainActivity.findViewById(R.id.addMenu);

        sign = (TextView) menu.findViewById(R.id.addsign);
        menuLayout = (LinearLayout) menu.findViewById(R.id.linearLayoutMenu);
        nameEdit = (EditText) menu.findViewById(R.id.nameedit);
        descriptionEdit = (EditText) menu.findViewById(R.id.descriptionedit);
        calendarView = (CalendarView) menu.findViewById(R.id.calendaredit);
        scrollView   = (ScrollView) menu.findViewById(R.id.editscroll);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            public void onSelectedDayChange(CalendarView view, int selectedYear, int selectedMonth, int selectedDay) {
                day   = selectedDay;
                year  = selectedYear;
                month = selectedMonth;
            }
        });
     }
     public static View getMenu(){
        return menu;
     }
    public void expand(){
        if (menuLayout.getVisibility() == View.GONE)
        {
            menuLayout.setVisibility(View.VISIBLE);
            sign.setVisibility(View.GONE);
        }
        else {
            menuLayout.setVisibility(View.GONE);
            sign.setVisibility(View.VISIBLE);
        }
        nameEdit.getText().clear();
        descriptionEdit.getText().clear();
        scrollView.scrollTo(0,0);

    }
    public EditText getNameEdit() {
        return nameEdit;
    }

    public EditText getDescriptionEdit() {
        return descriptionEdit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
