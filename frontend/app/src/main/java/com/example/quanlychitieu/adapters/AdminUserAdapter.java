package com.example.quanlychitieu.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.AdminEditUserActivity;
import com.example.quanlychitieu.holders.AdminUserHolder;
import com.example.quanlychitieu.models.User;

import org.parceler.Parcels;

import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserHolder> {
    private Context context;
    List<User> users;

    public AdminUserAdapter(List<User> users) {
        this.users = users;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdminUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_admin_user_item, parent, false);
        return new AdminUserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminUserHolder holder, int position) {
        User user = users.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.blank_avatar) // Replace with your placeholder image resource
                .error(R.drawable.blank_avatar); // Replace with your error image resource

        Glide.with(context)
                .load(user.getImageLink())
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getAdminUserAvatar());

        holder.getAdminUserName().setText(user.getLastname() + " " + user.getFirstname());
        holder.getAdminUserEmail().setText(user.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parcelable parcelable = Parcels.wrap(user);

                Intent intent = new Intent(context, AdminEditUserActivity.class);
                intent.putExtra("user", parcelable);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }
}
