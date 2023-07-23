package com.example.quanlychitieu.sampledatas;

import com.example.quanlychitieu.models.AccountType;
import com.example.quanlychitieu.models.User;
import com.example.quanlychitieu.models.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletData {
    public static List<Wallet> getAllWallets() {
        List<Wallet> list = new ArrayList<>();
        list.add(new Wallet(1, "Tiền mặt", 3000000, "Tiền chi tiêu hàng tháng", new AccountType(1, "Phổ biến"), new User(1), "https://scontent.fsgn2-4.fna.fbcdn.net/v/t39.30808-6/338018331_254856326897520_3856794107188959630_n.jpg?_nc_cat=101&cb=99be929b-59f725be&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=uCyNjuM9_roAX-O3Bqa&_nc_ht=scontent.fsgn2-4.fna&oh=00_AfCNuIJEvr4xTLTi_XIUufI0GPruayk2yzEn9hDyxMPBCQ&oe=64B89DA6"));
        list.add(new Wallet(2, "Momo", 1000000, "Tiền dự phòng", new AccountType(2, "Ví điện tử"), new User(1), "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
        list.add(new Wallet(3, "Vietcombank", 1500000, "Tiền đầu tư", new AccountType(3, "Tài khoản ngân hàng"), new User(1), "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
        list.add(new Wallet(4, "Nam a Bank", 5000000, "Tiền tiết kiệm", new AccountType(3, "Tài khoản ngân hàng"), new User(1), "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"));
        return list;
    }
}
