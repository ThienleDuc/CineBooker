package com.example.cinebooker.generalMethod;

public class NumberFormatter {
    public static String formatNumber(int number) {
        if (number >= 1000000) {
            return String.format("%.1fM", number / 1000000.0); // Chuyển đổi sang triệu (M)
        } else if (number >= 1000) {
            return String.format("%.1fk", number / 1000.0); // Chuyển đổi sang nghìn (k)
        } else {
            return String.valueOf(number); // Nếu nhỏ hơn 1000, không cần định dạng
        }
    }
}
