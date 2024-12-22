use dbQuanLyXemPhim
GO
-- Đã sử dụng
CREATE OR ALTER PROCEDURE pr_LayPhimDangChieu
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
CREATE OR ALTER PROCEDURE pr_LayPhimSapChieu
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
CREATE OR ALTER PROCEDURE pr_LayThongTinRapChieuCha
AS
BEGIN
    SET NOCOUNT ON;

    -- Bảng tạm để lưu trữ kết quả tính toán
    DECLARE @RapChieuTongHop TABLE (
        MaRapChieu INT,
        DiemDanhGiaRapChieuTrungBinh FLOAT,
        TongLuotDanhGia INT,
        TongDiaDiemRap INT
    );

    -- Tính toán và lưu kết quả vào bảng tạm
    INSERT INTO @RapChieuTongHop
    SELECT 
        r.MaRapChieu,
        dbo.fn_DiemDanhGiaTrungBinhRapChieuCha(r.MaRapChieu) AS DiemDanhGiaRapChieuTrungBinh,
        dbo.fn_TongLuotDanhGiaRapChieuCha(r.MaRapChieu) AS TongLuotDanhGia,
        dbo.fn_TongSoDiaDiemRapChieuCha(r.MaRapChieu) AS TongDiaDiemRap
    FROM 
        RapChieu r;

    -- Trả về kết quả từ bảng tạm đã tính toán
    SELECT 
        r.AnhRapChieu,
        r.TenRapChieu,
        r.MoTaRapChieu,
        t.DiemDanhGiaRapChieuTrungBinh,
        t.TongLuotDanhGia,
        t.TongDiaDiemRap
    FROM 
        RapChieu r
    INNER JOIN 
        @RapChieuTongHop t ON r.MaRapChieu = t.MaRapChieu
    ORDER BY 
        r.MaRapChieu; -- Sắp xếp theo MaRapChieu nếu cần thiết
END;

EXEC pr_LayThongTinRapChieuCha;
GO
CREATE OR ALTER PROCEDURE pr_LayThongTinPhimXepHangTheoNgay
AS
BEGIN
    SET NOCOUNT ON;

    -- CTE để tính toán các giá trị cần thiết
    WITH FilmRatings AS (
        SELECT 
            Phim.MaPhim,
            dbo.fn_DiemDanhGiaPhimTrungBinhTheoNgay(Phim.MaPhim) AS DiemDanhGiaTrungBinh,
            dbo.fn_TongLuotMuaPhimTheoNgay(Phim.MaPhim) AS TongLuotMuaPhim,
            dbo.fn_TongLuotDanhGiaPhimTheoNgay(Phim.MaPhim) AS TongDanhGiaPhim
        FROM 
            Phim
    )
    -- Lấy thông tin phim và xếp hạng theo ngày hiện tại
    SELECT DISTINCT
        Phim.MaPhim,
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        STUFF((
            SELECT '; ' + TheLoaiCha.TenTheLoai
            FROM TheLoai
            JOIN TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha
            WHERE TheLoai.MaPhim = Phim.MaPhim
            FOR XML PATH('')
        ), 1, 2, '') AS TenTheLoai,  -- Nối tên thể loại từ TheLoaiCha
        Phim.DinhDangPhim,
        FR.DiemDanhGiaTrungBinh,
        FR.TongLuotMuaPhim,
        FR.TongDanhGiaPhim,
        -- Tính điểm tổng hợp xếp hạng: ưu tiên điểm đánh giá và lượt mua
        ISNULL(FR.DiemDanhGiaTrungBinh, 0) * 0.7 +
        ISNULL(FR.TongLuotMuaPhim, 0) * 0.3 AS DiemXepHang
    FROM 
        Phim
    INNER JOIN 
        FilmRatings FR ON Phim.MaPhim = FR.MaPhim  -- Kết hợp với CTE
    LEFT JOIN 
        TheLoai ON Phim.MaPhim = TheLoai.MaPhim  -- LEFT JOIN với bảng TheLoai để lấy thể loại
    LEFT JOIN
        TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha  -- LEFT JOIN với bảng TheLoaiCha để lấy tên thể loại
    WHERE 
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND CAST(ThoiGianChieu.NgayChieu AS DATE) = CAST(GETDATE() AS DATE)  -- Chỉ lấy phim có lịch chiếu ngày hôm nay
        )
    ORDER BY 
        DiemXepHang DESC; -- Xếp hạng phim theo điểm
END;

