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
            //String IpInternet = "database-2.chi0cwy8qdtc.us-east-1.rds.amazonaws.com";
            String IpInternet = "192.168.1.13";
            String databaseName = "dbQuanLyXemPhim";
            String username = "sa";
            //String password = "leducthien.02092004";
            String password = "12345";


            String url = "jdbc:jtds:sqlserver://" + IpInternet + ":1433;databasename=" + databaseName + ";user=" + username + ";password=" + password;

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
