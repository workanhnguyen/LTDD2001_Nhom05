package com.example.quanlychitieu.dtos.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.EditTransactionActivity;
import com.example.quanlychitieu.holders.TransactionHolder;
import com.example.quanlychitieu.models.Transaction;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.utils.CustomConstant;
import com.example.quanlychitieu.utils.DateUtil;

import org.parceler.Parcels;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {
    private Context context;
    private List<Transaction> transactions;
    private LinearLayout transactionContentLayout;

    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public LinearLayout getTransactionContentLayout() {
        return transactionContentLayout;
    }

    public void setTransactionContentLayout(LinearLayout transactionContentLayout) {
        this.transactionContentLayout = transactionContentLayout;
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_transaction_item, parent, false);
        return new TransactionHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        // Handle contents
        holder.getTransactionDay().setText(DateUtil.getDate(transaction.getCreatedDate()));
        holder.getTransactionDayOfWeek().setText(DateUtil.getDayOfWeekVietnam(transaction.getCreatedDate()));
        holder.getTransactionMonthYear().setText(DateUtil.getMonth(transaction.getCreatedDate()) + "/" + DateUtil.getYear(transaction.getCreatedDate()));
        holder.getTransactionTotal().setText(CommonUtil.getMoneyFormat(transaction.getTotal()));
        holder.getTransactionName().setText(transaction.getCategoryType().getName());

        // Handle styles
        if (transaction.getCategoryType().getCategoryRoot().getType().equals(CustomConstant.CATEGORY_EXPENSE)) {
            holder.getTransactionTotal().setTextColor(context.getResources().getColor(R.color.red));
        } else {
            holder.getTransactionTotal().setTextColor(context.getResources().getColor(R.color.green));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcelable parcelable = Parcels.wrap(transaction);

                Intent intent = new Intent(context, EditTransactionActivity.class);
                intent.putExtra("transaction", parcelable);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions == null ? 0 : transactions.size();
    }
}
