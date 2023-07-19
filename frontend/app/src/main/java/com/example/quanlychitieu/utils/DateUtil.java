package com.example.quanlychitieu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static final Calendar calendar = Calendar.getInstance();

    public static String getDayOfWeekVietnam(Date date) {
        calendar.set(date.getYear(), date.getMonth() - 1, date.getDay());

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String dayOfWeekString = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime()).toLowerCase();

        switch (dayOfWeekString) {
            case "monday":
                return "Thứ hai";
            case "tuesday":
                return "Thứ ba";
            case "wednesday":
                return "Thứ tư";
            case "thursday":
                return "Thứ năm";
            case "friday":
                return "Thứ sáu";
            case "saturday":
                return "Thứ bảy";
            case "sunday":
                return "Chủ nhật";
            default:
                return "Unknown";
        }
    }

    public static String getDayOfWeekEnglish(Date date) {
        calendar.set(date.getYear(), date.getMonth() - 1, date.getDay());

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime());
    }

    public static String getDate(Date date) {
        return new SimpleDateFormat("dd").format(date);
    }
    public static String getMonth(Date date) {
        return new SimpleDateFormat("MM").format(date);
    }
    public static String getYear(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }
}
