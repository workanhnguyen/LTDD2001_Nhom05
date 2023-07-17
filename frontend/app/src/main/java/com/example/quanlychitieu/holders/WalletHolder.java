package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class WalletHolder extends RecyclerView.ViewHolder {
    ImageView walletImage;
    TextView walletName, walletBalance;
    public WalletHolder(@NonNull View itemView) {
        super(itemView);

        walletImage = itemView.findViewById(R.id.walletImage);
        walletName = itemView.findViewById(R.id.walletName);
        walletBalance = itemView.findViewById(R.id.walletBalance);
    }

    public ImageView getWalletImage() {
        return walletImage;
    }

    public void setWalletImage(ImageView walletImage) {
        this.walletImage = walletImage;
    }

    public TextView getWalletName() {
        return walletName;
    }

    public void setWalletName(TextView walletName) {
        this.walletName = walletName;
    }

    public TextView getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(TextView walletBalance) {
        this.walletBalance = walletBalance;
    }
}
