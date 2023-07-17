package com.example.quanlychitieu;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * Created by ganesh on 6/10/2017.
 */

public class CalendarActivity extends Fragment {

    private  static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_view, container, false);
        mCalendarView = view.findViewById(R.id.calendarView);
        set
        return view;
    }
}
