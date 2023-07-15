package com.example.quanlychitieu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.UserSettingActivity;
import com.example.quanlychitieu.adapters.SettingAdapter;
import com.example.quanlychitieu.models.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {
    RecyclerView settingList;
    List<Setting> items = new ArrayList<>();
    TextView settingUsername;
    LinearLayout linearLayoutUserInfo;

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

        // Hide the action bar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
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

        settingList = view.findViewById(R.id.settingList);
        linearLayoutUserInfo = view.findViewById(R.id.linearLayoutUserInfo);

        items.add(new Setting(R.drawable.app_icon_background, getString(R.string.general_setting)));
        items.add(new Setting(R.drawable.app_icon_background, getString(R.string.dev_info)));

        SettingAdapter adapter = new SettingAdapter(items);
        adapter.setContext(getActivity());
        settingList.setAdapter(adapter);
        settingList.setLayoutManager(new LinearLayoutManager(getActivity()));

        handleGoToUserInfoActivity();

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
}