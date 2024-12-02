package com.example.cinebooker.generalMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    // Thông tin kết nối cơ sở dữ liệu
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=dbQuanLyXemPhim";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";

    // Phương thức tạo kết nối
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Nạp driver SQL Server (chỉ cần thực hiện một lần khi ứng dụng khởi động)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Kết nối đến cơ sở dữ liệu
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công tới cơ sở dữ liệu");
        } catch (SQLException e) {
            System.err.println("SQL Exception: Không thể kết nối đến cơ sở dữ liệu.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Driver không tìm thấy: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    // Phương thức đóng kết nối
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Kết nối đã được đóng thành công.");
            } catch (SQLException e) {
                System.err.println("SQL Exception: Không thể đóng kết nối.");
                e.printStackTrace();
            }
        }
    }
}
