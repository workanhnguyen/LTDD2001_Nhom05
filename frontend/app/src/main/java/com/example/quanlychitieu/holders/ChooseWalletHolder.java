package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class ChooseWalletHolder extends RecyclerView.ViewHolder {
    TextView chooseWalletName, chooseWalletBalance;
    ImageView chooseWalletImage;
    public ChooseWalletHolder(@NonNull View itemView) {
        super(itemView);

        chooseWalletName = itemView.findViewById(R.id.chooseWalletName);
        chooseWalletBalance = itemView.findViewById(R.id.chooseWalletBalance);
        chooseWalletImage = itemView.findViewById(R.id.chooseWalletImage);
    }

    public TextView getChooseWalletName() {
        return chooseWalletName;
    }

    public void setChooseWalletName(TextView chooseWalletName) {
        this.chooseWalletName = chooseWalletName;
    }

    public TextView getChooseWalletBalance() {
        return chooseWalletBalance;
    }

    public void setChooseWalletBalance(TextView chooseWalletBalance) {
        this.chooseWalletBalance = chooseWalletBalance;
    }

    public ImageView getChooseWalletImage() {
        return chooseWalletImage;
    }

    public void setChooseWalletImage(ImageView chooseWalletImage) {
        this.chooseWalletImage = chooseWalletImage;
    }
}
