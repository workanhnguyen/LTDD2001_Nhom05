package com.example.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.ExpenseAdapter;
import com.example.quanlychitieu.models.ListExpense;
import com.example.quanlychitieu.spinners.CustomSpinnerExpense;

public class AddExpenseFragment extends Fragment implements CustomSpinnerExpense.OnSpinnerEventsListener {
    ImageView img;
    private CustomSpinnerExpense spinner_expense;

    private ExpenseAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        img = view.findViewById(R.id.arrowRight);
        spinner_expense = view.findViewById(R.id.spinner_expense);
        spinner_expense.setSpinnerEventsListener(this);
        adapter = new ExpenseAdapter(getContext(), ListExpense.getExpenseList());
        spinner_expense.setAdapter(adapter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCategoriesFragment();
            }
        });

        return view;
    }


    @Override
    public void onPopupWindowOpened(Spinner spinner) {

    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {

    }

    private void navigateToCategoriesFragment() {
        CategoryFragment categoryFragment = new CategoryFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, categoryFragment)
                .addToBackStack(null) // Optional, for back stack handling
                .commit();
    }
}
