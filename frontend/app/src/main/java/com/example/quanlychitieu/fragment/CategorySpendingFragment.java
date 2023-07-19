package com.example.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.CategorySpendAdapter;
import com.example.quanlychitieu.sampledatas.ListCategory;

public class CategorySpendingFragment extends Fragment {
    ListView lv, lv2, lv3;
    private CategorySpendAdapter adapter, adapter2, adapter3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.category_list_spend, container, false);
        lv = view.findViewById(R.id.AnUong);
//        lv2 = view.findViewById(R.id.DVSH);
//        lv3 = view.findViewById(R.id.lvEnjoyment);
        adapter = new CategorySpendAdapter(getContext(), ListCategory.getCategoryFoodList());
//        adapter2 = new CategorySpendAdapter(getContext(), ListCategory.getCategoryLivingList());
//        adapter3 = new CategorySpendAdapter(getContext(), ListCategory.getCategoryEnjoymentList());
        lv.setAdapter(adapter);
//        lv2.setAdapter(adapter2);
//        lv3.setAdapter(adapter3);
        return view;
    }


}
