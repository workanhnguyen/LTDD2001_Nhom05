package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class AccountRootHolder extends RecyclerView.ViewHolder {
    TextView accountRootItemTitle;
    ImageView accountRootItemImage;

    public AccountRootHolder(@NonNull View itemView) {
        super(itemView);

        accountRootItemTitle = itemView.findViewById(R.id.accountRootItemTitle);
        accountRootItemImage = itemView.findViewById(R.id.accountRootItemImage);
    }

    public TextView getAccountRootItemTitle() {
        return accountRootItemTitle;
    }

    public void setAccountRootItemTitle(TextView accountRootItemTitle) {
        this.accountRootItemTitle = accountRootItemTitle;
    }

    public ImageView getAccountRootItemImage() {
        return accountRootItemImage;
    }

    public void setAccountRootItemImage(ImageView accountRootItemImage) {
        this.accountRootItemImage = accountRootItemImage;
    }
}
