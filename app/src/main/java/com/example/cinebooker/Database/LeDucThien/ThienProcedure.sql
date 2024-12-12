use dbQuanLyXemPhim
GO
-- Đã sử dụng
CREATE PROCEDURE pr_LayPhimDangChieu
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        P.MaPhim,
        P.AnhPhim,
        P.TenPhim,
        P.Tuoi,
        P.DinhDangPhim,
        STRING_AGG(TC.TenTheLoai, ', ') AS TenTheLoai,
        -- Chuyển đổi NgayKhoiChieu và NgayKetThuc sang định dạng dd/MM/yyyy
        FORMAT(P.NgayKhoiChieu, 'dd/MM/yyyy') AS NgayKhoiChieu,
        FORMAT(P.NgayKetThuc, 'dd/MM/yyyy') AS NgayKetThuc,
        P.TrangThaiChieu,
        -- Chuyển đổi ThoiLuong thành định dạng HH:mm:ss
        RIGHT('00' + CAST(DATEPART(HOUR, P.ThoiLuong) AS VARCHAR), 2) + ':' +
        RIGHT('00' + CAST(DATEPART(MINUTE, P.ThoiLuong) AS VARCHAR), 2) + ':' +
        RIGHT('00' + CAST(DATEPART(SECOND, P.ThoiLuong) AS VARCHAR), 2) AS ThoiLuong,
        -- Gọi hàm fn_DiemDanhGiaPhimTrungBinh để tính điểm trung bình
        dbo.fn_DiemDanhGiaPhimTrungBinh(P.MaPhim) AS DiemDanhGiaTrungBinh
    FROM 
        Phim P
    LEFT JOIN 
        TheLoai TL ON P.MaPhim = TL.MaPhim
    LEFT JOIN 
        TheLoaiCha TC ON TL.MaTheLoaiCha = TC.MaTheLoaiCha
    WHERE 
        P.TrangThaiChieu = N'Đang chiếu'
    GROUP BY 
        P.MaPhim, 
        P.AnhPhim, 
        P.TenPhim, 
        P.Tuoi, 
        P.DinhDangPhim, 
        P.NgayKhoiChieu, 
        P.NgayKetThuc, 
        P.TrangThaiChieu, 
        P.ThoiLuong;
END;

EXEC dbo.pr_LayPhimDangChieu
GO
Select * from Phim Where TrangThaiChieu = N'Đang chiếu'
GO
CREATE PROCEDURE pr_LayPhimSapChieu
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        P.MaPhim,
        P.AnhPhim,
        P.TenPhim,
        P.Tuoi,
        P.DinhDangPhim,
        STRING_AGG(TC.TenTheLoai, ', ') AS TenTheLoai,
        -- Chuyển đổi NgayKhoiChieu và NgayKetThuc sang định dạng dd/MM/yyyy
        FORMAT(P.NgayKhoiChieu, 'dd/MM/yyyy') AS NgayKhoiChieu,
        FORMAT(P.NgayKetThuc, 'dd/MM/yyyy') AS NgayKetThuc,
        P.TrangThaiChieu,
        -- Chuyển đổi ThoiLuong thành định dạng HH:mm:ss
        RIGHT('00' + CAST(DATEPART(HOUR, P.ThoiLuong) AS VARCHAR), 2) + ':' +
        RIGHT('00' + CAST(DATEPART(MINUTE, P.ThoiLuong) AS VARCHAR), 2) + ':' +
        RIGHT('00' + CAST(DATEPART(SECOND, P.ThoiLuong) AS VARCHAR), 2) AS ThoiLuong,
        -- Gọi hàm fn_DiemDanhGiaPhimTrungBinh để tính điểm trung bình
        dbo.fn_DiemDanhGiaPhimTrungBinh(P.MaPhim) AS DiemDanhGiaTrungBinh
    FROM 
        Phim P
    LEFT JOIN 
        TheLoai TL ON P.MaPhim = TL.MaPhim
    LEFT JOIN 
        TheLoaiCha TC ON TL.MaTheLoaiCha = TC.MaTheLoaiCha
    WHERE 
        P.TrangThaiChieu = N'Sắp chiếu'
    GROUP BY 
        P.MaPhim, 
        P.AnhPhim, 
        P.TenPhim, 
        P.Tuoi, 
        P.DinhDangPhim, 
        P.NgayKhoiChieu, 
        P.NgayKetThuc, 
        P.TrangThaiChieu, 
        P.ThoiLuong;
