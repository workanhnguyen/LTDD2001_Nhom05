package com.example.quanlychitieu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quanlychitieu.R;

import java.util.List;

public class SpinnerLanguageAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> dataList;

    public SpinnerLanguageAdapter(Context context, List<String> dataList) {
        super(context, R.layout.custom_spinner_language_item, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.custom_spinner_language_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.spinnerLanguageItemTitle);
        textView.setText(dataList.get(position));

        return convertView;
    }
}

