package com.example.quanlychitieu.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.quanlychitieu.R;
import com.example.quanlychitieu.adapters.AdminUserAdapter;
import com.example.quanlychitieu.models.User;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    RecyclerView adminUserList;
    List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.manage_user);
            actionBar.setElevation(0);
        }

        adminUserList = findViewById(R.id.adminUserList);

        list.add(new User(1, "Anh", "Nguyễn", "anhnguyen", "123", "anhnguyen@gmail.com", "https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/338018331_254856326897520_3856794107188959630_n.jpg?_nc_cat=101&cb=99be929b-59f725be&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=uCyNjuM9_roAX-O3Bqa&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfCNuIJEvr4xTLTi_XIUufI0GPruayk2yzEn9hDyxMPBCQ&oe=64B89DA6", "Sinh viên", true));
        list.add(new User(2, "Đạt", "Lương", "luongdat", "123", "luongdat@gmail.com", "https://scontent.fsgn2-3.fna.fbcdn.net/v/t39.30808-6/341033378_927338095248252_3614182632098763100_n.jpg?stp=cp6_dst-jpg&_nc_cat=107&cb=99be929b-59f725be&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=6mjLrvWL7ugAX_-Zj46&_nc_ht=scontent.fsgn2-3.fna&oh=00_AfC3Ese2kBAxHZiGAOuJ1zBqyH13h7gGn6tkF0NlG2QvBw&oe=64B7E346", "Sinh viên", true));
        list.add(new User(3, "Quỳnh", "Đào", "quynhdao", "123", "quynhdao@gmail.com", "https://scontent.fsgn2-6.fna.fbcdn.net/v/t39.30808-6/325171580_507602668030371_976961888220416543_n.jpg?stp=cp6_dst-jpg&_nc_cat=111&cb=99be929b-59f725be&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=hS4k1KgFdrUAX-FliMG&_nc_ht=scontent.fsgn2-6.fna&oh=00_AfCgHxr4DBYfERad62lFyhX2iNq1iewfB3TKLLGtVmG6RQ&oe=64B96978", "Sinh viên", false));
        list.add(new User(1, "Mãi", "Văn", "maivan", "123", "maivan@gmail.com", "https://scontent.fsgn2-5.fna.fbcdn.net/v/t39.30808-6/280334526_126618866654533_5990798067395328668_n.jpg?_nc_cat=104&cb=99be929b-59f725be&ccb=1-7&_nc_sid=174925&_nc_ohc=_i_2jygUwTcAX_uD-PC&_nc_ht=scontent.fsgn2-5.fna&oh=00_AfAEfEvT1XI5JLCQkCDSaH_h6qvz0-BjTZuN6ny7SOJpog&oe=64B857EF", "Sinh viên", true));

        AdminUserAdapter adapter = new AdminUserAdapter(list);
        adapter.setContext(this);
        adminUserList.setAdapter(adapter);
        adminUserList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }
}