END;
Go
CREATE PROCEDURE pr_LayThongTinRapChieuCha
AS
BEGIN
    DECLARE @MaRapChieu INT;
    DECLARE @AnhRapChieu VARCHAR(MAX);
    DECLARE @TenRapChieu NVARCHAR(255);
    DECLARE @MoTaRapChieu NVARCHAR(255);
    DECLARE @DiemDanhGiaRapChieuTrungBinh FLOAT;
    DECLARE @TongLuotDanhGia INT;
    DECLARE @TongDiaDiemRap INT;

    -- Tạo bảng tạm để lưu trữ kết quả
    CREATE TABLE #RapChieuResult (
        AnhRapChieu VARCHAR(MAX),
        TenRapChieu NVARCHAR(255),
        MoTaRapChieu NVARCHAR(255),
        DiemDanhGiaRapChieuTrungBinh FLOAT,
        TongLuotDanhGia INT,
        TongDiaDiemRap INT
    );

    -- Khai báo cursor để lấy tất cả các MaRapChieu
    DECLARE RapChieuCursor CURSOR FOR
    SELECT MaRapChieu
    FROM RapChieu;

    -- Mở cursor
    OPEN RapChieuCursor;
    
    -- Lặp qua từng MaRapChieu
    FETCH NEXT FROM RapChieuCursor INTO @MaRapChieu;
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Lấy các thông tin cần thiết cho rạp chiếu
        SELECT @AnhRapChieu = AnhRapChieu,
               @TenRapChieu = TenRapChieu,
               @MoTaRapChieu = MoTaRapChieu
        FROM RapChieu
        WHERE MaRapChieu = @MaRapChieu;

        -- Gọi các function để tính toán các giá trị cần thiết
        SET @DiemDanhGiaRapChieuTrungBinh = dbo.fn_DiemDanhGiaTrungBinhRapChieuCha(@MaRapChieu);
        SET @TongLuotDanhGia = dbo.fn_TongLuotDanhGiaRapChieuCha(@MaRapChieu);
        SET @TongDiaDiemRap = dbo.fn_TongSoDiaDiemRapChieuCha(@MaRapChieu);

        -- Chèn kết quả vào bảng tạm
        INSERT INTO #RapChieuResult (AnhRapChieu, TenRapChieu, MoTaRapChieu, DiemDanhGiaRapChieuTrungBinh, TongLuotDanhGia, TongDiaDiemRap)
        VALUES (@AnhRapChieu, @TenRapChieu, @MoTaRapChieu, @DiemDanhGiaRapChieuTrungBinh, @TongLuotDanhGia, @TongDiaDiemRap);

        -- Lấy MaRapChieu tiếp theo
        FETCH NEXT FROM RapChieuCursor INTO @MaRapChieu;
    END

    -- Đóng và giải phóng cursor
    CLOSE RapChieuCursor;
    DEALLOCATE RapChieuCursor;

    -- Trả về kết quả từ bảng tạm
    SELECT * FROM #RapChieuResult;

    -- Xóa bảng tạm
    DROP TABLE #RapChieuResult;
END;

EXEC pr_LayThongTinRapChieuCha;
GO
CREATE PROCEDURE pr_LayThongTinPhimXepHangTheoNgay
AS
BEGIN
    SET NOCOUNT ON;

    -- Lấy thông tin phim và xếp hạng theo ngày hiện tại
    SELECT 
		Phim.MaPhim,
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        STRING_AGG(TheLoaiCha.TenTheLoai, ', ') AS TenTheLoai,  -- Nối tên thể loại từ TheLoaiCha
        Phim.DinhDangPhim,
        dbo.fn_DiemDanhGiaPhimTrungBinhTheoNgay(Phim.MaPhim) AS DiemDanhGiaTrungBinh,
        dbo.fn_TongLuotMuaPhimTheoNgay(Phim.MaPhim) AS TongLuotMuaPhim,
        dbo.fn_TongLuotDanhGiaPhimTheoNgay(Phim.MaPhim) AS TongDanhGiaPhim,
        -- Tính điểm tổng hợp xếp hạng: ưu tiên điểm đánh giá và lượt mua
        ISNULL(dbo.fn_DiemDanhGiaPhimTrungBinhTheoNgay(Phim.MaPhim), 0) * 0.7 +
        ISNULL(dbo.fn_TongLuotMuaPhimTheoNgay(Phim.MaPhim), 0) * 0.3 AS DiemXepHang
    FROM 
        Phim
    JOIN 
        TheLoai ON Phim.MaPhim = TheLoai.MaPhim  -- JOIN với bảng TheLoai
    JOIN
        TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha  -- JOIN với bảng TheLoaiCha
    WHERE 
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND CAST(ChiTietLichChieu.NgayChieu AS DATE) = CAST(GETDATE() AS DATE)
        )
    GROUP BY 
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        Phim.DinhDangPhim,
        Phim.MaPhim
    ORDER BY 
        DiemXepHang DESC;
