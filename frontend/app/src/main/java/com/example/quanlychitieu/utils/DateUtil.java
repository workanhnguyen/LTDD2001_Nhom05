package com.example.quanlychitieu.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    @SuppressLint("SimpleDateFormat")
    public static String convertSecondsToFormattedDate(Long seconds, String pattern) {
        Date date = new Date(seconds * 1000);
        return new SimpleDateFormat(pattern).format(date);
    }

    public static long convertDateToSeconds(Date date) {
        return date.getTime() / 1000;
    }

    public static String getDayOfWeekVietnam(Long seconds) {
        Date date = new Date(seconds * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String dayOfWeekString = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime()).toLowerCase();

        return switch (dayOfWeek) {
            case 1 -> "Chủ nhật";
            case 2 -> "Thứ hai";
            case 3 -> "Thứ ba";
            case 4 -> "Thứ tư";
            case 5 -> "Thứ năm";
            case 6 -> "Thứ sáu";
            case 7 -> "Thứ bảy";
            default -> "Không xác định";
        };
    }

    public static String getDayOfWeekEnglish(Long seconds) {
        Date date = new Date(seconds * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String dayOfWeekString = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(calendar.getTime()).toLowerCase();

        return switch (dayOfWeek) {
            case 1 -> "Sunday";
            case 2 -> "Monday";
            case 3 -> "Tuesday";
            case 4 -> "Wednesday";
            case 5 -> "Thursday";
            case 6 -> "Friday";
            case 7 -> "Saturday";
            default -> "Unknown";
        };
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDate(Long seconds) {
        return new SimpleDateFormat("dd").format(new Date(seconds * 1000));
    }
    @SuppressLint("SimpleDateFormat")
    public static String getMonth(Long seconds) {
        return new SimpleDateFormat("MM").format(new Date(seconds * 1000));
    }
    @SuppressLint("SimpleDateFormat")
    public static String getYear(Long seconds) {
        return new SimpleDateFormat("yyyy").format(new Date(seconds * 1000));
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static Date parseStringToDate(String dateString, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern, Locale.ENGLISH).parse(dateString);
    }
}
