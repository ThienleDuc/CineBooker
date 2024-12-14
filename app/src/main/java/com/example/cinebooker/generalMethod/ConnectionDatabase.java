package com.example.cinebooker.generalMethod;

import android.os.StrictMode;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDatabase {
    // Phương thức mở kết nối đến cơ sở dữ liệu
    public Connection getConnection() {
        Connection connection = null;
        try {
            String IpInternet = "192.168.0.105";
            String url = "jdbc:jtds:sqlserver://" + IpInternet + ":1433;databasename=dbQuanLyXemPhim;user=sa;password=123456";
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connection = DriverManager.getConnection(url);
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