END;

EXEC pr_LayThongTinPhimXepHangTheoNgay;
GO
CREATE PROCEDURE pr_LayThongTinPhimXepHangTheoTuan
AS
BEGIN
    SET NOCOUNT ON;

    -- Lấy thông tin phim và xếp hạng theo tuần hiện tại
    SELECT 
		Phim.MaPhim,
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        STRING_AGG(TheLoaiCha.TenTheLoai, ', ') AS TenTheLoai,  -- Lấy tên thể loại từ bảng TheLoaiCha
        Phim.DinhDangPhim,
        dbo.fn_DiemDanhGiaPhimTrungBinhTheoTuan(Phim.MaPhim) AS DiemDanhGiaTrungBinh,
        dbo.fn_TongLuotMuaPhimTheoTuan(Phim.MaPhim) AS TongLuotMuaPhim,
        dbo.fn_TongLuotDanhGiaPhimTheoTuan(Phim.MaPhim) AS TongDanhGiaPhim,
        -- Tính điểm tổng hợp xếp hạng: ưu tiên điểm đánh giá và lượt mua
        ISNULL(dbo.fn_DiemDanhGiaPhimTrungBinhTheoTuan(Phim.MaPhim), 0) * 0.7 +
        ISNULL(dbo.fn_TongLuotMuaPhimTheoTuan(Phim.MaPhim), 0) * 0.3 AS DiemXepHang
    FROM 
        Phim
    JOIN 
        TheLoai ON Phim.MaPhim = TheLoai.MaPhim  -- Kết nối với bảng TheLoai
    JOIN 
        TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha  -- Kết nối với bảng TheLoaiCha để lấy tên thể loại cha
    WHERE 
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND DATEPART(ISO_WEEK, ChiTietLichChieu.NgayChieu) = DATEPART(ISO_WEEK, GETDATE())
            AND YEAR(ChiTietLichChieu.NgayChieu) = YEAR(GETDATE())
        )
    AND Phim.TrangThaiChieu = N'Đang chiếu'  -- Chỉ lấy phim đang chiếu
    GROUP BY 
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        Phim.DinhDangPhim,
        Phim.MaPhim
    ORDER BY 
        DiemXepHang DESC;
END;
EXEC pr_LayThongTinPhimXepHangTheoTuan;
GO
CREATE PROCEDURE pr_LayThongTinPhimXepHangTheoThang
AS
BEGIN
    SET NOCOUNT ON;

    -- Lấy thông tin phim và xếp hạng theo tháng hiện tại
    SELECT 
		Phim.MaPhim,
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        STRING_AGG(TheLoaiCha.TenTheLoai, ', ') AS TenTheLoai,  -- Lấy tên thể loại từ bảng TheLoaiCha
        Phim.DinhDangPhim,
        dbo.fn_DiemDanhGiaPhimTrungBinhTheoThang(Phim.MaPhim) AS DiemDanhGiaTrungBinh,
        dbo.fn_TongLuotMuaPhimTheoThang(Phim.MaPhim) AS TongLuotMuaPhim,
        dbo.fn_TongLuotDanhGiaPhimTheoThang(Phim.MaPhim) AS TongDanhGiaPhim,
        -- Tính điểm tổng hợp xếp hạng: ưu tiên điểm đánh giá và lượt mua
        ISNULL(dbo.fn_DiemDanhGiaPhimTrungBinhTheoThang(Phim.MaPhim), 0) * 0.7 +
        ISNULL(dbo.fn_TongLuotMuaPhimTheoThang(Phim.MaPhim), 0) * 0.3 AS DiemXepHang
    FROM 
        Phim
    JOIN 
        TheLoai ON Phim.MaPhim = TheLoai.MaPhim  -- Kết nối với bảng TheLoai
    JOIN 
        TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha  -- Kết nối với bảng TheLoaiCha để lấy tên thể loại cha
    WHERE 
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND MONTH(ChiTietLichChieu.NgayChieu) = MONTH(GETDATE())
            AND YEAR(ChiTietLichChieu.NgayChieu) = YEAR(GETDATE())
        )
    AND Phim.TrangThaiChieu = N'Đang chiếu'  -- Chỉ lấy phim đang chiếu
    GROUP BY 
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        Phim.DinhDangPhim,
        Phim.MaPhim
    ORDER BY 
        DiemXepHang DESC;
