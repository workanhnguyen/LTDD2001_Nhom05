package com.example.quanlychitieu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.holders.TransactionHolder;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.utils.DateUtil;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {
    private List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_transaction_item, parent, false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.getTransactionDay().setText(String.valueOf(transaction.getCreatedDate().getDate()));
        holder.getTransactionDayOfWeek().setText(String.valueOf(DateUtil.getDayOfWeekVietnam(transaction.getCreatedDate())));
        holder.getTransactionMonthYear().setText(String.valueOf(transaction.getCreatedDate().getMonth() + 1 + "/" + (transaction.getCreatedDate().getYear() + 1900)));
        holder.getTransactionSumIncome().setText(String.valueOf(transaction.getTotal()));
        holder.getTransactionSumExpense().setText(String.valueOf(transaction.getTotal()));
    }

    @Override
    public int getItemCount() {
        return transactions == null ? 0 : transactions.size();
    }
}
