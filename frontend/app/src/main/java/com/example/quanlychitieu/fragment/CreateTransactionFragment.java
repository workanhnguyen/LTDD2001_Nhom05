package com.example.quanlychitieu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.ChooseCategoryTypeActivity;
import com.example.quanlychitieu.spinners.CustomSpinnerExpense;

public class CreateTransactionFragment extends Fragment implements CustomSpinnerExpense.OnSpinnerEventsListener {
    LinearLayout calendarView, linearLayoutCreateTransactionCategoryType;

    TextView txtCalendarDate;

    TextView txtTimerDate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setTitle(R.string.transaction);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_transaction, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        initializeElement(view);
        handleShowCalendar();
        handleSwitchToChooseCategoryTypeActivity();
    }

    private void handleSwitchToChooseCategoryTypeActivity() {
        linearLayoutCreateTransactionCategoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChooseCategoryTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleShowCalendar() {
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });
    }

    private void initializeElement(View view) {
        txtCalendarDate = view.findViewById(R.id.calendarDate);
        txtTimerDate = view.findViewById(R.id.timerDate);
        calendarView = view.findViewById(R.id.calendar);
        linearLayoutCreateTransactionCategoryType = view.findViewById(R.id.linearLayoutCreateTransactionCategoryType);
    }


    @Override
    public void onPopupWindowOpened(Spinner spinner) {

    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {

    }

//    private void navigateToCategoriesFragment() {
//        CategoryTypeFragment categoryFragment = new CategoryTypeFragment();
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frame_layout, categoryFragment)
//                .addToBackStack(null) // Optional, for back stack handling
//                .commit();
//    }

    public void showCalendar() {
        CalendarFragment calendarDialogFragment = new CalendarFragment();
        calendarDialogFragment.show(getChildFragmentManager(), "calendar_dialog");
    }

    public void setDateTime(String selectedDate, String selectedTime) {
        txtCalendarDate.setText(selectedDate);
        txtTimerDate.setText(selectedTime);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