END;
EXEC pr_LayThongTinPhimXepHangTheoThang;
GO
CREATE PROCEDURE pr_BinhLuanNoiBat
AS
BEGIN
    SET NOCOUNT ON;

    ;WITH BinhLuanXepHang AS (
        SELECT 
            Phim.MaPhim,
            Phim.AnhPhim,
            Phim.TenPhim,
			Phim.Tuoi,
            -- Sử dụng FOR XML PATH để nối tên thể loại với thể loại cha, ngăn cách bằng dấu chấm phẩy
            STUFF((
                SELECT '; ' + TheLoaiCha.TenTheLoai
                FROM TheLoai
                JOIN TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha
                WHERE TheLoai.MaPhim = Phim.MaPhim
                FOR XML PATH('')
            ), 1, 2, '') AS TenTheLoai,  -- Xóa bỏ dấu chấm phẩy đầu tiên
            dbo.fn_DiemDanhGiaPhimTrungBinh(Phim.MaPhim) AS DiemDanhGiaTrungBinh,
            dbo.fn_TongLuotMuaPhim(Phim.MaPhim) AS TongLuotMuaPhim,
            dbo.fn_TongLuotDanhGiaPhim(Phim.MaPhim) AS TongLuotDanhGiaPhim,
			KhachHang.AnhKhachHang,
            KhachHang.TenKhachHang,
            FORMAT(DanhGia.NgayDanhGia, 'dd/MM/yyyy HH:mm') AS NgayDanhGia,
            DanhGia.DanhGia,
            DanhGia.LuotThich,
            ROW_NUMBER() OVER (PARTITION BY Phim.MaPhim ORDER BY DanhGia.LuotThich DESC) AS RowNum  -- Đánh số các bình luận theo lượt thích
        FROM 
            DanhGia
        JOIN 
            Phim ON DanhGia.MaPhim = Phim.MaPhim  -- JOIN với bảng Phim
        JOIN 
            KhachHang ON DanhGia.MaKhachHang = KhachHang.MaKhachHang  -- JOIN với bảng KhachHang
        JOIN 
            TheLoai ON Phim.MaPhim = TheLoai.MaPhim  -- JOIN với bảng TheLoai
        JOIN 
            TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha  -- JOIN với bảng TheLoaiCha
        WHERE 
            DanhGia.DiemDanhGia IS NOT NULL  -- Chỉ lấy những bình luận có điểm đánh giá
    )
    
    -- Chỉ lấy bình luận đầu tiên theo lượt thích giảm dần cho mỗi MaPhim
    SELECT 
        MaPhim,
        AnhPhim,
        TenPhim,
		Tuoi,
        TenTheLoai,
        DiemDanhGiaTrungBinh,
        TongLuotMuaPhim,
        TongLuotDanhGiaPhim,
		AnhKhachHang,
        TenKhachHang,
        NgayDanhGia,
        DanhGia,
        LuotThich
    FROM 
        BinhLuanXepHang
    WHERE 
        RowNum = 1  -- Chỉ lấy bình luận đầu tiên (theo lượt thích giảm dần)
    ORDER BY 
        LuotThich DESC;  -- Sắp xếp lại theo lượt thích giảm dần
