package com.example.quanlychitieu.adapters;

import static com.example.quanlychitieu.sampledatas.ListExpense.getExpenseList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.models.Expense;

import java.util.List;

public class ExpenseAdapter extends BaseAdapter {
    private List<Expense> lst;
    private Context context;


    public ExpenseAdapter(Context appContext, List<Expense> list){
        this.setContext(appContext);
        this.setLst(list);
    }

    @Override
    public int getCount() {
        return getLst().size();
    }

    @Override
    public Object getItem(int position) {
        return getLst().get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = LayoutInflater.from(getContext())
                .inflate(R.layout.expense_list, parent, false);
        TextView txtName = view.findViewById(R.id.name);
        ImageView image = view.findViewById(R.id.image);

        txtName.setText(getExpenseList().get(i).getName());
        image.setImageResource(getExpenseList().get(i).getImage());

        return view;
    }

    public List<Expense> getLst() {
        return lst;
    }

    public void setLst(List<Expense> lst) {
        this.lst = lst;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
