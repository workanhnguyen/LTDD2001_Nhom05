package com.example.quanlychitieu.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlychitieu.R;

import java.util.Calendar;

public class MonthYearPickerActivity extends DialogFragment {

    private OnDateSetListener onDateSetListener;
    private int selectedMonth;
    private int selectedYear;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = (LayoutInflater) requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.custom_month_year_picker, null);
        builder.setView(dialogView);

        final NumberPicker monthPicker = dialogView.findViewById(R.id.monthPicker);
        final NumberPicker yearPicker = dialogView.findViewById(R.id.yearPicker);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(currentMonth + 1);

        yearPicker.setMinValue(1900);
        yearPicker.setMaxValue(currentYear + 100);
        yearPicker.setValue(currentYear);

        builder.setPositiveButton("OK", (dialog, which) -> {
            selectedMonth = monthPicker.getValue() - 1; // Subtract 1 to get 0-based month value
            selectedYear = yearPicker.getValue();
            if (onDateSetListener != null) {
                onDateSetListener.onDateSet(selectedYear, selectedMonth);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });

        return builder.create();
    }

    public void setOnDateSetListener(OnDateSetListener listener) {
        this.onDateSetListener = listener;
    }

    public interface OnDateSetListener {
        void onDateSet(int year, int month);
    }
}