END;
GO
exec pr_BinhLuanNoiBat;
drop procedure pr_BinhLuanNoiBat;
GO
CREATE PROCEDURE pr_TimKiemPhim
    @TenPhim NVARCHAR(255)  -- Đầu vào tên phim cần tìm kiếm
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        P.MaPhim,
        P.AnhPhim,
        P.TenPhim,
        P.Tuoi,
        P.DinhDangPhim,
        STRING_AGG(TC.TenTheLoai, ', ') AS TenTheLoai,
        -- Chuyển đổi NgayKhoiChieu và NgayKetThuc sang định dạng dd/MM/yyyy
        FORMAT(P.NgayKhoiChieu, 'dd/MM/yyyy') AS NgayKhoiChieu,
        FORMAT(P.NgayKetThuc, 'dd/MM/yyyy') AS NgayKetThuc,
        P.TrangThaiChieu,
        -- Chuyển đổi ThoiLuong thành định dạng HH:mm:ss
        RIGHT('00' + CAST(DATEPART(HOUR, P.ThoiLuong) AS VARCHAR), 2) + ':' +
        RIGHT('00' + CAST(DATEPART(MINUTE, P.ThoiLuong) AS VARCHAR), 2) + ':' +
        RIGHT('00' + CAST(DATEPART(SECOND, P.ThoiLuong) AS VARCHAR), 2) AS ThoiLuong,
        -- Gọi hàm fn_DiemDanhGiaPhimTrungBinh để tính điểm trung bình
        dbo.fn_DiemDanhGiaPhimTrungBinh(P.MaPhim) AS DiemDanhGiaTrungBinh,
		dbo.fn_TongLuotMuaPhim(P.MaPhim) AS TongLuotMuaPhim,
		dbo.fn_TongLuotDanhGiaPhim(P.MaPhim) AS TongLuotDanhGiaPhim

    FROM 
        Phim P
    LEFT JOIN 
        TheLoai TL ON P.MaPhim = TL.MaPhim
    LEFT JOIN 
        TheLoaiCha TC ON TL.MaTheLoaiCha = TC.MaTheLoaiCha
    WHERE 
        P.TenPhim LIKE N'%' + @TenPhim + N'%'  
    GROUP BY 
        P.MaPhim, 
        P.AnhPhim, 
        P.TenPhim, 
        P.Tuoi, 
        P.DinhDangPhim, 
        P.NgayKhoiChieu, 
        P.NgayKetThuc, 
        P.TrangThaiChieu, 
        P.ThoiLuong;
END;
GO
CREATE PROCEDURE pr_LayTatCaPhim
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        P.MaPhim,
        P.AnhPhim,
        P.TenPhim,
        P.Tuoi,
        P.DinhDangPhim,
        STRING_AGG(TC.TenTheLoai, ', ') AS TenTheLoai,
        -- Chuyển đổi NgayKhoiChieu và NgayKetThuc sang định dạng dd/MM/yyyy
        FORMAT(P.NgayKhoiChieu, 'dd/MM/yyyy') AS NgayKhoiChieu,
        FORMAT(P.NgayKetThuc, 'dd/MM/yyyy') AS NgayKetThuc,
        P.TrangThaiChieu,
        -- Chuyển đổi ThoiLuong thành định dạng HH:mm:ss
        RIGHT('00' + CAST(DATEPART(HOUR, P.ThoiLuong) AS VARCHAR), 2) + ':' +
        RIGHT('00' + CAST(DATEPART(MINUTE, P.ThoiLuong) AS VARCHAR), 2) + ':' +
        RIGHT('00' + CAST(DATEPART(SECOND, P.ThoiLuong) AS VARCHAR), 2) AS ThoiLuong,
        -- Gọi hàm fn_DiemDanhGiaPhimTrungBinh để tính điểm trung bình
        dbo.fn_DiemDanhGiaPhimTrungBinh(P.MaPhim) AS DiemDanhGiaTrungBinh,
		dbo.fn_TongLuotMuaPhim(P.MaPhim) AS TongLuotMuaPhim,
		dbo.fn_TongLuotDanhGiaPhim(P.MaPhim) AS TongLuotDanhGiaPhim

    FROM 
        Phim P
    LEFT JOIN 
        TheLoai TL ON P.MaPhim = TL.MaPhim
    LEFT JOIN 
        TheLoaiCha TC ON TL.MaTheLoaiCha = TC.MaTheLoaiCha
    GROUP BY 
        P.MaPhim, 
        P.AnhPhim, 
        P.TenPhim, 
        P.Tuoi, 
        P.DinhDangPhim, 
        P.NgayKhoiChieu, 
        P.NgayKetThuc, 
        P.TrangThaiChieu, 
        P.ThoiLuong;
END;
GO
EXEC pr_LayTatCaPhim;
-- END--


GO
CREATE PROCEDURE pr_GetRapChieuCon
    @MaTinhThanh INT,
    @MaRapChieu INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Truy vấn dữ liệu với MaTinhThanh và MaRapChieu
    SELECT 
        RCon.MaRapChieuCon,
        RC.AnhRapChieu, 
        RCon.TenRapChieuCon, 
        DRC.DiaChiRapChieu
    FROM 
        TinhThanhPho TTP
    INNER JOIN 
        DiaChiRapChieuCon DRC ON TTP.MaTinhThanh = DRC.MaTinhThanh
    INNER JOIN 
        RapChieuCon RCon ON DRC.MaRapChieuCon = RCon.MaRapChieuCon
    INNER JOIN 
        RapChieu RC ON RCon.MaRapChieu = RC.MaRapChieu
    WHERE 
        TTP.MaTinhThanh = @MaTinhThanh
        AND RCon.MaRapChieu = @MaRapChieu;
