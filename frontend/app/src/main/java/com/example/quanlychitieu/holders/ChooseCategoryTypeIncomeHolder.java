package com.example.quanlychitieu.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;

public class ChooseCategoryTypeIncomeHolder extends RecyclerView.ViewHolder {
    TextView chooseCategoryTypeIncomeItemTitle;
    ImageView chooseCategoryTypeIncomeItemImage;
    public ChooseCategoryTypeIncomeHolder(@NonNull View itemView) {
        super(itemView);

        chooseCategoryTypeIncomeItemTitle = itemView.findViewById(R.id.chooseCategoryTypeIncomeItemTitle);
        chooseCategoryTypeIncomeItemImage = itemView.findViewById(R.id.chooseCategoryTypeIncomeItemImage);
    }

    public TextView getChooseCategoryTypeIncomeItemTitle() {
        return chooseCategoryTypeIncomeItemTitle;
    }

    public void setChooseCategoryTypeIncomeItemTitle(TextView chooseCategoryTypeIncomeItemTitle) {
        this.chooseCategoryTypeIncomeItemTitle = chooseCategoryTypeIncomeItemTitle;
    }

    public ImageView getChooseCategoryTypeIncomeItemImage() {
        return chooseCategoryTypeIncomeItemImage;
    }

    public void setChooseCategoryTypeIncomeItemImage(ImageView chooseCategoryTypeIncomeItemImage) {
        this.chooseCategoryTypeIncomeItemImage = chooseCategoryTypeIncomeItemImage;
    }
}
