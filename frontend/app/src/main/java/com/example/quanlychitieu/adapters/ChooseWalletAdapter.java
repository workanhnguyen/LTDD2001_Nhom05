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
import com.example.quanlychitieu.holders.ChooseWalletHolder;
import com.example.quanlychitieu.models.Wallet;
import com.example.quanlychitieu.utils.AdapterListener;
import com.example.quanlychitieu.utils.CommonUtil;
import com.example.quanlychitieu.utils.PassDataUtil;

import java.util.List;

public class ChooseWalletAdapter extends RecyclerView.Adapter<ChooseWalletHolder> {
    private Context context;
    private List<Wallet> wallets;
    private AdapterListener adapterListener;
    private PassDataUtil passDataUtil;

    public ChooseWalletAdapter(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterListener getAdapterListener() {
        return adapterListener;
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }

    public PassDataUtil getPassDataUtil() {
        return passDataUtil;
    }

    public void setPassDataUtil(PassDataUtil passDataUtil) {
        this.passDataUtil = passDataUtil;
    }

    @NonNull
    @Override
    public ChooseWalletHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_choose_wallet_item, parent, false);
        return new ChooseWalletHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseWalletHolder holder, int position) {
        Wallet wallet = wallets.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.app_icon_background) // Replace with your placeholder image resource
                .error(R.drawable.app_icon_background); // Replace with your error image resource

        Glide.with(context)
                .load(wallet.getAccountType().getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getChooseWalletImage());

        holder.getChooseWalletName().setText(wallet.getName());
        holder.getChooseWalletBalance().setText(CommonUtil.getMoneyFormat(wallet.getBalance()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passDataUtil != null) {
                    passDataUtil.passData(wallet);
                    adapterListener.onFinishActivity();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallets == null ? 0 : wallets.size();
    }
}