END;
EXEC pr_GetRapChieuCon @MaTinhThanh = 1,  @MaRapChieu = 1;
GO
CREATE PROCEDURE pr_GetRapChieuConByMaRapChieuCon
    @MaRapChieuCon INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Truy vấn dữ liệu với MaRapChieuCon đã cung cấp
    SELECT 
        RCon.MaRapChieuCon,
        RC.AnhRapChieu, 
        RCon.TenRapChieuCon, 
        DRC.DiaChiRapChieu
    FROM 
        DiaChiRapChieuCon DRC
    INNER JOIN 
        RapChieuCon RCon ON DRC.MaRapChieuCon = RCon.MaRapChieuCon
    INNER JOIN 
        RapChieu RC ON RCon.MaRapChieu = RC.MaRapChieu
    WHERE 
        RCon.MaRapChieuCon = @MaRapChieuCon;
END;

EXEC pr_GetRapChieuConByMaRapChieuCon 1;
GO
CREATE PROCEDURE pr_LayThongTinPhimTheoNgayChieuRapChieuCon
    @TenRapChieuCon NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    -- Tạo một cursor hoặc bảng tạm để lấy tất cả các MaPhim và NgayChieu từ bảng ChiTietLichChieu
    DECLARE @MaPhim INT;
    DECLARE @NgayChieu DATETIME;

    DECLARE PhimCursor CURSOR FOR
    SELECT DISTINCT LichChieu.MaPhim, ChiTietLichChieu.NgayChieu
    FROM LichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
    JOIN RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    WHERE RapChieuCon.TenRapChieuCon = @TenRapChieuCon;

    OPEN PhimCursor;
    FETCH NEXT FROM PhimCursor INTO @MaPhim, @NgayChieu;

    -- Duyệt qua từng MaPhim và lấy thông tin phim
    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Truy vấn thông tin cho từng MaPhim
        SELECT 
            Phim.AnhPhim,
            Phim.TenPhim,
            Phim.Tuoi,
            STRING_AGG(TheLoai.TenTheLoai, ', ') AS TenTheLoai, -- Kết hợp tên thể loại với dấu phẩy
            Phim.DinhDangPhim,
            dbo.fn_DiemDanhGiaTrungBinhTheoNgayChieuRapChieuCon(@MaPhim, @NgayChieu, @TenRapChieuCon) AS DiemDanhGiaTrungBinh,
            dbo.fn_TongLuotMuaPhimTrungBinhTheoNgayChieuRapChieuCon(@MaPhim, @NgayChieu, @TenRapChieuCon) AS TongLuotMuaPhim,
            dbo.fn_TongLuotDanhGiaPhimTheoNgayChieuRapChieuCon(@MaPhim, @NgayChieu, @TenRapChieuCon) AS TongDanhGiaPhim
        FROM 
            Phim
        JOIN 
            TheLoai ON Phim.MaTheLoai = TheLoai.MaTheLoai
        WHERE 
            Phim.MaPhim = @MaPhim
        GROUP BY 
            Phim.AnhPhim,
            Phim.TenPhim,
            Phim.Tuoi,
            Phim.DinhDangPhim;

        FETCH NEXT FROM PhimCursor INTO @MaPhim, @NgayChieu;
    END

    CLOSE PhimCursor;
    DEALLOCATE PhimCursor;
END;

GO
CREATE PROCEDURE pr_LayThoiGianChieuPhim
    @MaPhim INT,
    @NgayChieu DATETIME,
    @TenRapChieuCon NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        ChiTietLichChieu.ThoiGianBatDau,
        ChiTietLichChieu.ThoiGianKetThuc
    FROM 
        ChiTietLichChieu
    JOIN 
        LichChieu ON ChiTietLichChieu.MaLichChieu = LichChieu.MaLichChieu
    JOIN 
        RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    WHERE 
        LichChieu.MaPhim = @MaPhim
        AND ChiTietLichChieu.NgayChieu = @NgayChieu
        AND RapChieuCon.TenRapChieuCon = @TenRapChieuCon;
END;
GO
