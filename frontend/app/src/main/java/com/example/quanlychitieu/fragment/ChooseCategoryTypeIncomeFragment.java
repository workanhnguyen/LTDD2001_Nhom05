package com.example.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.CategoryTypeExpenseAdapter;
import com.example.quanlychitieu.adapters.CategoryTypeIncomeAdapter;
import com.example.quanlychitieu.adapters.ChooseCategoryTypeIncomeAdapter;
import com.example.quanlychitieu.models.CategoryRoot;
import com.example.quanlychitieu.models.CategoryType;

import java.util.ArrayList;
import java.util.List;

public class ChooseCategoryTypeIncomeFragment extends Fragment {
    RecyclerView chooseCategoryTypeIncomeList;
    ChooseCategoryTypeIncomeAdapter adapter;
    List<CategoryType> categoryTypes = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_choose_category_type_income, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeElement(view);
        loadCategoryRootData();
        handleShowCategoryTypeIncomeToUI();
    }

    private void handleShowCategoryTypeIncomeToUI() {
        adapter = new ChooseCategoryTypeIncomeAdapter(categoryTypes);
        adapter.setContext(getContext());
        chooseCategoryTypeIncomeList.setAdapter(adapter);
        chooseCategoryTypeIncomeList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void loadCategoryRootData() {
        categoryTypes.add(new CategoryType(5, "Lương", ""));
        categoryTypes.add(new CategoryType(6, "Thưởng", ""));
        categoryTypes.add(new CategoryType(7, "Lãi tiết kiệm", ""));
        categoryTypes.add(new CategoryType(8, "Được cho, tặng", ""));
    }

    private void initializeElement(View view) {
        chooseCategoryTypeIncomeList = view.findViewById(R.id.chooseCategoryTypeIncomeList);
    }
}
