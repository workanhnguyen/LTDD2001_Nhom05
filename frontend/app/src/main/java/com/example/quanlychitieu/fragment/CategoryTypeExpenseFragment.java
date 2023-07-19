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
import com.example.quanlychitieu.models.CategoryRoot;
import com.example.quanlychitieu.models.CategoryType;

import java.util.ArrayList;
import java.util.List;

public class CategoryTypeExpenseFragment extends Fragment {
    RecyclerView categoryTypeExpenseList;
    CategoryTypeExpenseAdapter adapter;
    List<CategoryType> categoryTypes = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_category_type_expense, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeElement(view);
        loadCategoryRootData();
        handleShowCategoryTypeExpenseToUI();
    }

    private void handleShowCategoryTypeExpenseToUI() {
        adapter = new CategoryTypeExpenseAdapter(categoryTypes);
        adapter.setContext(getContext());
        categoryTypeExpenseList.setAdapter(adapter);
        categoryTypeExpenseList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void loadCategoryRootData() {
        categoryTypes.add(new CategoryType(1, "Ăn sáng", ""));
        categoryTypes.add(new CategoryType(2, "Ăn trưa", ""));
        categoryTypes.add(new CategoryType(3, "Ăn tối", ""));
        categoryTypes.add(new CategoryType(4, "Ăn tiệm", ""));
    }

    private void initializeElement(View view) {
        categoryTypeExpenseList = view.findViewById(R.id.categoryTypeExpenseList);
    }
}
