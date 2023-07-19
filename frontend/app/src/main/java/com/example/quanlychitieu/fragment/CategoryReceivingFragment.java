package com.example.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.CategoryReceiveAdapter;
import com.example.quanlychitieu.sampledatas.ListCategory;

public class CategoryReceivingFragment extends Fragment {
    RecyclerView lv;
    private CategoryReceiveAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.category_list_receive, container, false);
//        lv = view.findViewById(R.id.listViewReceiving);
//        adapter = new CategoryReceiveAdapter(getContext(), ListCategory.getCategoryReceivingList());
//        lv.setAdapter(adapter);
        return view;
    }
}
