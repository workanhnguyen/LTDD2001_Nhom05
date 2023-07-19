package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class ChooseCategoryTypeExpenseHolder extends RecyclerView.ViewHolder {
    ImageView chooseCategoryTypeExpenseItemImage;
    TextView chooseCategoryTypeExpenseItemTitle;
    public ChooseCategoryTypeExpenseHolder(@NonNull View itemView) {
        super(itemView);

        chooseCategoryTypeExpenseItemTitle = itemView.findViewById(R.id.chooseCategoryTypeExpenseItemTitle);
        chooseCategoryTypeExpenseItemImage = itemView.findViewById(R.id.chooseCategoryTypeExpenseItemImage);
    }

    public ImageView getChooseCategoryTypeExpenseItemImage() {
        return chooseCategoryTypeExpenseItemImage;
    }

    public void setChooseCategoryTypeExpenseItemImage(ImageView chooseCategoryTypeExpenseItemImage) {
        this.chooseCategoryTypeExpenseItemImage = chooseCategoryTypeExpenseItemImage;
    }

    public TextView getChooseCategoryTypeExpenseItemTitle() {
        return chooseCategoryTypeExpenseItemTitle;
    }

    public void setChooseCategoryTypeExpenseItemTitle(TextView chooseCategoryTypeExpenseItemTitle) {
        this.chooseCategoryTypeExpenseItemTitle = chooseCategoryTypeExpenseItemTitle;
    }
}
