package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class ChooseCategoryTypeHolder extends RecyclerView.ViewHolder {
    ImageView chooseCategoryTypeImage;
    TextView chooseCategoryTypeName;
    public ChooseCategoryTypeHolder(@NonNull View itemView) {
        super(itemView);

        chooseCategoryTypeImage = itemView.findViewById(R.id.chooseCategoryTypeItemImage);
        chooseCategoryTypeName = itemView.findViewById(R.id.chooseCategoryTypeItemName);
    }

    public ImageView getChooseCategoryTypeImage() {
        return chooseCategoryTypeImage;
    }

    public void setChooseCategoryTypeImage(ImageView chooseCategoryTypeImage) {
        this.chooseCategoryTypeImage = chooseCategoryTypeImage;
    }

    public TextView getChooseCategoryTypeName() {
        return chooseCategoryTypeName;
    }

    public void setChooseCategoryTypeName(TextView chooseCategoryTypeName) {
        this.chooseCategoryTypeName = chooseCategoryTypeName;
    }
}
