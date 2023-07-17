package com.example.quanlychitieu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.models.Category;

import java.util.List;

public class CategorySpendAdapter extends BaseAdapter {
    private List<Category> list;
    private Context context;

    public CategorySpendAdapter(Context cont, List<Category> list){
        this.setContext(cont);
        this.setList(list);
    }
    @Override
    public int getCount() {
        return getList().size();
    }

    @Override
    public Object getItem(int position) {
        return getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_spend, parent, false);
        TextView txtName = convertView.findViewById(R.id.nameCate);
        ImageView image = convertView.findViewById(R.id.imageCate);
        txtName.setText(getList().get(i).getCategoryName());
        image.setImageResource(getList().get(i).getImg());
        return convertView;
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
