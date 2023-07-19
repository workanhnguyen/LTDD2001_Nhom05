package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class CategoryTypeIncomeHolder extends RecyclerView.ViewHolder {
    TextView categoryTypeIncomeItemTitle;
    ImageView categoryTypeIncomeItemImage;
    public CategoryTypeIncomeHolder(@NonNull View itemView) {
        super(itemView);

        categoryTypeIncomeItemTitle = itemView.findViewById(R.id.categoryTypeIncomeItemTitle);
        categoryTypeIncomeItemImage = itemView.findViewById(R.id.categoryTypeIncomeItemImage);
    }

    public TextView getCategoryTypeIncomeItemTitle() {
        return categoryTypeIncomeItemTitle;
    }

    public void setCategoryTypeIncomeItemTitle(TextView categoryTypeIncomeItemTitle) {
        this.categoryTypeIncomeItemTitle = categoryTypeIncomeItemTitle;
    }

    public ImageView getCategoryTypeIncomeItemImage() {
        return categoryTypeIncomeItemImage;
    }

    public void setCategoryTypeIncomeItemImage(ImageView categoryTypeIncomeItemImage) {
        this.categoryTypeIncomeItemImage = categoryTypeIncomeItemImage;
    }
}
