package com.example.quanlychitieu.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.UserSettingActivity;
import com.example.quanlychitieu.adapters.SettingAdapter;
import com.example.quanlychitieu.models.Setting;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {
    RecyclerView settingList;
    TextView settingUserFullName, settingUserEmail;
    ImageView settingUserImage;
    List<Setting> items = new ArrayList<>();
    LinearLayout linearLayoutUserInfo;
    SharedPreferences sharedPreferences;

    public SettingFragment() { }

    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        sharedPreferences = requireActivity().getSharedPreferences("loggingUser", Context.MODE_PRIVATE);

        // Hide the action bar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setTitle(R.string.setting);
            activity.getSupportActionBar().setElevation(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        initializeElement(view);
        handleShowDataToUI();
        handleGoToUserInfoActivity();

    }

    private void handleShowDataToUI() {
        items.add(new Setting(R.drawable.baseline_settings_24, getString(R.string.general_setting)));
        items.add(new Setting(R.drawable.baseline_info_grey_24, getString(R.string.dev_info)));

        SettingAdapter adapter = new SettingAdapter(items);
        adapter.setContext(getActivity());
        settingList.setAdapter(adapter);
        settingList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Show logging user data
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.blank_avatar)
                .error(R.drawable.blank_avatar);
        Glide.with(requireActivity())
                .load(sharedPreferences.getString("imageLink", ""))
                .apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(settingUserImage);
        settingUserFullName.setText(sharedPreferences.getString("lastName", "") + " " + sharedPreferences.getString("firstName", ""));
        settingUserEmail.setText(sharedPreferences.getString("email", ""));
    }

    private void initializeElement(View view) {
        settingList = view.findViewById(R.id.settingList);
        linearLayoutUserInfo = view.findViewById(R.id.linearLayoutUserInfo);
        settingUserFullName = view.findViewById(R.id.settingUserFullName);
        settingUserEmail = view.findViewById(R.id.settingUserEmail);
        settingUserImage = view.findViewById(R.id.settingUserImage);
    }

    private void handleGoToUserInfoActivity() {
        linearLayoutUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserSettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_only_title, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}