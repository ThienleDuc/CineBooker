package com.example.cinebooker.PhanCongQuoc.activity;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_Voucher;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_voucherAdapter;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_voucherAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketvoucherMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class voucher extends AppCompatActivity {

    private RecyclerView list_voucher;
    private Ticket_voucherAdapter ticketvoucherAdapter;
    private List<ticketvoucherMoviesEntity> ticketvoucherMoviesList;
    private TextView soluong;  // Khai báo TextView soluong

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        // Lấy tham chiếu các view
        ImageView back = findViewById(R.id.voucher_back);
        soluong = findViewById(R.id.soluong_voucher); // Gán TextView soluong

        back.setOnClickListener(v -> onBackPressed());

        // Khởi tạo RecyclerView và thiết lập layout manager
        list_voucher = findViewById(R.id.list_voucher);
        list_voucher.setLayoutManager(new LinearLayoutManager(this));

        // Gọi phương thức load voucher và tổng số voucher
        voucher();
        loadTotalVoucherCount();  // Gọi hàm lấy tổng số voucher
    }

    private void voucher() {
        RecyclerView voucherRecyclerView = findViewById(R.id.list_voucher);
        BL_Voucher blVoucher = new BL_Voucher();

        // Load dữ liệu từ BL_Voucher
        blVoucher.loadVoucherVertical(this, voucherRecyclerView);
    }

    // Phương thức lấy tổng số voucher từ ba bảng
    private void loadTotalVoucherCount() {
        new Thread(() -> {
            int totalVoucherCount = getTotalVoucherCount(); // Lấy tổng số voucher
            runOnUiThread(() -> soluong.setText(String.valueOf(totalVoucherCount))); // Cập nhật TextView soluong
        }).start();
    }

    // Phương thức lấy tổng số voucher từ ba bảng
    private int getTotalVoucherCount() {
        int totalVoucherCount = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = new ConnectionDatabase().getConnection(); // Sử dụng kết nối đã có sẵn
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return totalVoucherCount;
            }

            // Truy vấn tổng số voucher từ ba bảng
            String sql = "SELECT " +
                    "(SELECT COUNT(*) FROM VoucherCuaToi) + " +
                    "(SELECT COUNT(*) FROM VoucherDoiTac) + " +
                    "(SELECT COUNT(*) FROM VoucherUngDung) AS TotalVoucherCount";
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalVoucherCount = resultSet.getInt("TotalVoucherCount");
            }

        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy tổng số voucher: ", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng kết nối: ", e);
            }
        }

        return totalVoucherCount;
    }
}
