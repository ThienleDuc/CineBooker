use dbQuanLyXemPhim
go
CREATE PROCEDURE pr_GetPhimDangChieuInfo
AS
BEGIN
    SELECT 
        p.MaPhim,
        p.AnhPhim,
        p.TenPhim,
        p.Tuoi,
        tl.TenTheLoai AS TheLoai,
        dbo.fn_DiemDanhGiaPhimTrungBinhTheoNgay(p.MaPhim) AS DiemDanhGiaTrungBinh
    FROM 
        Phim p
    INNER JOIN 
        TheLoai tl ON p.MaTheLoai = tl.MaTheLoai
    INNER JOIN 
        LichChieu lc ON p.MaPhim = lc.MaPhim
    INNER JOIN 
        PhimDangChieu pdc ON lc.MaLichChieu = pdc.MaLichChieu
    ORDER BY 
        dbo.fn_DiemDanhGiaPhimTrungBinhTheoNgay(p.MaPhim) DESC;
END;
GO
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
        TL.TenTheLoai,
        P.NgayKhoiChieu,
        P.NgayKetThuc,
        P.TrangThaiChieu,
        P.ThoiLuong
    FROM 
        Phim P
    INNER JOIN 
        TheLoai TL ON P.MaTheLoai = TL.MaTheLoai
    WHERE 
        P.TrangThaiChieu = N'Đang chiếu';
END;
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
        TL.TenTheLoai,
        P.NgayKhoiChieu,
        P.NgayKetThuc,
        P.TrangThaiChieu,
        P.ThoiLuong
    FROM 
        Phim P
    INNER JOIN 
        TheLoai TL ON P.MaTheLoai = TL.MaTheLoai
    WHERE 
        P.TrangThaiChieu = N'Sắp chiếu';
END;
GO
CREATE PROCEDURE pr_GetRapChieuCon
    @TenTinhThanh NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
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
        TTP.TenTinhThanh = @TenTinhThanh;
END;
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
            TheLoai.TenTheLoai,
            Phim.DinhDangPhim,
            dbo.fn_DiemDanhGiaTrungBinhTheoNgayChieuRapChieuCon(@MaPhim, @NgayChieu, @TenRapChieuCon) AS DiemDanhGiaTrungBinh,
            dbo.fn_TongLuotMuaPhimTrungBinhTheoNgayChieuRapChieuCon(@MaPhim, @NgayChieu, @TenRapChieuCon) AS TongLuotMuaPhim,
            dbo.fn_TongLuotDanhGiaPhimTheoNgayChieuRapChieuCon(@MaPhim, @NgayChieu, @TenRapChieuCon) AS TongDanhGiaPhim
        FROM 
            Phim
        JOIN 
            TheLoai ON Phim.MaTheLoai = TheLoai.MaTheLoai
        WHERE 
            Phim.MaPhim = @MaPhim;

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

        -- Trả về kết quả cho mỗi rạp chiếu
        SELECT @AnhRapChieu AS AnhRapChieu,
               @TenRapChieu AS TenRapChieu,
               @MoTaRapChieu AS MoTaRapChieu,
               @DiemDanhGiaRapChieuTrungBinh AS DiemDanhGiaRapChieuTrungBinh,
               @TongLuotDanhGia AS TongLuotDanhGia,
               @TongDiaDiemRap AS TongDiaDiemRap;

        -- Lấy MaRapChieu tiếp theo
        FETCH NEXT FROM RapChieuCursor INTO @MaRapChieu;
    END

    -- Đóng và giải phóng cursor
    CLOSE RapChieuCursor;
    DEALLOCATE RapChieuCursor;
END;
GO
CREATE PROCEDURE pr_LayThongTinPhimXepHangTheoNgay
AS
BEGIN
    SET NOCOUNT ON;

    -- Lấy thông tin phim và xếp hạng theo ngày hiện tại
    SELECT 
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        TheLoai.TenTheLoai,
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
        TheLoai ON Phim.MaTheLoai = TheLoai.MaTheLoai
    WHERE 
        -- Điều kiện chỉ lấy các phim đang chiếu ngày hôm nay
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND CAST(ChiTietLichChieu.NgayChieu AS DATE) = CAST(GETDATE() AS DATE)
        )
    ORDER BY 
        -- Sắp xếp theo điểm xếp hạng giảm dần
        DiemXepHang DESC;
END;
GO
CREATE PROCEDURE pr_LayThongTinPhimXepHangTheoTuan
AS
BEGIN
    SET NOCOUNT ON;

    -- Lấy thông tin phim và xếp hạng theo tuần hiện tại
    SELECT 
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        TheLoai.TenTheLoai,
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
        TheLoai ON Phim.MaTheLoai = TheLoai.MaTheLoai
    WHERE 
        -- Điều kiện chỉ lấy các phim có lịch chiếu trong tuần hiện tại
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND DATEPART(ISO_WEEK, ChiTietLichChieu.NgayChieu) = DATEPART(ISO_WEEK, GETDATE())
            AND YEAR(ChiTietLichChieu.NgayChieu) = YEAR(GETDATE())
        )
    ORDER BY 
        -- Sắp xếp theo điểm xếp hạng giảm dần
        DiemXepHang DESC;
END;
GO
CREATE PROCEDURE pr_LayThongTinPhimXepHangTheoThang
AS
BEGIN
    SET NOCOUNT ON;

    -- Lấy thông tin phim và xếp hạng theo tháng hiện tại
    SELECT 
        Phim.AnhPhim,
        Phim.TenPhim,
        Phim.Tuoi,
        TheLoai.TenTheLoai,
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
        TheLoai ON Phim.MaTheLoai = TheLoai.MaTheLoai
    WHERE 
        -- Điều kiện chỉ lấy các phim có lịch chiếu trong tháng hiện tại
        EXISTS (
            SELECT 1 
            FROM LichChieu
            JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND MONTH(ChiTietLichChieu.NgayChieu) = MONTH(GETDATE())
            AND YEAR(ChiTietLichChieu.NgayChieu) = YEAR(GETDATE())
        )
    ORDER BY 
        -- Sắp xếp theo điểm xếp hạng giảm dần
        DiemXepHang DESC;
END;
GO
