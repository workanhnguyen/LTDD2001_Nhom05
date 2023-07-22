package com.example.quanlychitieu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlychitieu.MainActivity;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.utils.CustomConstant;

public class DayStatisticFragment extends Fragment {
    TextView filterYesterday, filterToday, filterOtherDay;
    Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day_statistic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeElement(view);
//        handleChooseStatisticFilter();
    }

//    private void handleChooseStatisticFilter() {
//        filterYesterday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra("statistic_filter", CustomConstant.FILTER_STATISTIC_YESTERDAY);
//                startActivity(intent);
////                requireActivity().onBackPressed();
//                requireActivity().finish();
//            }
//        });
//    }

    private void initializeElement(View view) {
        filterYesterday = view.findViewById(R.id.filterDayYesterday);
        filterToday = view.findViewById(R.id.filterDayToday);
        filterOtherDay = view.findViewById(R.id.filterDayOtherDay);
    }
}