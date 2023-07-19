package com.example.quanlychitieu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.models.Category;

import java.util.List;


public class CategoryReceiveAdapter  {

//    private Context context;
//    private List<Category> categoryReceiveList;
//
//    public CategoryReceiveAdapter(Context context, List categoryReceiveList) {
//        this.setContext(context);
//        this.setCategoryReceiveList(categoryReceiveList);
//    }
//
//
//    public Context getContext() {
//        return context;
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }
//
//    public List<Category> getCategoryReceiveList() {
//        return categoryReceiveList;
//    }
//
//    public void setCategoryReceiveList(List<Category> categoryReceiveList) {
//        this.categoryReceiveList = categoryReceiveList;
//    }
//
//    @Override
//    public int getCount() {
//        return getCategoryReceiveList().size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return getCategoryReceiveList().get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int i, View convertView, ViewGroup parent) {
//        convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_receive, parent, false);
//        TextView txtName = convertView.findViewById(R.id.name);
//        ImageView image = convertView.findViewById(R.id.image);
//        txtName.setText(getCategoryReceiveList().get(i).getCategoryName());
//        image.setImageResource(getCategoryReceiveList().get(i).getImg());
//        return convertView;
//
//    }

}