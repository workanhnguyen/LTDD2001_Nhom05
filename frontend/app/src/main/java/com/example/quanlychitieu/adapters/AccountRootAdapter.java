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
import com.example.quanlychitieu.holders.AccountRootHolder;
import com.example.quanlychitieu.models.AccountRoot;

import java.util.List;

public class AccountRootAdapter extends RecyclerView.Adapter<AccountRootHolder> {
    private Context context;
    private List<AccountRoot> accountRoots;

    public AccountRootAdapter(List<AccountRoot> accountRoots) {
        this.accountRoots = accountRoots;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AccountRootHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_account_root_item, parent, false);
        return new AccountRootHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountRootHolder holder, int position) {
        AccountRoot accountRoot = accountRoots.get(position);

        holder.getAccountRootItemTitle().setText(accountRoot.getName());
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.app_icon_background) // Replace with your placeholder image resource
                .error(R.drawable.app_icon_background); // Replace with your error image resource

        Glide.with(context)
                .load(accountRoot.getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getAccountRootItemImage());
    }

    @Override
    public int getItemCount() {
        return accountRoots == null ? 0 : accountRoots.size();
    }
}
