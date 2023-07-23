package com.example.quanlychitieu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.holders.ChooseAccountTypeHolder;
import com.example.quanlychitieu.models.AccountType;

import java.util.ArrayList;
import java.util.List;

public class ChooseAccountTypeAdapter extends RecyclerView.Adapter<ChooseAccountTypeHolder> {
    private Context context;
    private List<AccountType> list = new ArrayList<>();

    public ChooseAccountTypeAdapter(List<AccountType> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChooseAccountTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_choose_account_type_item, parent, false);
        return new ChooseAccountTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseAccountTypeHolder holder, int position) {
        AccountType accountType = list.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.app_icon_background)
                .error(R.drawable.app_icon_background);
        Glide.with(context)
                .load(accountType.getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getChooseAccountTypeItemImage());

        holder.getChooseAccountTypeItemName().setText(accountType.getName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
