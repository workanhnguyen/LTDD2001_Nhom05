package com.example.quanlychitieu.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.WalletDetailActivity;
import com.example.quanlychitieu.holders.WalletHolder;
import com.example.quanlychitieu.models.Wallet;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletHolder> {
    private Context context;
    private List<Wallet> wallets;

    public WalletAdapter(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WalletHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_wallet_item, parent, false);
        return new WalletHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletHolder holder, int position) {
        Wallet wallet = wallets.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.blank_avatar) // Replace with your placeholder image resource
                .error(R.drawable.blank_avatar); // Replace with your error image resource

        Glide.with(context)
                .load(wallet.getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getWalletImage());

        holder.getWalletName().setText(wallet.getName());
        holder.getWalletBalance().setText(wallet.getBalance() + " đ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcelable parcelable = Parcels.wrap(wallet);

                Intent intent = new Intent(context, WalletDetailActivity.class);
                intent.putExtra("wallet", parcelable);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallets == null ? 0 : wallets.size();
    }
}
