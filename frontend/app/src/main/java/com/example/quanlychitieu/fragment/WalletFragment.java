package com.example.quanlychitieu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.activities.CreateWalletActivity;
import com.example.quanlychitieu.adapters.WalletAdapter;
import com.example.quanlychitieu.models.AccountRoot;
import com.example.quanlychitieu.models.AccountType;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.models.Wallet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WalletFragment extends Fragment {
    RecyclerView walletList;
    WalletAdapter adapter;
    FloatingActionButton btnCreateNewWallet;
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

        initializeElement(view);
        loadListWalletData();
        handleShowDataToUI();

        handleSwitchToCreateWalletActivity();
    }

    private void handleSwitchToCreateWalletActivity() {
        btnCreateNewWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateWalletActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleShowDataToUI() {
        adapter = new WalletAdapter(list);
        adapter.setContext(getActivity());
        walletList.setLayoutManager(new LinearLayoutManager(getActivity()));
        walletList.setAdapter(adapter);
    }

    private void loadListWalletData() {
        list.add(new Wallet(1, "Tiền mặt", 3000000, "Tiền chi tiêu hàng tháng", new AccountType(1, "Phổ biến"), new User(1), "https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/338018331_254856326897520_3856794107188959630_n.jpg?_nc_cat=101&cb=99be929b-59f725be&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=uCyNjuM9_roAX-O3Bqa&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfCNuIJEvr4xTLTi_XIUufI0GPruayk2yzEn9hDyxMPBCQ&oe=64B89DA6"));
        list.add(new Wallet(2, "Momo", 1000000, "Tiền dự phòng", new AccountType(2, "Ví điện tử"), new User(1), "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
        list.add(new Wallet(3, "Vietcombank", 1500000, "Tiền đầu tư", new AccountType(3, "Tài khoản ngân hàng"), new User(1), "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
        list.add(new Wallet(4, "Nam a Bank", 5000000, "Tiền tiết kiệm", new AccountType(3, "Tài khoản ngân hàng"), new User(1), "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
    }

    private void initializeElement(View view) {
        btnCreateNewWallet = view.findViewById(R.id.btnCreateNewWallet);
        walletList = view.findViewById(R.id.walletList);
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
