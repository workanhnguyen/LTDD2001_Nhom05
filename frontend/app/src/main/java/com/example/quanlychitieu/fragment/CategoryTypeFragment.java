package com.example.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.dtos.adapters.CategoryTypeAdapter;
import com.example.quanlychitieu.models.CategoryType;
import com.example.quanlychitieu.sampledatas.CategoryData;

import java.util.List;

public class CategoryTypeFragment extends Fragment {
    TextView categoryTypeExpense, categoryTypeIncome;
    RecyclerView categoryTypeList;
    CategoryTypeAdapter adapter;

    List<CategoryType> expenseCategories = CategoryData.getExpenseCategoryTypeList();
    List<CategoryType> incomeCategories = CategoryData.getIncomeCategoryTypeList();
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
        loadCategoryTypeData(expenseCategories);
        handleShowCategoryTypeDataToUI();
    }

    private void loadCategoryTypeData(List<CategoryType> list) {
        adapter = new CategoryTypeAdapter(list);
        adapter.setContext(getActivity());
        categoryTypeList.setAdapter(adapter);
        categoryTypeList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void handleShowCategoryTypeDataToUI() {
        categoryTypeExpense.setTextColor(getResources().getColor(R.color.primary));
        categoryTypeIncome.setTextColor(getResources().getColor(R.color.black));

        categoryTypeExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryTypeExpense.setTextColor(getResources().getColor(R.color.primary));
                categoryTypeIncome.setTextColor(getResources().getColor(R.color.black));

                loadCategoryTypeData(expenseCategories);
            }
        });

        categoryTypeIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryTypeExpense.setTextColor(getResources().getColor(R.color.black));
                categoryTypeIncome.setTextColor(getResources().getColor(R.color.primary));

                loadCategoryTypeData(incomeCategories);
            }
        });
    }

    private void initializeElement(View view) {
        categoryTypeExpense = view.findViewById(R.id.categoryTypeExpense);
        categoryTypeIncome = view.findViewById(R.id.categoryTypeIncome);
        categoryTypeList = view.findViewById(R.id.categoryTypeList);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.btnSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
