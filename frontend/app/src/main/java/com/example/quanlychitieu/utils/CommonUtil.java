package com.example.quanlychitieu.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CommonUtil {
    public static String getMoneyFormat(long money) {
        return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(money);
    }

    public static String[] getFirstAndLastName(String fullName) {
        String[] nameParts = fullName.trim().split("\\s+", 2); // Split by whitespace, limit to 2 parts

        String lastName = nameParts[0];
        String firstName = "";

        if (nameParts.length > 1) {
            firstName = nameParts[1];
        }

        return new String[]{lastName, firstName};
    }
}
