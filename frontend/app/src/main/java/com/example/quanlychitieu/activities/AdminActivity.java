package com.example.quanlychitieu.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.AdminUserAdapter;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.presenters.AdminPresenter;
import com.example.quanlychitieu.views.AdminView;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements AdminView {
    TextView adminAlert;
    RecyclerView adminUserList;
    List<User> list = new ArrayList<>();
    SharedPreferences userPref;
    AdminPresenter adminPresenter;
    AdminUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.manage_user);
            actionBar.setElevation(0);
        }

        initializeElement();
        loadData();
    }

    private void loadData() {
        adminAlert.setText(getString(R.string.loading_data));
        adminPresenter.getUsers();
    }

    private void initializeElement() {
        userPref = getSharedPreferences("loggingUser", Context.MODE_PRIVATE);
        adminPresenter = new AdminPresenter(this);

        adminUserList = findViewById(R.id.adminUserList);
        adminAlert = findViewById(R.id.adminAlert);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnAdminLogout) {
            SharedPreferences.Editor editor = userPref.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(AdminActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.btnAdminRefresh) {
            loadData();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showUserList(List<User> list) {
        if (!list.isEmpty()) {
            adminAlert.setText("");

            adapter = new AdminUserAdapter(list);
            adapter.setContext(this);
            adminUserList.setAdapter(adapter);
            adminUserList.setLayoutManager(new LinearLayoutManager(this));
        } else {
            adminAlert.setText(getString(R.string.no_data));
        }
    }

    @Override
    public void showError() {
        adminAlert.setText(getString(R.string.error_loading_data));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}