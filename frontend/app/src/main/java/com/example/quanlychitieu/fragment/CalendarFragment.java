package com.example.quanlychitieu.fragment;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.quanlychitieu.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ganesh on 6/10/2017.
 */

public class CalendarFragment extends DialogFragment {

    private  static final String TAG = "CalendarActivity";
    TimePicker timePicker;
    Button setDateTime;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        View rootView = LayoutInflater.from(requireContext()).inflate(R.layout.calendar_view, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(rootView);
        timePicker = rootView.findViewById(R.id.datePicker);
        setDateTime = rootView.findViewById(R.id.btnSetDateTime);
        timePicker.setIs24HourView(true);
        CalendarView calendarView = rootView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

            }
        });
        setDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected date from the CalendarView
                long selectedDateInMillis = calendarView.getDate();
                Date selectedDate = new Date(selectedDateInMillis);
                int day = selectedDate.getDay();
                int month = selectedDate.getMonth();
                int year = selectedDate.getYear();

                // Get the selected time from the TimePicker
                int selectedHour = timePicker.getHour();
                int selectedMinute = timePicker.getMinute();

                // Format the selected date and time
                String formattedDate = formatDate(day, month, year);
                String formattedTime = setTime(selectedHour, selectedMinute);

                // Pass the selected date and time back to AddExpenseFragment
                onDateTimeSet(formattedDate, formattedTime);
                dismiss();
            }
        });
        return builder.create();
    }

    private String formatDate(int year, int month, int day) {
        // Format the selected date to a desired string format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date(year - 1900, month, day);
        return sdf.format(date);
    }

    private void onDateTimeSet(String selectedDate, String selectedTime) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof CreateTransactionFragment) {
            ((CreateTransactionFragment) parentFragment).setDateTime(selectedDate, selectedTime);
        }
    }

    private String setTime(int hour, int minute){
        String am_pm;
        if (Build.VERSION.SDK_INT >= 23 ){
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }
        else{
            hour = timePicker.getHour();
            minute =timePicker.getMinute();
        }
        if(hour > 12) {
            am_pm = "PM";
            hour = hour - 12;
        }
        else
        {
            am_pm="AM";
        }
        return String.format(Locale.getDefault(), "%02d:%02d %s", hour, minute, am_pm);
    }
}