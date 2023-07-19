package com.example.quanlychitieu.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CommonUtil {
    public static String getMoneyFormat(long money) {
        return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(money);
    }
}
