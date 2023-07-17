package com.example.quanlychitieu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.WalletAdapter;
import com.example.quanlychitieu.models.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletFragment extends Fragment {
    RecyclerView walletList;
    WalletAdapter adapter;
    List<Wallet> list = new ArrayList<>();
    public WalletFragment() { }

    public static WalletFragment newInstance(Bundle bundle) {
        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        // Show the action bar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
            activity.getSupportActionBar().setTitle(getString(R.string.account));
            activity.getSupportActionBar().setElevation(0);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        walletList = view.findViewById(R.id.walletList);

        list.add(new Wallet(1, "Tiền mặt", 3000000, "Tiền chi tiêu hàng tháng", 1, 1, "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
        list.add(new Wallet(1, "Tiền mặt", 1000000, "Tiền dự phòng", 2, 1, "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
        list.add(new Wallet(1, "Tiền mặt", 1500000, "Tiền đầu tư", 3, 1, "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
        list.add(new Wallet(1, "Tiền mặt", 5000000, "Tiền tiết kiệm", 5, 1, "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));

        adapter = new WalletAdapter(list);
        adapter.setContext(getContext());
        walletList.setLayoutManager(new LinearLayoutManager(getActivity()));
        walletList.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.btnSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