EXEC pr_LayThongTinPhimXepHangTheoNgay;
GO
CREATE OR ALTER PROCEDURE pr_LayThongTinPhimXepHangTheoTuan
AS
BEGIN
    SET NOCOUNT ON;

    -- CTE tính toán điểm đánh giá trung bình, tổng lượt mua và tổng lượt đánh giá
    WITH FilmRatings AS (
        SELECT 
            Phim.MaPhim,
            dbo.fn_DiemDanhGiaPhimTrungBinhTheoTuan(Phim.MaPhim) AS DiemDanhGiaTrungBinh,
            dbo.fn_TongLuotMuaPhimTheoTuan(Phim.MaPhim) AS TongLuotMuaPhim,
            dbo.fn_TongLuotDanhGiaPhimTheoTuan(Phim.MaPhim) AS TongDanhGiaPhim
        FROM 
            Phim
    )
    -- Lấy thông tin phim và xếp hạng theo tuần hiện tại
    SELECT DISTINCT
        Phim.MaPhim,
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        STUFF((
            SELECT '; ' + TheLoaiCha.TenTheLoai
            FROM TheLoai
            JOIN TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha
            WHERE TheLoai.MaPhim = Phim.MaPhim
            FOR XML PATH('')
        ), 1, 2, '') AS TenTheLoai,  -- Nối tên thể loại từ TheLoaiCha
        Phim.DinhDangPhim,
        FR.DiemDanhGiaTrungBinh,
        FR.TongLuotMuaPhim,
        FR.TongDanhGiaPhim,
        -- Tính điểm tổng hợp xếp hạng: ưu tiên điểm đánh giá và lượt mua
        ISNULL(FR.DiemDanhGiaTrungBinh, 0) * 0.7 +
        ISNULL(FR.TongLuotMuaPhim, 0) * 0.3 AS DiemXepHang
    FROM 
        Phim
    INNER JOIN 
        FilmRatings FR ON Phim.MaPhim = FR.MaPhim  -- Kết hợp với CTE
    LEFT JOIN 
        TheLoai ON Phim.MaPhim = TheLoai.MaPhim  -- LEFT JOIN với bảng TheLoai để lấy thể loại
    LEFT JOIN
        TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha  -- LEFT JOIN với bảng TheLoaiCha để lấy tên thể loại
    WHERE 
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND DATEPART(ISO_WEEK, ThoiGianChieu.NgayChieu) = DATEPART(ISO_WEEK, GETDATE())
            AND YEAR(ThoiGianChieu.NgayChieu) = YEAR(GETDATE())
        )
    AND Phim.TrangThaiChieu = N'Đang chiếu'  -- Chỉ lấy phim đang chiếu
    ORDER BY 
        DiemXepHang DESC; -- Xếp hạng phim theo điểm
END;

EXEC pr_LayThongTinPhimXepHangTheoTuan;
GO
CREATE OR ALTER PROCEDURE pr_LayThongTinPhimXepHangTheoThang
AS
BEGIN
    SET NOCOUNT ON;

    -- CTE tính toán điểm đánh giá trung bình, tổng lượt mua và tổng lượt đánh giá
    WITH FilmRatings AS (
        SELECT DISTINCT 
            Phim.MaPhim,
            dbo.fn_DiemDanhGiaPhimTrungBinhTheoThang(Phim.MaPhim) AS DiemDanhGiaTrungBinh,
            dbo.fn_TongLuotMuaPhimTheoThang(Phim.MaPhim) AS TongLuotMuaPhim,
            dbo.fn_TongLuotDanhGiaPhimTheoThang(Phim.MaPhim) AS TongDanhGiaPhim
        FROM 
            Phim
    )
    -- Lấy thông tin phim và xếp hạng theo tháng hiện tại
    SELECT DISTINCT 
        Phim.MaPhim,
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        STUFF((
            SELECT '; ' + TheLoaiCha.TenTheLoai
            FROM TheLoai
            JOIN TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha
            WHERE TheLoai.MaPhim = Phim.MaPhim
            FOR XML PATH('')
        ), 1, 2, '') AS TenTheLoai,  -- Nối tên thể loại từ TheLoaiCha
        Phim.DinhDangPhim,
        FR.DiemDanhGiaTrungBinh,
        FR.TongLuotMuaPhim,
        FR.TongDanhGiaPhim,
        -- Tính điểm tổng hợp xếp hạng: ưu tiên điểm đánh giá và lượt mua
        ISNULL(FR.DiemDanhGiaTrungBinh, 0) * 0.7 +
        ISNULL(FR.TongLuotMuaPhim, 0) * 0.3 AS DiemXepHang
    FROM 
        Phim
    INNER JOIN 
        FilmRatings FR ON Phim.MaPhim = FR.MaPhim  -- Kết hợp với CTE
    LEFT JOIN 
        TheLoai ON Phim.MaPhim = TheLoai.MaPhim  -- LEFT JOIN với bảng TheLoai để lấy thể loại
    LEFT JOIN
        TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha  -- LEFT JOIN với bảng TheLoaiCha để lấy tên thể loại
    WHERE 
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND MONTH(ThoiGianChieu.NgayChieu) = MONTH(GETDATE())  -- Lọc theo tháng hiện tại
            AND YEAR(ThoiGianChieu.NgayChieu) = YEAR(GETDATE())   -- Lọc theo năm hiện tại
        )
    AND Phim.TrangThaiChieu = N'Đang chiếu'  -- Chỉ lấy phim đang chiếu
    ORDER BY 
        DiemXepHang DESC; -- Xếp hạng phim theo điểm
