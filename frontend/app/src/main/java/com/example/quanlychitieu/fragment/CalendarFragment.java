package com.example.quanlychitieu.fragment;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.fragment.CreateTransactionFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ganesh on 6/10/2017.
 */

public class CalendarFragment extends DialogFragment {

    private  static final String TAG = "CalendarActivity";
    TimePicker timePicker;
    Button btnsetDateTime;

    DatePicker datepicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        View rootView = LayoutInflater.from(requireContext()).inflate(R.layout.calendar_view, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(rootView);
        timePicker = rootView.findViewById(R.id.timePicker);
        btnsetDateTime = rootView.findViewById(R.id.btnSetDateTime);
        timePicker.setIs24HourView(true);
        datepicker = rootView.findViewById(R.id.calendarPicker);

        btnsetDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateTime();
            }
        });
        return builder.create();
    }

//    public void onSelectedDateChange(String selectedDate) {
//        Fragment parentFragment = getParentFragment();
//        if (parentFragment instanceof AddExpenseFragment) {
//            ((AddExpenseFragment) parentFragment).setCalendarDate(selectedDate);
//        }
//    }

    private String formatDate(int year, int month, int day) {
        // Format the selected date to a desired string format
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date(year - 1900, month, day);
        return sdf.format(date);
    }

    private void onDateTimeSet(String selectedDateTime) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof CreateTransactionFragment) {
            CreateTransactionFragment addExpenseFragment = (CreateTransactionFragment) parentFragment;
            addExpenseFragment.setDateTime(selectedDateTime);
        }
        dismiss();
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

    private void setDateTime(){
        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth();
        int year = datepicker.getYear();

        // Get the selected time from the TimePicker
        int selectedHour = timePicker.getHour();
        int selectedMinute = timePicker.getMinute();

        // Format the selected date and time
        String formattedDate = formatDate(year, month, day);
        String formattedTime = setTime(selectedHour, selectedMinute);

        String formattedDateTime = formattedDate + " " + formattedTime;

        // Pass the selected date and time back to AddExpenseFragment
        onDateTimeSet(formattedDateTime);

    }
}