package com.example.quanlychitieu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.holders.TransactionHolder;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.utils.CommonUtil;
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
        holder.getTransactionDay().setText(DateUtil.getDate(transaction.getCreatedDate()));
        holder.getTransactionDayOfWeek().setText(DateUtil.getDayOfWeekVietnam(transaction.getCreatedDate()));
        holder.getTransactionMonthYear().setText(DateUtil.getMonth(transaction.getCreatedDate()) + "/" + DateUtil.getYear(transaction.getCreatedDate()));
        holder.getTransactionTotal().setText(CommonUtil.getMoneyFormat(transaction.getTotal()));
    }

    @Override
    public int getItemCount() {
        return transactions == null ? 0 : transactions.size();
    }
}
