package com.example.cinebooker.generalMethod;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThienDateUtils {

    // Phương thức chuyển đổi Date thành String theo định dạng dd/MM/yyyy
    public static String formatDateToString(Date date) {
        if (date == null) {
            return null; // Trả về null nếu đối tượng Date là null
        }

        // Định dạng ngày cần chuyển đổi
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    // Phương thức chuyển đổi String thành Date (nếu cần thiết)
    public static Date stringToDate(String dateString) {
        try {
            // Định dạng ngày nhập vào
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu có lỗi
        }
    }
}
