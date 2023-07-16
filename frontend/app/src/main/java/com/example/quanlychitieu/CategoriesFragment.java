package com.example.quanlychitieu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class CategoriesFragment extends Fragment {
    TextView mucThu, mucChi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        mucChi = view.findViewById(R.id.txtMucChi);
        mucThu = view.findViewById(R.id.txtMucThu);
        navigateToCategorySpendingFragment();
        mucChi.setTextColor(getResources().getColor(R.color.lavender));
        mucThu.setTextColor(getResources().getColor(R.color.dark_grey));
        View.OnClickListener textViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the default text color for both TextViews
                mucChi.setTextColor(getResources().getColor(R.color.dark_grey));
                mucThu.setTextColor(getResources().getColor(R.color.dark_grey));

                // Set the clicked TextView's text color to the selected color
                TextView clickedTextView = (TextView) v;
                clickedTextView.setTextColor(getResources().getColor(R.color.lavender));

                // Handle navigation based on which TextView is clicked
                if (v.getId() == R.id.txtMucChi) {
                    navigateToCategorySpendingFragment();
                } else if (v.getId() == R.id.txtMucThu) {
                    navigateToCategoryReceivingFragment();
                }
            }
        };
        mucChi.setOnClickListener(textViewClickListener);
        mucThu.setOnClickListener(textViewClickListener);
        return view;
    }

    private void navigateToCategorySpendingFragment() {
        CategorySpendingFragment categorySpendingFragment = new  CategorySpendingFragment ();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view, categorySpendingFragment)
                .addToBackStack(null) // Optional, for back stack handling
                .commit();
    }
    private void navigateToCategoryReceivingFragment() {
        CategoryReceivingFragment categoryReceivingFragment = new  CategoryReceivingFragment ();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view, categoryReceivingFragment)
                .addToBackStack(null) // Optional, for back stack handling
                .commit();
    }
}
