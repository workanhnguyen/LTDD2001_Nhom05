package com.example.quanlychitieu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.holders.ChooseCategoryTypeHolder;
import com.example.quanlychitieu.models.CategoryType;

import java.util.List;

public class ChooseCategoryTypeAdapter extends RecyclerView.Adapter<ChooseCategoryTypeHolder> {
    private Context context;
    List<CategoryType> list;

    public ChooseCategoryTypeAdapter(List<CategoryType> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChooseCategoryTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_choose_category_type_item, parent, false);
        return new ChooseCategoryTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseCategoryTypeHolder holder, int position) {
        CategoryType categoryType = list.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.app_icon_background)
                .error(R.drawable.app_icon_background);

        Glide.with(context).load(categoryType.getImageLink())
                .apply(requestOptions).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getChooseCategoryTypeImage());

        holder.getChooseCategoryTypeName().setText(categoryType.getName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
