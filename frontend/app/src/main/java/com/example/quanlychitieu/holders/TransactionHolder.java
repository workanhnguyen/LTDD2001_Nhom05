package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class TransactionHolder extends RecyclerView.ViewHolder {

    TextView transactionDay, transactionDayOfWeek, transactionMonthYear, transactionTotal;

    public TransactionHolder(@NonNull View itemView) {
        super(itemView);

        transactionDay = itemView.findViewById(R.id.transactionDay);
        transactionDayOfWeek = itemView.findViewById(R.id.transactionDayOfWeek);
        transactionMonthYear = itemView.findViewById(R.id.transactionMonthYear);
        transactionTotal = itemView.findViewById(R.id.transactionTotal);
    }

    public TextView getTransactionDay() {
        return transactionDay;
    }

    public TextView getTransactionDayOfWeek() {
        return transactionDayOfWeek;
    }

    public TextView getTransactionMonthYear() {
        return transactionMonthYear;
    }

    public TextView getTransactionTotal() {
        return transactionTotal;
    }
}
