package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class CategoryTypeExpenseHolder extends RecyclerView.ViewHolder {
    ImageView categoryTypeExpenseItemImage;
    TextView categoryTypeExpenseItemTitle;
    public CategoryTypeExpenseHolder(@NonNull View itemView) {
        super(itemView);

        categoryTypeExpenseItemTitle = itemView.findViewById(R.id.categoryTypeExpenseItemTitle);
        categoryTypeExpenseItemImage = itemView.findViewById(R.id.categoryTypeExpenseItemImage);
    }

    public ImageView getCategoryTypeExpenseItemImage() {
        return categoryTypeExpenseItemImage;
    }

    public void setCategoryTypeExpenseItemImage(ImageView categoryTypeExpenseItemImage) {
        this.categoryTypeExpenseItemImage = categoryTypeExpenseItemImage;
    }

    public TextView getCategoryTypeExpenseItemTitle() {
        return categoryTypeExpenseItemTitle;
    }

    public void setCategoryTypeExpenseItemTitle(TextView categoryTypeExpenseItemTitle) {
        this.categoryTypeExpenseItemTitle = categoryTypeExpenseItemTitle;
    }
}
