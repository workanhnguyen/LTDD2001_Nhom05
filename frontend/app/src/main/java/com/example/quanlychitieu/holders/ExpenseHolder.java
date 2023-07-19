package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class ExpenseHolder extends RecyclerView.ViewHolder {
    ImageView expenseItemImage;
    TextView expenseItemTitle;
    public ExpenseHolder(@NonNull View itemView) {
        super(itemView);

        expenseItemImage = itemView.findViewById(R.id.expenseItemImage);
    }
}
