package com.example.cinebooker.generalMethod;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDatabase {

    // Các thông tin kết nối
    private final String UserName = "sa"; // Tên người dùng
    private final String PassWord = "12345"; // Mật khẩu người dùng
    private final String portName = "1433"; // Cổng SQL Server
    private final String DataBaseName = "dbQuanLyXemPhim"; // Tên cơ sở dữ liệu
    private final String ServerName = "127.0.0.1"; // Máy chủ cơ sở dữ liệu (localhost)

    // Phương thức mở kết nối đến cơ sở dữ liệu
    public Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlserver://" + ServerName + ":" + portName + ";databaseName=" + DataBaseName + ";encrypt=true;trustServerCertificate=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, UserName, PassWord);
        } catch (ClassNotFoundException e) {
            System.err.println("SQL Server Driver không được tìm thấy.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    // Phương thức đóng kết nối cơ sở dữ liệu
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức đóng CallableStatement
    public static void closeCallableStatement(CallableStatement callableStatement) {
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức đóng PreparedStatement
    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức đóng ResultSet
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



} // Đóng class
