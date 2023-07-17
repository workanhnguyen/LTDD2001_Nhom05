package com.example.quanlychitieu.models;

import com.example.quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;

public class ListExpense {
    public static List<Expense> getExpenseList() {
        List<Expense> expenseList = new ArrayList<>();

        Expense spend = new Expense();
        spend.setName("Chi tiền");
        spend.setImage(R.drawable.chi_tien);
        expenseList.add(spend);

        Expense receive= new Expense();
        receive.setName("Thu tiền");
        receive.setImage(R.drawable.thu_tien);
        expenseList.add(receive);
        return expenseList;
    }
}
