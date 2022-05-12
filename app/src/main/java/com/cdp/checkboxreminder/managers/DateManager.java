package com.cdp.checkboxreminder.managers;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdp.checkboxreminder.R;
import com.cdp.checkboxreminder.activity.MainActivity;

import java.util.ArrayList;

public class DateManager {
    MainActivity mainActivity;

    LinearLayout dateList;

    public DateManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        dateList = mainActivity.findViewById(R.id.datelist);
    }



    public boolean exist(int day,int month,int year) {
        for (int i = 0; i < dateList.getChildCount(); i++) {
            View v = dateList.getChildAt(i);
            if (v instanceof LinearLayout) {
                if (v.getId() == (10000*year+100*month+day)) {
                    return true;
                }
            }

        }
        return false;
    }

    public void createDate(int day, int month, int year) {
        View dateView;
        TextView date;

        dateView = mainActivity.getLayoutInflater().inflate(R.layout.date, null, false);
        date = (TextView) dateView.findViewById(R.id.date);
        date.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        dateView.setId(10000*year+100*month+day);
        dateList.addView(dateView);


    }

    public View getCorrectList(int day,int month,int year) {

        for (int i = 0; i < dateList.getChildCount(); i++) {
            View v = dateList.getChildAt(i);
            if (v instanceof LinearLayout) {
                if (v.getId() == (10000*year+100*month+day)) {
                    return v;
                }
            }

        }
        return null;
    }
    public LinearLayout getDateList() {
        return dateList;
    }

}