package com.example.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.ChooseCategoryTypeActivity;
import com.example.quanlychitieu.adapters.CategoryTypeAdapter;
import com.example.quanlychitieu.adapters.ChooseCategoryTypeAdapter;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.presenters.CategoryTypePresenter;
import com.example.quanlychitieu.sampledatas.CategoryData;
import com.example.quanlychitieu.views.CategoryTypeView;

import java.util.List;

public class CategoryTypeFragment extends Fragment implements CategoryTypeView {
    TextView categoryTypeExpense, categoryTypeIncome, categoryTypeAlert;
    RecyclerView categoryTypeList;
    CategoryTypeAdapter adapter;
    CategoryTypePresenter categoryTypePresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setTitle(R.string.category_3);
            activity.getSupportActionBar().setElevation(0);
        }

        categoryTypePresenter = new CategoryTypePresenter(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_category_type, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeElement(view);
        loadExpenseCategoryTypeData();
        handleShowCategoryTypeDataToUI();
    }

    private void loadExpenseCategoryTypeData() {
        categoryTypeAlert.setText(getString(R.string.loading_data));
        categoryTypePresenter.loadExpenseCategories();
    }

    private void loadIncomeCategoryTypeData() {
        categoryTypeAlert.setText(getString(R.string.loading_data));
        categoryTypePresenter.loadIncomeCategories();
    }

    private void handleShowCategoryTypeDataToUI() {
        categoryTypeExpense.setTextColor(getResources().getColor(R.color.primary));
        categoryTypeIncome.setTextColor(getResources().getColor(R.color.black));

        categoryTypeExpense.setOnClickListener(v -> {
            categoryTypeIncome.setTextColor(getResources().getColor(R.color.black));
            categoryTypeExpense.setTextColor(getResources().getColor(R.color.primary));

            loadExpenseCategoryTypeData();
        });

        categoryTypeIncome.setOnClickListener(v -> {
            categoryTypeIncome.setTextColor(getResources().getColor(R.color.primary));
            categoryTypeExpense.setTextColor(getResources().getColor(R.color.black));

            loadIncomeCategoryTypeData();
        });
    }

    private void initializeElement(View view) {
        categoryTypeAlert = view.findViewById(R.id.categoryTypeAlert);
        categoryTypeExpense = view.findViewById(R.id.categoryTypeExpense);
        categoryTypeIncome = view.findViewById(R.id.categoryTypeIncome);
        categoryTypeList = view.findViewById(R.id.categoryTypeList);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_only_title, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showExpenseCategories(List<CategoryType> list) {
        categoryTypeAlert.setText("");

        adapter = new CategoryTypeAdapter(list);
        adapter.setContext(requireActivity());

        categoryTypeList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        categoryTypeList.setAdapter(adapter);
    }

    @Override
    public void showIncomeCategories(List<CategoryType> list) {
        categoryTypeAlert.setText("");

        adapter = new CategoryTypeAdapter(list);
        adapter.setContext(requireActivity());

        categoryTypeList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        categoryTypeList.setAdapter(adapter);
    }

    @Override
    public void showError() {
        categoryTypeAlert.setText(getString(R.string.error_loading_data));
    }
}