END;

EXEC pr_LayThongTinPhimXepHangTheoThang;
GO
CREATE OR ALTER PROCEDURE pr_BinhLuanNoiBat
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
GO
CREATE OR ALTER PROCEDURE pr_TimKiemPhim
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
CREATE OR ALTER PROCEDURE pr_LayTatCaPhim
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
GO
CREATE OR ALTER PROCEDURE pr_GetRapChieuCon
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
        DRC.DiaChiRapChieu,
        DRC.map
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
CREATE OR ALTER PROCEDURE pr_GetRapChieuConByTenRapChieu
    @MaTinhThanh INT,
    @MaRapChieu INT,
    @TenRapChieuCon NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    -- Kiểm tra tham số đầu vào để tránh lỗi
    IF @MaTinhThanh IS NULL OR @MaRapChieu IS NULL
    BEGIN
        RAISERROR ('MaTinhThanh và MaRapChieu không được NULL', 16, 1);
        RETURN;
    END

    -- Truy vấn dữ liệu
    SELECT 
        RCon.MaRapChieuCon,
        RC.AnhRapChieu, 
        RCon.TenRapChieuCon, 
        DRC.DiaChiRapChieu,
        DRC.map
    FROM 
        TinhThanhPho AS TTP
    INNER JOIN 
        DiaChiRapChieuCon AS DRC ON TTP.MaTinhThanh = DRC.MaTinhThanh
    INNER JOIN 
        RapChieuCon AS RCon ON DRC.MaRapChieuCon = RCon.MaRapChieuCon
    INNER JOIN 
        RapChieu AS RC ON RCon.MaRapChieu = RC.MaRapChieu
    WHERE 
        TTP.MaTinhThanh = @MaTinhThanh
        AND RCon.MaRapChieu = @MaRapChieu
        AND (
            @TenRapChieuCon IS NULL 
            OR @TenRapChieuCon = '' 
            OR RCon.TenRapChieuCon LIKE N'%' + @TenRapChieuCon + N'%'
        ); -- Nếu @TenRapChieuCon NULL hoặc rỗng, không lọc theo tên
END;

EXEC pr_GetRapChieuConByTenRapChieu @MaTinhThanh = 1,  @MaRapChieu = 1, @TenRapChieuCon = N'Rạp Chiếu';
GO
CREATE OR ALTER PROCEDURE pr_Get7NgayChieuFromToday
AS
BEGIN
    SET NOCOUNT ON;

    -- Lấy 7 ngày chiếu tiếp theo bắt đầu từ ngày hôm nay
    SELECT TOP 7 
        MaThoiGianChieu,
        KieuNgay, 
        CONVERT(DATE, NgayChieu) AS NgayChieu -- Trích xuất ngày
    FROM ThoiGianChieu
    WHERE NgayChieu >= CAST(GETDATE() AS DATE) -- Chỉ lấy từ ngày hôm nay trở đi
    ORDER BY NgayChieu ASC; -- Sắp xếp theo NgàyChieu tăng dần
END;

