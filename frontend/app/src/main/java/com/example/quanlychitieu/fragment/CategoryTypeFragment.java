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
import androidx.fragment.app.FragmentManager;

import com.example.quanlychitieu.R;

public class CategoryTypeFragment extends Fragment {
    TextView mucThu, mucChi;
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
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeElement(view);
        handleChangeCategoryTypeFragment();
    }

    private void handleChangeCategoryTypeFragment() {
        navigateToCategoryExpenseFragment();
        mucChi.setTextColor(getResources().getColor(R.color.primary));
        mucThu.setTextColor(getResources().getColor(R.color.black));
        View.OnClickListener textViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the default text color for both TextViews
                mucChi.setTextColor(getResources().getColor(R.color.black));
                mucThu.setTextColor(getResources().getColor(R.color.black));

                // Set the clicked TextView's text color to the selected color
                TextView clickedTextView = (TextView) v;
                clickedTextView.setTextColor(getResources().getColor(R.color.primary));

                // Handle navigation based on which TextView is clicked
                if (v.getId() == R.id.txtMucChi) {
                    navigateToCategoryExpenseFragment();
                } else if (v.getId() == R.id.txtMucThu) {
                    navigateToCategoryIncomeFragment();
                }
            }
        };
        mucChi.setOnClickListener(textViewClickListener);
        mucThu.setOnClickListener(textViewClickListener);
    }

    private void initializeElement(View view) {
        mucChi = view.findViewById(R.id.txtMucChi);
        mucThu = view.findViewById(R.id.txtMucThu);
    }

    private void navigateToCategoryExpenseFragment() {
        CategoryTypeExpenseFragment categoryExpenseFragment = new CategoryTypeExpenseFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view, categoryExpenseFragment)
                .addToBackStack(null) // Optional, for back stack handling
                .commit();
    }
    private void navigateToCategoryIncomeFragment() {
        CategoryTypeIncomeFragment categoryIncomeFragment = new CategoryTypeIncomeFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view, categoryIncomeFragment)
                .addToBackStack(null) // Optional, for back stack handling
                .commit();
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
