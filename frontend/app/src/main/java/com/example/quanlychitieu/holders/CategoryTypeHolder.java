package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class CategoryTypeHolder extends RecyclerView.ViewHolder {
    ImageView categoryTypeItemImage;
    TextView categoryTypeItemName;
    public CategoryTypeHolder(@NonNull View itemView) {
        super(itemView);

        categoryTypeItemImage = itemView.findViewById(R.id.categoryTypeItemImage);
        categoryTypeItemName = itemView.findViewById(R.id.categoryTypeItemName);
    }

    public ImageView getCategoryTypeItemImage() {
        return categoryTypeItemImage;
    }

    public void setCategoryTypeItemImage(ImageView categoryTypeItemImage) {
        this.categoryTypeItemImage = categoryTypeItemImage;
    }

    public TextView getCategoryTypeItemName() {
        return categoryTypeItemName;
    }

    public void setCategoryTypeItemName(TextView categoryTypeItemName) {
        this.categoryTypeItemName = categoryTypeItemName;
    }
}
