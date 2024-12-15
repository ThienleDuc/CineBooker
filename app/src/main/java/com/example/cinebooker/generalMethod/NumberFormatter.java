package com.example.cinebooker.generalMethod;

public class NumberFormatter {
    public static String formatNumber(double number) {
        if (number >= 1000000) {
            return String.format("%.1fM", number / 1000000.0); // Chuyển đổi sang triệu (M)
        } else if (number >= 1000) {
            return String.format("%.1fk", number / 1000.0); // Chuyển đổi sang nghìn (k)
        } else {
            return String.format("%.1f", number); // Nếu nhỏ hơn 1000, không cần định dạng
        }
    }

    public static String formatIntValueToString(int number) {
        if (number >= 1000000) {
            return String.format("%.1fM", (double)number / 1000000);
        } else if (number >= 1000) {
            return String.format("%.1fk", (double)number / 1000);
        } else {
            return String.format("%d", number);
        }
    }

    public static String formatDoubleValueToString(double number) {
        return String.format("%.1f", number);
    }
}
