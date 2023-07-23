package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class ChooseAccountTypeHolder extends RecyclerView.ViewHolder {
    ImageView chooseAccountTypeItemImage;
    TextView chooseAccountTypeItemName;
    public ChooseAccountTypeHolder(@NonNull View itemView) {
        super(itemView);

        chooseAccountTypeItemImage = itemView.findViewById(R.id.chooseAccountTypeItemImage);
        chooseAccountTypeItemName = itemView.findViewById(R.id.chooseAccountTypeItemName);
    }

    public ImageView getChooseAccountTypeItemImage() {
        return chooseAccountTypeItemImage;
    }

    public void setChooseAccountTypeItemImage(ImageView chooseAccountTypeItemImage) {
        this.chooseAccountTypeItemImage = chooseAccountTypeItemImage;
    }

    public TextView getChooseAccountTypeItemName() {
        return chooseAccountTypeItemName;
    }

    public void setChooseAccountTypeItemName(TextView chooseAccountTypeItemName) {
        this.chooseAccountTypeItemName = chooseAccountTypeItemName;
    }
}