EXEC pr_Get7NgayChieuFromToday
Go
CREATE OR ALTER PROCEDURE pr_GetRapChieuConByMaRapChieuCon
    @MaRapChieuCon INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Truy vấn dữ liệu với MaRapChieuCon đã cung cấp
    SELECT 
        RCon.MaRapChieuCon,
        RC.AnhRapChieu, 
        RCon.TenRapChieuCon, 
        DRC.DiaChiRapChieu,
        DRC.map
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
CREATE OR ALTER PROCEDURE pr_LayThongTinPhimTheoNgayChieuRapChieuCon
    @MaRapChieuCon INT,
    @MaThoiGianChieu INT
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @NgayChieu VARCHAR(10);

    -- Lấy ngày chiếu từ bảng ThoiGianChieu
    SELECT @NgayChieu = CONVERT(VARCHAR(10), ThoiGianChieu.NgayChieu, 103) 
    FROM ThoiGianChieu
    WHERE MaThoiGianChieu = @MaThoiGianChieu;
    
    -- Truy vấn thông tin phim không sử dụng bảng DanhGiaPhim
    SELECT 
        Phim.MaPhim,
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        STUFF((
            SELECT '; ' + TheLoaiCha.TenTheLoai
            FROM TheLoai
            JOIN TheLoaiCha ON TheLoai.MaTheLoaiCha = TheLoaiCha.MaTheLoaiCha
            WHERE TheLoai.MaPhim = Phim.MaPhim
            FOR XML PATH('')
        ), 1, 2, '') AS TenTheLoai, 
        Phim.DinhDangPhim,
        
        -- Các tính toán liên quan đến điểm đánh giá và lượt mua
        dbo.fn_DiemDanhGiaTrungBinhTheoNgayChieuRapChieuCon(Phim.MaPhim, @NgayChieu, @MaRapChieuCon) AS DiemDanhGiaTrungBinh,
        dbo.fn_TongLuotMuaPhimTheoNgayRapChieuCon(Phim.MaPhim, @NgayChieu, @MaRapChieuCon) AS TongLuotMuaPhim,
        dbo.fn_TongLuotDanhGiaPhimTheoNgayChieuRapChieuCon(Phim.MaPhim, @NgayChieu, @MaRapChieuCon) AS TongDanhGiaPhim,
        
        -- Tính điểm tổng hợp xếp hạng: ưu tiên điểm đánh giá và lượt mua
        ISNULL(dbo.fn_DiemDanhGiaTrungBinhTheoNgayChieuRapChieuCon(Phim.MaPhim, @NgayChieu, @MaRapChieuCon), 0) * 0.7 +
        ISNULL(dbo.fn_TongLuotMuaPhimTheoNgayRapChieuCon(Phim.MaPhim, @NgayChieu, @MaRapChieuCon), 0) * 0.3 AS DiemXepHang

    FROM 
        Phim
    JOIN 
        TheLoai ON Phim.MaPhim = TheLoai.MaPhim
    JOIN 
        TheLoaiCha ON TheLoaiCha.MaTheLoaiCha = TheLoai.MaTheLoaiCha
    JOIN 
        LichChieu ON LichChieu.MaPhim = Phim.MaPhim
    JOIN 
        ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
    JOIN 
        ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
    JOIN 
        RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    WHERE 
        RapChieuCon.MaRapChieuCon = @MaRapChieuCon
        AND ChiTietLichChieu.MaThoiGianChieu = @MaThoiGianChieu
    GROUP BY 
        Phim.MaPhim,
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        Phim.DinhDangPhim
    ORDER BY 
        DiemXepHang DESC;
END;

EXEC pr_LayThongTinPhimTheoNgayChieuRapChieuCon @MaRapChieuCon = 1, @MaThoiGianChieu = 7;
GO
CREATE OR ALTER PROCEDURE pr_LayThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon
    @MaPhim INT,
    @MaRapChieuCon INT,
    @MaThoiGianChieu INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Truy vấn lấy MaPhim, ThoiGianBatDau và ThoiGianKetThuc theo MaPhim và MaRapChieuCon với định dạng HH:mm
    SELECT 
        LichChieu.MaPhim,
        -- Sử dụng ISNULL để xử lý trường hợp không có thời gian
        CONVERT(VARCHAR(5), ISNULL(ChiTietLichChieu.ThoiGianBatDau, '00:00'), 108) AS ThoiGianBatDau, -- Định dạng HH:mm
        CONVERT(VARCHAR(5), ISNULL(ChiTietLichChieu.ThoiGianKetThuc, '00:00'), 108) AS ThoiGianKetThuc -- Định dạng HH:mm
    FROM 
        ChiTietLichChieu
    JOIN 
        LichChieu ON ChiTietLichChieu.MaLichChieu = LichChieu.MaLichChieu
    JOIN 
        ThoiGianChieu ON ChiTietLichChieu.MaThoiGianChieu = ThoiGianChieu.MaThoiGianChieu
    JOIN 
        RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    WHERE 
        LichChieu.MaPhim = @MaPhim
        AND LichChieu.MaRapChieuCon = @MaRapChieuCon
        AND ChiTietLichChieu.MaThoiGianChieu = @MaThoiGianChieu;
END;
EXEC pr_LayThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon @MaPhim = 2, @MaRapChieuCon = 1, @MaThoiGianChieu = 7;
-- END--