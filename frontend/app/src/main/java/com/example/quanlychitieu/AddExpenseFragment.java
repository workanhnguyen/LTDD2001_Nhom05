package com.example.quanlychitieu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

//import com.applandeo.materialcalendarview.EventDay;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//public class AddExpenseFragment extends Fragment implements com.example.quanlychitieu.CustomSpinnerExpense.OnSpinnerEventsListener {
//    ImageView img, calendarView;
//
//
//    TextView txtCalendarDateTime;
//
//
//
//    private com.example.quanlychitieu.CustomSpinnerExpense spinner_expense;
//
//
//
//    private com.example.quanlychitieu.ExpenseAdapter adapter;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                         Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
//
//        img = view.findViewById(R.id.arrowRight);
//
//        txtCalendarDateTime = view.findViewById(R.id.calendarDateTime);
//        calendarView = view.findViewById(R.id.calendar);
//        spinner_expense = view.findViewById(R.id.spinner_expense);
//        String currentDateTime = java.text.DateFormat.getDateTimeInstance().format(new Date());
//        txtCalendarDateTime.setText(currentDateTime);
//
//        spinner_expense.setSpinnerEventsListener(this);
//        adapter = new ExpenseAdapter(getContext(), ListExpense.getExpenseList());
//        spinner_expense.setAdapter(adapter);
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigateToCategoriesFragment();
//            }
//        });
//        calendarView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showCalendar();
//            }
//        });
//        return view;
//    }
//
//
//    @Override
//    public void onPopupWindowOpened(Spinner spinner) {
//
//    }
//
//    @Override
//    public void onPopupWindowClosed(Spinner spinner) {
//
//    }
//
//    private void navigateToCategoriesFragment() {
//        CategoriesFragment categoryFragment = new CategoriesFragment();
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frame_layout, categoryFragment)
//                .addToBackStack(null) // Optional, for back stack handling
//                .commit();
//    }
//
//    public void showCalendar() {
//        CalendarFragment calendarDialogFragment = new CalendarFragment();
//        calendarDialogFragment.show(getChildFragmentManager(), "calendar_dialog");
//    }
//
//    public void setDateTime(String selectedDateTime) {
//        txtCalendarDateTime.setText(selectedDateTime);
//    }
//}
