USE [master]
GO
/****** Object:  Database [dbQuanLyXemPhim]    Script Date: 22/12/2024 22:41:06 ******/
CREATE DATABASE [dbQuanLyXemPhim]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'dbQuanLyXemPhim', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\dbQuanLyXemPhim.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'dbQuanLyXemPhim_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\dbQuanLyXemPhim_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [dbQuanLyXemPhim] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [dbQuanLyXemPhim].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [dbQuanLyXemPhim] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET ARITHABORT OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET  ENABLE_BROKER 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET RECOVERY FULL 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET  MULTI_USER 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [dbQuanLyXemPhim] SET DB_CHAINING OFF 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [dbQuanLyXemPhim] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'dbQuanLyXemPhim', N'ON'
GO
ALTER DATABASE [dbQuanLyXemPhim] SET QUERY_STORE = ON
GO
ALTER DATABASE [dbQuanLyXemPhim] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [dbQuanLyXemPhim]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_DiemDanhGiaPhimTrungBinh]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_DiemDanhGiaPhimTrungBinh] (@MaPhim INT)
RETURNS FLOAT
AS
BEGIN
    DECLARE @DiemTrungBinh FLOAT;

    -- Tính điểm trung bình từ bảng DanhGia cho bộ phim với MaPhim tương ứng
    SELECT @DiemTrungBinh = AVG(DiemDanhGia)
    FROM DanhGia
    WHERE MaPhim = @MaPhim;

    -- Trả về điểm trung bình
    RETURN @DiemTrungBinh;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_DiemDanhGiaPhimTrungBinhTheoNgay]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Đã sử dụng
CREATE FUNCTION [dbo].[fn_DiemDanhGiaPhimTrungBinhTheoNgay] (@MaPhim INT)
RETURNS FLOAT
AS
BEGIN
    DECLARE @DiemTrungBinh FLOAT;

    -- Tính điểm trung bình từ bảng DanhGia cho bộ phim với MaPhim tương ứng, chỉ tính các đánh giá có ngày hôm nay
    SELECT @DiemTrungBinh = AVG(DiemDanhGia)
    FROM DanhGia
    WHERE MaPhim = @MaPhim
    AND CAST(NgayDanhGia AS DATE) = CAST(GETDATE() AS DATE);

    -- Trả về điểm trung bình
    RETURN ISNULL(@DiemTrungBinh, 0);
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_DiemDanhGiaPhimTrungBinhTheoThang]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_DiemDanhGiaPhimTrungBinhTheoThang] (@MaPhim INT)
RETURNS FLOAT
AS
BEGIN
    DECLARE @DiemTrungBinh FLOAT;

    -- Tính điểm trung bình từ bảng DanhGia cho bộ phim với MaPhim tương ứng, chỉ tính các đánh giá trong tháng hiện tại
    SELECT @DiemTrungBinh = AVG(DiemDanhGia)
    FROM DanhGia
    WHERE MaPhim = @MaPhim
    AND MONTH(NgayDanhGia) = MONTH(GETDATE())  -- Kiểm tra tháng của NgayDanhGia so với tháng hiện tại
    AND YEAR(NgayDanhGia) = YEAR(GETDATE());  -- Kiểm tra năm của NgayDanhGia so với năm hiện tại

    -- Trả về điểm trung bình
    RETURN ISNULL(@DiemTrungBinh, 0);
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_DiemDanhGiaPhimTrungBinhTheoTuan]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_DiemDanhGiaPhimTrungBinhTheoTuan] (@MaPhim INT)
RETURNS FLOAT
AS
BEGIN
    DECLARE @DiemTrungBinh FLOAT;

    -- Tính điểm trung bình từ bảng DanhGia cho bộ phim với MaPhim tương ứng, chỉ tính các đánh giá trong tuần hiện tại
    SELECT @DiemTrungBinh = AVG(DiemDanhGia)
    FROM DanhGia
    WHERE MaPhim = @MaPhim
    AND DATEPART(week, NgayDanhGia) = DATEPART(week, GETDATE())
    AND YEAR(NgayDanhGia) = YEAR(GETDATE());

    -- Trả về điểm trung bình
    RETURN ISNULL(@DiemTrungBinh, 0);
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_DiemDanhGiaTrungBinhRapChieuCha]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_DiemDanhGiaTrungBinhRapChieuCha] (@MaRapChieu INT)
RETURNS FLOAT
AS
BEGIN
    DECLARE @DiemTrungBinh FLOAT;

    -- Tính điểm trung bình cho rạp chiếu cha từ các rạp chiếu con
    SELECT @DiemTrungBinh = AVG(diemCon)
    FROM (
        SELECT dbo.fn_DiemDanhGiaTrungBinhRapChieuCon(rc.MaRapChieuCon) AS diemCon
        FROM RapChieuCon rc
        WHERE rc.MaRapChieu = @MaRapChieu
    ) AS DiemConTable;

    -- Nếu không có đánh giá nào, trả về 0
    IF @DiemTrungBinh IS NULL
    BEGIN
        SET @DiemTrungBinh = 0;
    END

    RETURN @DiemTrungBinh;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_DiemDanhGiaTrungBinhRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_DiemDanhGiaTrungBinhRapChieuCon] (@MaRapChieuCon INT)
RETURNS FLOAT
AS
BEGIN
    DECLARE @DiemTrungBinh FLOAT;

    -- Tính điểm trung bình đánh giá cho rạp chiếu con
    SELECT @DiemTrungBinh = AVG(DanhGiaRapChieu.DiemDanhGia)
    FROM DanhGiaRapChieu
    WHERE DanhGiaRapChieu.MaRapChieuCon = @MaRapChieuCon;

    -- Nếu không có đánh giá nào, trả về NULL
    IF @DiemTrungBinh IS NULL
    BEGIN
        SET @DiemTrungBinh = 0;
    END

    RETURN @DiemTrungBinh;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_DiemDanhGiaTrungBinhTheoNgayChieuRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_DiemDanhGiaTrungBinhTheoNgayChieuRapChieuCon] (
    @MaPhim INT,
    @NgayChieu VARCHAR(10),
    @MaRapChieuCon INT
)
RETURNS FLOAT
AS
BEGIN
    DECLARE @DiemTrungBinh FLOAT;

    -- Tính điểm trung bình từ bảng DanhGia cho bộ phim với MaPhim, NgayChieu và TenRapChieuCon tương ứng
    SELECT @DiemTrungBinh = ISNULL(AVG(DiemDanhGia), 0)
    FROM DanhGia
    JOIN LichChieu ON DanhGia.MaPhim = LichChieu.MaPhim
    JOIN RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
	JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
    WHERE LichChieu.MaPhim = @MaPhim
      AND CONVERT(VARCHAR(10), ThoiGianChieu.NgayChieu, 103) = @NgayChieu
      AND RapChieuCon.MaRapChieu = @MaRapChieuCon;

    -- Trả về điểm trung bình
    RETURN @DiemTrungBinh;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_GetMinMaRapChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_GetMinMaRapChieu]()
RETURNS INT
AS
BEGIN
    DECLARE @MinMaRapChieu INT;

    SELECT @MinMaRapChieu = MIN(MaRapChieu)
    FROM RapChieu;

    RETURN @MinMaRapChieu;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_GetMinMaRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_GetMinMaRapChieuCon]
(
    @MaRapChieu INT,
    @MaTinhThanh INT
)
RETURNS INT
AS
BEGIN
    DECLARE @MinMaRapChieuCon INT;

    SELECT @MinMaRapChieuCon = MIN(RCon.MaRapChieuCon)
    FROM RapChieuCon RCon
    INNER JOIN DiaChiRapChieuCon DRC ON RCon.MaRapChieuCon = DRC.MaRapChieuCon
    WHERE RCon.MaRapChieu = @MaRapChieu
      AND DRC.MaTinhThanh = @MaTinhThanh;

    RETURN @MinMaRapChieuCon;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_LayMaThoiGianChieu_HienTai]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_LayMaThoiGianChieu_HienTai]()
RETURNS INT
AS
BEGIN
    DECLARE @MaThoiGianChieu INT;

    -- Truy vấn lấy MaThoiGianChieu của ngày hiện tại
    SELECT @MaThoiGianChieu = MaThoiGianChieu
    FROM ThoiGianChieu
    WHERE CONVERT(DATE, NgayChieu) = CONVERT(DATE, GETDATE());

    -- Trả về MaThoiGianChieu nếu có, nếu không trả về NULL
    RETURN @MaThoiGianChieu;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotDanhGiaPhim]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotDanhGiaPhim] (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotDanhGia INT;

    -- Tính tổng số lượt đánh giá cho bộ phim (không có điều kiện về ngày, tuần, tháng)
    SELECT @TongLuotDanhGia = COUNT(DISTINCT DanhGia.MaDanhGia)
    FROM DanhGia
    JOIN Phim ON DanhGia.MaPhim = Phim.MaPhim
    WHERE Phim.MaPhim = @MaPhim;

    RETURN @TongLuotDanhGia;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotDanhGiaPhimTheoNgay]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotDanhGiaPhimTheoNgay] (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotDanhGia INT;

    -- Tính tổng số lượt đánh giá cho bộ phim trong ngày hiện tại
    SELECT @TongLuotDanhGia = COUNT(DISTINCT DanhGia.MaDanhGia)
    FROM DanhGia
    JOIN Phim ON DanhGia.MaPhim = Phim.MaPhim
    WHERE Phim.MaPhim = @MaPhim
    AND CAST(DanhGia.NgayDanhGia AS DATE) = CAST(GETDATE() AS DATE);  -- So sánh ngày hiện tại

    RETURN @TongLuotDanhGia;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotDanhGiaPhimTheoNgayChieuRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotDanhGiaPhimTheoNgayChieuRapChieuCon] (
    @MaPhim INT,
    @NgayChieu VARCHAR(10),
    @MaRapChieuCon INT
)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotDanhGia INT;

    -- Tính tổng lượt đánh giá phim theo NgàyChiếu và TenRapChieuCon
    SELECT @TongLuotDanhGia = COUNT(DISTINCT DanhGia.MaDanhGia)
    FROM DanhGia
    JOIN LichChieu ON DanhGia.MaPhim = LichChieu.MaPhim
    JOIN RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
	JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
    WHERE DanhGia.MaPhim = @MaPhim
    AND CONVERT(VARCHAR(10), ThoiGianChieu.NgayChieu, 103) = @NgayChieu
    AND RapChieuCon.MaRapChieu = @MaRapChieuCon;


    -- Trả về tổng lượt đánh giá
    RETURN @TongLuotDanhGia;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotDanhGiaPhimTheoThang]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotDanhGiaPhimTheoThang] (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotDanhGia INT;

    -- Tính tổng số lượt đánh giá cho bộ phim trong tháng hiện tại
    SELECT @TongLuotDanhGia = COUNT(DISTINCT DanhGia.MaDanhGia)
    FROM DanhGia
    JOIN Phim ON DanhGia.MaPhim = Phim.MaPhim
    WHERE Phim.MaPhim = @MaPhim
    AND MONTH(DanhGia.NgayDanhGia) = MONTH(GETDATE())  -- So sánh tháng hiện tại
    AND YEAR(DanhGia.NgayDanhGia) = YEAR(GETDATE());  -- Đảm bảo năm trùng với năm hiện tại

    RETURN @TongLuotDanhGia;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotDanhGiaPhimTheoTuan]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotDanhGiaPhimTheoTuan] (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotDanhGia INT;

    -- Tính tổng số lượt đánh giá cho bộ phim trong tuần hiện tại
    SELECT @TongLuotDanhGia = COUNT(DISTINCT DanhGia.MaDanhGia)
    FROM DanhGia
    JOIN Phim ON DanhGia.MaPhim = Phim.MaPhim
    WHERE Phim.MaPhim = @MaPhim
    AND DATEPART(WEEK, DanhGia.NgayDanhGia) = DATEPART(WEEK, GETDATE())  -- So sánh tuần hiện tại
    AND YEAR(DanhGia.NgayDanhGia) = YEAR(GETDATE());  -- Đảm bảo năm trùng với năm hiện tại

    RETURN @TongLuotDanhGia;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotDanhGiaRapChieuCha]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotDanhGiaRapChieuCha] (@MaRapChieu INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotDanhGia INT = 0;

    -- Duyệt qua các rạp chiếu con của rạp chiếu cha và cộng dồn số lượt đánh giá
    DECLARE @MaRapChieuCon INT;

    DECLARE ConCursor CURSOR FOR
    SELECT MaRapChieuCon
    FROM RapChieuCon
    WHERE MaRapChieu = @MaRapChieu;

    OPEN ConCursor;
    FETCH NEXT FROM ConCursor INTO @MaRapChieuCon;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Cộng dồn lượt đánh giá của từng rạp chiếu con
        SET @TongLuotDanhGia = @TongLuotDanhGia + dbo.fn_TongLuotDanhGiaRapChieuCon(@MaRapChieuCon);
        FETCH NEXT FROM ConCursor INTO @MaRapChieuCon;
    END

    CLOSE ConCursor;
    DEALLOCATE ConCursor;

    RETURN @TongLuotDanhGia;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotDanhGiaRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotDanhGiaRapChieuCon] (@MaRapChieuCon INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotDanhGia INT;

    -- Tính tổng số lượt đánh giá cho rạp chiếu con
    SELECT @TongLuotDanhGia = COUNT(DISTINCT DanhGiaRapChieu.MaDanhGiaRapChieu)
    FROM DanhGiaRapChieu
    WHERE DanhGiaRapChieu.MaRapChieuCon = @MaRapChieuCon;

    RETURN @TongLuotDanhGia;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotMuaPhim]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotMuaPhim] (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim với điều kiện TinhTrang = N'Đã dùng'
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN LichChieu ON VePhim.MaLichChieu = LichChieu.MaLichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
    JOIN Phim ON Phim.MaPhim = LichChieu.MaPhim  -- Thêm JOIN với bảng Phim
    WHERE LichChieu.MaPhim = @MaPhim

    RETURN @TongLuotMua;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotMuaPhimTheoNgay]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotMuaPhimTheoNgay] (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim theo ngày hiện tại (không xét tình trạng vé)
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN LichChieu ON VePhim.MaLichChieu = LichChieu.MaLichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
	JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
    JOIN Phim ON Phim.MaPhim = LichChieu.MaPhim  -- Thêm JOIN với bảng Phim
    WHERE LichChieu.MaPhim = @MaPhim
    AND CAST(ThoiGianChieu.NgayChieu AS DATE) = CAST(GETDATE() AS DATE)  -- Sử dụng ngày hiện tại
    AND Phim.TrangThaiChieu = N'Đang chiếu';  -- Điều kiện phim đang chiếu

    RETURN @TongLuotMua;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotMuaPhimTheoNgayRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotMuaPhimTheoNgayRapChieuCon] (
    @MaPhim INT,
    @NgayChieu VARCHAR(10),
	@MaRapChieuCon INT
)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim tại rạp chiếu con vào ngày cụ thể
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN LichChieu ON VePhim.MaLichChieu = LichChieu.MaLichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
	JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
    JOIN RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    JOIN Phim ON Phim.MaPhim = LichChieu.MaPhim
    WHERE LichChieu.MaPhim = @MaPhim
      AND RapChieuCon.MaRapChieuCon = @MaRapChieuCon
      AND CONVERT(VARCHAR(10), ThoiGianChieu.NgayChieu, 103) = @NgayChieu  
      AND Phim.TrangThaiChieu = N'Đang chiếu';  -- Điều kiện phim đang chiếu

    RETURN @TongLuotMua;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotMuaPhimTheoThang]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotMuaPhimTheoThang] (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim trong tháng hiện tại (không xét tình trạng vé)
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN LichChieu ON VePhim.MaLichChieu = LichChieu.MaLichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
	JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
    JOIN Phim ON Phim.MaPhim = LichChieu.MaPhim  -- Thêm JOIN với bảng Phim
    WHERE LichChieu.MaPhim = @MaPhim
    AND MONTH(ThoiGianChieu.NgayChieu) = MONTH(GETDATE())  -- So sánh tháng hiện tại
    AND YEAR(ThoiGianChieu.NgayChieu) = YEAR(GETDATE())  -- Đảm bảo năm trùng với năm hiện tại
    AND Phim.TrangThaiChieu = N'Đang chiếu';  -- Điều kiện phim đang chiếu

    RETURN @TongLuotMua;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongLuotMuaPhimTheoTuan]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongLuotMuaPhimTheoTuan] (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim trong tuần hiện tại (không xét tình trạng vé)
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN LichChieu ON VePhim.MaLichChieu = LichChieu.MaLichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
	JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
    JOIN Phim ON Phim.MaPhim = LichChieu.MaPhim  -- Thêm JOIN với bảng Phim
    WHERE LichChieu.MaPhim = @MaPhim
    AND DATEPART(ISO_WEEK, ThoiGianChieu.NgayChieu) = DATEPART(ISO_WEEK, GETDATE())  -- So sánh tuần hiện tại
    AND YEAR(ThoiGianChieu.NgayChieu) = YEAR(GETDATE())  -- Đảm bảo năm trùng với năm hiện tại
    AND Phim.TrangThaiChieu = N'Đang chiếu';  -- Điều kiện phim đang chiếu

    RETURN @TongLuotMua;
END;

GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongSoDiaDiemRapChieuCha]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongSoDiaDiemRapChieuCha] (@MaRapChieu INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongDiaDiem INT;

    -- Đếm số lượng MaRapChieuCon của rạp chiếu cha
    SELECT @TongDiaDiem = COUNT(MaRapChieuCon)
    FROM RapChieuCon
    WHERE MaRapChieu = @MaRapChieu;

    RETURN @TongDiaDiem;
END;
GO
/****** Object:  UserDefinedFunction [dbo].[fn_TongTienThanhToan]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[fn_TongTienThanhToan] (
    @MaKhachHang INT
)
RETURNS FLOAT
AS
BEGIN
    DECLARE @TongTien FLOAT;

    -- Tính tổng tiền thanh toán của khách hàng
    SELECT @TongTien = SUM(TT.TongTien)
    FROM ThanhToan TT
    INNER JOIN VePhim VP ON TT.MaVe = VP.MaVe
    WHERE VP.MaKhachHang = @MaKhachHang;

    -- Trả về tổng tiền
    RETURN ISNULL(@TongTien, 0);  -- Trả về 0 nếu không có thanh toán nào
END;
GO
/****** Object:  Table [dbo].[CapBacChiTieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CapBacChiTieu](
	[MaCapBacChiTieu] [int] IDENTITY(1,1) NOT NULL,
	[AnhCapBac] [varchar](max) NULL,
	[TenCapBac] [nvarchar](50) NOT NULL,
	[HanMucChiTieu] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaCapBacChiTieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietCapBac]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietCapBac](
	[MaChiTietCapBac] [int] IDENTITY(1,1) NOT NULL,
	[MaKhachHang] [int] NOT NULL,
	[MaCapBacChiTieu] [int] NOT NULL,
	[ThoiHanReset] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaChiTietCapBac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietLichChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietLichChieu](
	[MaChiTietLichChieu] [int] IDENTITY(1,1) NOT NULL,
	[MaLichChieu] [int] NULL,
	[MaThoiGianChieu] [int] NULL,
	[ThoiGianBatDau] [time](7) NOT NULL,
	[ThoiGianKetThuc] [time](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaChiTietLichChieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DanhGia]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DanhGia](
	[MaDanhGia] [int] IDENTITY(1,1) NOT NULL,
	[MaKhachHang] [int] NULL,
	[MaPhim] [int] NULL,
	[NgayDanhGia] [datetime2](7) NOT NULL,
	[DanhGia] [nvarchar](1000) NULL,
	[DiemDanhGia] [float] NULL,
	[LuotThich] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDanhGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DanhGiaRapChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DanhGiaRapChieu](
	[MaDanhGiaRapChieu] [int] IDENTITY(1,1) NOT NULL,
	[MaRapChieuCon] [int] NULL,
	[MaKhachHang] [int] NULL,
	[NgayDanhGia] [datetime2](7) NOT NULL,
	[DanhGia] [nvarchar](1000) NULL,
	[DiemDanhGia] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDanhGiaRapChieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DiaChiRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DiaChiRapChieuCon](
	[MaDiaChiRapChieuCon] [int] IDENTITY(1,1) NOT NULL,
	[MaRapChieuCon] [int] NULL,
	[MaTinhThanh] [int] NULL,
	[DiaChiRapChieu] [nvarchar](255) NULL,
	[map] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDiaChiRapChieuCon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DoiTuongApDung]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DoiTuongApDung](
	[MaDoiTuongApDung] [int] IDENTITY(1,1) NOT NULL,
	[DoiTuongApDung] [nvarchar](255) NOT NULL,
	[MucApDung] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaDoiTuongApDung] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[MaKhachHang] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](255) NOT NULL,
	[MatKhau] [varchar](16) NOT NULL,
	[AnhKhachHang] [varchar](max) NULL,
	[TenKhachHang] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LichChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LichChieu](
	[MaLichChieu] [int] IDENTITY(1,1) NOT NULL,
	[MaRapChieuCon] [int] NULL,
	[MaPhim] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLichChieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phim]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phim](
	[MaPhim] [int] IDENTITY(1,1) NOT NULL,
	[AnhPhim] [varchar](max) NULL,
	[TenPhim] [nvarchar](255) NOT NULL,
	[Tuoi] [int] NULL,
	[DinhDangPhim] [nvarchar](255) NULL,
	[NgayKhoiChieu] [datetime2](7) NOT NULL,
	[NgayKetThuc] [datetime2](7) NOT NULL,
	[TrangThaiChieu] [nvarchar](50) NULL,
	[ThoiLuong] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaPhim] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RapChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RapChieu](
	[MaRapChieu] [int] IDENTITY(1,1) NOT NULL,
	[AnhRapChieu] [varchar](max) NULL,
	[TenRapChieu] [nvarchar](255) NOT NULL,
	[MoTaRapChieu] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaRapChieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RapChieuCon](
	[MaRapChieuCon] [int] IDENTITY(1,1) NOT NULL,
	[MaRapChieu] [int] NULL,
	[TenRapChieuCon] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaRapChieuCon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThanhToan]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThanhToan](
	[MaThanhToan] [int] IDENTITY(1,1) NOT NULL,
	[MaVe] [int] NULL,
	[QRThanhToan] [varchar](max) NULL,
	[TongTien] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaThanhToan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheLoai]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoai](
	[MaTheLoai] [int] IDENTITY(1,1) NOT NULL,
	[MaTheLoaiCha] [int] NULL,
	[MaPhim] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTheLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheLoaiCha]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoaiCha](
	[MaTheLoaiCha] [int] IDENTITY(1,1) NOT NULL,
	[TenTheLoai] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTheLoaiCha] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThoiGianChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThoiGianChieu](
	[MaThoiGianChieu] [int] IDENTITY(1,1) NOT NULL,
	[KieuNgay] [nvarchar](20) NULL,
	[NgayChieu] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaThoiGianChieu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TinhThanhPho]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TinhThanhPho](
	[MaTinhThanh] [int] IDENTITY(1,1) NOT NULL,
	[TenTinhThanh] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTinhThanh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TinhTrangVe]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TinhTrangVe](
	[MaTinhTrang] [int] IDENTITY(1,1) NOT NULL,
	[MaVe] [int] NULL,
	[TinhTrang] [nvarchar](50) NULL,
	[ThoiGian] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTinhTrang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VePhim]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VePhim](
	[MaVe] [int] IDENTITY(1,1) NOT NULL,
	[MaLichChieu] [int] NULL,
	[MaKhachHang] [int] NULL,
	[SoLuongVe] [int] NULL,
	[GheNgoi] [int] NULL,
	[PhongChieu] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaVe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VoucherCuaToi]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VoucherCuaToi](
	[MaVoucherCuaToi] [int] IDENTITY(1,1) NOT NULL,
	[MaKhachHang] [int] NULL,
	[TenVoucher] [varchar](50) NOT NULL,
	[MaDoiTuongApDung] [int] NULL,
	[TrangThaiGiam] [nvarchar](50) NULL,
	[MucGiam] [int] NOT NULL,
	[HanSuDung] [datetime2](7) NOT NULL,
	[TrangThaiSuDung] [nvarchar](50) NULL,
	[SoLuongToiDa] [int] NULL,
	[SoLuongSuDung] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaVoucherCuaToi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VoucherDoiTac]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VoucherDoiTac](
	[MaVoucherDoiTac] [int] IDENTITY(1,1) NOT NULL,
	[MaRapChieu] [int] NULL,
	[TenVoucher] [varchar](50) NOT NULL,
	[MaDoiTuongApDung] [int] NULL,
	[TrangThaiGiam] [nvarchar](50) NULL,
	[MucGiam] [int] NOT NULL,
	[HanSuDung] [datetime2](7) NOT NULL,
	[TrangThaiSuDung] [nvarchar](50) NULL,
	[SoLuongToiDa] [int] NULL,
	[SoLuongSuDung] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaVoucherDoiTac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VoucherUngDung]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VoucherUngDung](
	[MaVoucherUngDung] [int] IDENTITY(1,1) NOT NULL,
	[AnhUngDung] [varchar](max) NULL,
	[TenVoucher] [varchar](50) NOT NULL,
	[MaDoiTuongApDung] [int] NULL,
	[TrangThaiGiam] [nvarchar](50) NULL,
	[MucGiam] [int] NOT NULL,
	[HanSuDung] [datetime2](7) NOT NULL,
	[TrangThaiSuDung] [nvarchar](50) NULL,
	[SoLuongToiDa] [int] NULL,
	[SoLuongSuDung] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaVoucherUngDung] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[CapBacChiTieu] ON 

INSERT [dbo].[CapBacChiTieu] ([MaCapBacChiTieu], [AnhCapBac], [TenCapBac], [HanMucChiTieu]) VALUES (1, N'anhcapbac1', N'đồng', 10000000)
INSERT [dbo].[CapBacChiTieu] ([MaCapBacChiTieu], [AnhCapBac], [TenCapBac], [HanMucChiTieu]) VALUES (2, N'anhcapbac2', N'bạc', 20000000)
INSERT [dbo].[CapBacChiTieu] ([MaCapBacChiTieu], [AnhCapBac], [TenCapBac], [HanMucChiTieu]) VALUES (3, N'anhcapbac3', N'vàng', 30000000)
INSERT [dbo].[CapBacChiTieu] ([MaCapBacChiTieu], [AnhCapBac], [TenCapBac], [HanMucChiTieu]) VALUES (4, N'anhcapbac4', N'bạch kim', 40000000)
INSERT [dbo].[CapBacChiTieu] ([MaCapBacChiTieu], [AnhCapBac], [TenCapBac], [HanMucChiTieu]) VALUES (5, N'anhcapbac5', N'kim cương', 50000000)
SET IDENTITY_INSERT [dbo].[CapBacChiTieu] OFF
GO
SET IDENTITY_INSERT [dbo].[ChiTietCapBac] ON 

INSERT [dbo].[ChiTietCapBac] ([MaChiTietCapBac], [MaKhachHang], [MaCapBacChiTieu], [ThoiHanReset]) VALUES (1, 1, 1, CAST(N'2025-12-31T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ChiTietCapBac] ([MaChiTietCapBac], [MaKhachHang], [MaCapBacChiTieu], [ThoiHanReset]) VALUES (2, 2, 1, CAST(N'2025-12-31T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ChiTietCapBac] ([MaChiTietCapBac], [MaKhachHang], [MaCapBacChiTieu], [ThoiHanReset]) VALUES (3, 3, 1, CAST(N'2025-12-31T00:00:00.0000000' AS DateTime2))
SET IDENTITY_INSERT [dbo].[ChiTietCapBac] OFF
GO
SET IDENTITY_INSERT [dbo].[ChiTietLichChieu] ON 

INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (1, 1, 8, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (2, 2, 8, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (3, 3, 8, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (4, 4, 8, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (5, 5, 8, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (6, 6, 8, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (7, 7, 8, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (8, 8, 8, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (9, 9, 8, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (10, 10, 8, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (11, 1, 9, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (12, 2, 9, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (13, 3, 9, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (14, 4, 9, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (15, 5, 9, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (16, 6, 9, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (17, 7, 9, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (18, 8, 9, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (19, 9, 9, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (20, 10, 9, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (21, 1, 10, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (22, 2, 10, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (23, 3, 10, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (24, 4, 10, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (25, 5, 10, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (26, 6, 10, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (27, 7, 10, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (28, 8, 10, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (29, 9, 10, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (30, 10, 10, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (31, 1, 11, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (32, 2, 11, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (33, 3, 11, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (34, 4, 11, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (35, 5, 11, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (36, 6, 11, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (37, 7, 11, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (38, 8, 11, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (39, 9, 11, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (40, 10, 11, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (41, 1, 12, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (42, 2, 12, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (43, 3, 12, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (44, 4, 12, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (45, 5, 12, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (46, 6, 12, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (47, 7, 12, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (48, 8, 12, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (49, 9, 12, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (50, 10, 12, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (51, 1, 13, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (52, 2, 13, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (53, 3, 13, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (54, 4, 13, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (55, 5, 13, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (56, 6, 13, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (57, 7, 13, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (58, 8, 13, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (59, 9, 13, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (60, 10, 13, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (61, 1, 14, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (62, 2, 14, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (63, 3, 14, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (64, 4, 14, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (65, 5, 14, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (66, 6, 14, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (67, 7, 14, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (68, 8, 14, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (69, 9, 14, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (70, 10, 14, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (71, 1, 8, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (72, 2, 8, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (73, 3, 8, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (74, 4, 8, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (75, 5, 8, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (76, 6, 8, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (77, 7, 8, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (78, 8, 8, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (79, 9, 8, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (80, 10, 8, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (81, 1, 9, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (82, 2, 9, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (83, 3, 9, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (84, 4, 9, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (85, 5, 9, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (86, 6, 9, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (87, 7, 9, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (88, 8, 9, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (89, 9, 9, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (90, 10, 9, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (91, 1, 8, CAST(N'10:00:00' AS Time), CAST(N'12:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (92, 2, 8, CAST(N'11:00:00' AS Time), CAST(N'13:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (93, 3, 8, CAST(N'12:00:00' AS Time), CAST(N'14:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (94, 4, 8, CAST(N'13:00:00' AS Time), CAST(N'15:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (95, 5, 8, CAST(N'14:00:00' AS Time), CAST(N'16:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (96, 6, 8, CAST(N'15:00:00' AS Time), CAST(N'17:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (97, 7, 8, CAST(N'16:00:00' AS Time), CAST(N'18:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (98, 8, 8, CAST(N'17:00:00' AS Time), CAST(N'19:00:00' AS Time))
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (99, 9, 8, CAST(N'18:00:00' AS Time), CAST(N'20:00:00' AS Time))
GO
INSERT [dbo].[ChiTietLichChieu] ([MaChiTietLichChieu], [MaLichChieu], [MaThoiGianChieu], [ThoiGianBatDau], [ThoiGianKetThuc]) VALUES (100, 10, 8, CAST(N'19:00:00' AS Time), CAST(N'21:00:00' AS Time))
SET IDENTITY_INSERT [dbo].[ChiTietLichChieu] OFF
GO
SET IDENTITY_INSERT [dbo].[DanhGia] ON 

INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (1, 1, 1, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 1', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (2, 1, 2, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 2', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (3, 1, 3, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 3', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (4, 1, 4, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 4', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (5, 1, 5, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 5', 9, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (6, 1, 6, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 6', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (7, 1, 7, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 7', 6, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (8, 1, 8, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 8', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (9, 1, 9, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 9', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (10, 1, 10, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 10', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (11, 1, 11, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 11', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (12, 1, 12, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 12', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (13, 1, 13, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 13', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (14, 1, 14, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 14', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (15, 1, 15, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 15', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (16, 1, 16, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 16', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (17, 1, 17, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 17', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (18, 1, 18, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 18', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (19, 1, 19, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 19', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (20, 1, 20, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 20', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (21, 1, 21, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 21', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (22, 1, 22, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 22', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (23, 1, 23, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 23', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (24, 1, 24, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 24', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (25, 1, 25, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 25', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (26, 1, 26, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 26', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (27, 1, 27, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 27', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (28, 1, 28, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 28', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (29, 1, 29, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 29', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (30, 1, 30, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 1 cho phim 30', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (31, 2, 1, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 1', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (32, 2, 2, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 2', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (33, 2, 3, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 3', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (34, 2, 4, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 4', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (35, 2, 5, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 5', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (36, 2, 6, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 6', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (37, 2, 7, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 7', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (38, 2, 8, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 8', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (39, 2, 9, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 9', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (40, 2, 10, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 10', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (41, 2, 11, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 11', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (42, 2, 12, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 12', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (43, 2, 13, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 13', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (44, 2, 14, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 14', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (45, 2, 15, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 15', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (46, 2, 16, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 16', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (47, 2, 17, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 17', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (48, 2, 18, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 18', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (49, 2, 19, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 19', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (50, 2, 20, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 20', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (51, 2, 21, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 21', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (52, 2, 22, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 22', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (53, 2, 23, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 23', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (54, 2, 24, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 24', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (55, 2, 25, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 25', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (56, 2, 26, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 26', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (57, 2, 27, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 27', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (58, 2, 28, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 28', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (59, 2, 29, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 29', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (60, 2, 30, CAST(N'2024-12-22T15:49:47.4533333' AS DateTime2), N'Đánh giá của khách hàng 2 cho phim 30', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (61, 3, 1, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 1', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (62, 3, 2, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 2', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (63, 3, 3, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 3', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (64, 3, 4, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 4', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (65, 3, 5, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 5', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (66, 3, 6, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 6', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (67, 3, 7, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 7', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (68, 3, 8, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 8', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (69, 3, 9, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 9', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (70, 3, 10, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 10', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (71, 3, 11, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 11', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (72, 3, 12, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 12', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (73, 3, 13, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 13', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (74, 3, 14, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 14', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (75, 3, 15, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 15', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (76, 3, 16, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 16', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (77, 3, 17, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 17', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (78, 3, 18, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 18', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (79, 3, 19, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 19', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (80, 3, 20, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 20', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (81, 3, 21, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 21', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (82, 3, 22, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 22', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (83, 3, 23, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 23', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (84, 3, 24, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 24', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (85, 3, 25, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 25', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (86, 3, 26, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 26', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (87, 3, 27, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 27', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (88, 3, 28, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 28', 7, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (89, 3, 29, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 29', 8, 0)
INSERT [dbo].[DanhGia] ([MaDanhGia], [MaKhachHang], [MaPhim], [NgayDanhGia], [DanhGia], [DiemDanhGia], [LuotThich]) VALUES (90, 3, 30, CAST(N'2024-12-22T15:49:47.4600000' AS DateTime2), N'Đánh giá của khách hàng 3 cho phim 30', 7, 0)
SET IDENTITY_INSERT [dbo].[DanhGia] OFF
GO
SET IDENTITY_INSERT [dbo].[DanhGiaRapChieu] ON 

INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (1, 1, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 1 rất tuyệt vời!', 5)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (2, 2, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 2 dịch vụ khá ổn.', 4)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (3, 3, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 3 cần cải thiện chất lượng âm thanh.', 3)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (4, 4, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 4 không gian rất thoải mái.', 4)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (5, 5, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 5 ánh sáng trong phòng chiếu không tốt.', 2)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (6, 6, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 6 rất đẹp, đội ngũ nhân viên thân thiện.', 5)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (7, 7, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 7 thiết bị chiếu hơi lỗi thời.', 3)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (8, 8, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 8 dịch vụ tốt nhưng cần cải thiện vệ sinh.', 4)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (9, 9, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 9 quá đông, cần thêm ghế.', 2)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (10, 10, 1, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 10 rất tuyệt vời, sẽ quay lại!', 5)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (11, 11, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 11 không gian thoải mái nhưng thiếu ghế VIP.', 3)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (12, 12, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 12 chất lượng chiếu hình ảnh tốt.', 4)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (13, 13, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 13 cần nâng cấp hệ thống điều hòa.', 2)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (14, 14, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 14 là một lựa chọn tuyệt vời!', 5)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (15, 15, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 15 chưa đạt chất lượng, ghế không thoải mái.', 2)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (16, 16, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 16 rất tiện lợi và sạch sẽ.', 5)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (17, 17, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 17 ánh sáng yếu quá, khó nhìn.', 3)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (18, 18, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 18 nhân viên lịch sự, môi trường sạch sẽ.', 4)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (19, 19, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 19 chất lượng âm thanh tuyệt vời!', 5)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (20, 20, 2, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 20 cần cải thiện hệ thống chiếu phim.', 3)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (21, 21, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 21 không gian nhỏ, không đủ chỗ ngồi.', 2)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (22, 22, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 22 dịch vụ tốt nhưng có thể cải thiện.', 4)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (23, 23, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 23 âm thanh chưa đủ lớn.', 3)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (24, 24, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 24 chất lượng hình ảnh ổn.', 3)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (25, 25, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 25 rất đẹp và thoải mái.', 5)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (26, 26, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 26 cần cải thiện ghế ngồi.', 2)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (27, 27, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 27 âm thanh rất hay, sẽ quay lại!', 5)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (28, 28, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 28 nhân viên không thân thiện lắm.', 2)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (29, 29, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 29 quá đông, không có đủ không gian.', 3)
INSERT [dbo].[DanhGiaRapChieu] ([MaDanhGiaRapChieu], [MaRapChieuCon], [MaKhachHang], [NgayDanhGia], [DanhGia], [DiemDanhGia]) VALUES (30, 30, 3, CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), N'Rạp Chiếu con 30 rất tuyệt vời, ghế ngồi thoải mái.', 4)
SET IDENTITY_INSERT [dbo].[DanhGiaRapChieu] OFF
GO
SET IDENTITY_INSERT [dbo].[DiaChiRapChieuCon] ON 

INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (1, 1, 1, N'Địa chỉ rạp chiếu con 1, Tỉnh 1', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (2, 2, 2, N'Địa chỉ rạp chiếu con 2, Tỉnh 2', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (3, 3, 3, N'Địa chỉ rạp chiếu con 3, Tỉnh 3', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (4, 4, 4, N'Địa chỉ rạp chiếu con 4, Tỉnh 4', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (5, 5, 5, N'Địa chỉ rạp chiếu con 5, Tỉnh 5', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (6, 6, 6, N'Địa chỉ rạp chiếu con 6, Tỉnh 6', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (7, 7, 7, N'Địa chỉ rạp chiếu con 7, Tỉnh 7', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (8, 8, 8, N'Địa chỉ rạp chiếu con 8, Tỉnh 8', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (9, 9, 9, N'Địa chỉ rạp chiếu con 9, Tỉnh 9', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (10, 10, 10, N'Địa chỉ rạp chiếu con 10, Tỉnh 10', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (11, 11, 11, N'Địa chỉ rạp chiếu con 11, Tỉnh 11', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (12, 12, 12, N'Địa chỉ rạp chiếu con 12, Tỉnh 12', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (13, 13, 13, N'Địa chỉ rạp chiếu con 13, Tỉnh 13', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (14, 14, 14, N'Địa chỉ rạp chiếu con 14, Tỉnh 14', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (15, 15, 15, N'Địa chỉ rạp chiếu con 15, Tỉnh 15', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (16, 16, 16, N'Địa chỉ rạp chiếu con 16, Tỉnh 16', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (17, 17, 17, N'Địa chỉ rạp chiếu con 17, Tỉnh 17', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (18, 18, 18, N'Địa chỉ rạp chiếu con 18, Tỉnh 18', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (19, 19, 19, N'Địa chỉ rạp chiếu con 19, Tỉnh 19', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (20, 20, 20, N'Địa chỉ rạp chiếu con 20, Tỉnh 20', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (21, 21, 21, N'Địa chỉ rạp chiếu con 21, Tỉnh 21', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (22, 22, 22, N'Địa chỉ rạp chiếu con 22, Tỉnh 22', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (23, 23, 23, N'Địa chỉ rạp chiếu con 23, Tỉnh 23', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (24, 24, 24, N'Địa chỉ rạp chiếu con 24, Tỉnh 24', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (25, 25, 25, N'Địa chỉ rạp chiếu con 25, Tỉnh 25', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (26, 26, 26, N'Địa chỉ rạp chiếu con 26, Tỉnh 26', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (27, 27, 27, N'Địa chỉ rạp chiếu con 27, Tỉnh 27', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (28, 28, 28, N'Địa chỉ rạp chiếu con 28, Tỉnh 28', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (29, 29, 29, N'Địa chỉ rạp chiếu con 29, Tỉnh 29', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (30, 30, 30, N'Địa chỉ rạp chiếu con 30, Tỉnh 30', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (31, 31, 31, N'Địa chỉ rạp chiếu con 31, Tỉnh 31', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (32, 32, 32, N'Địa chỉ rạp chiếu con 32, Tỉnh 32', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (33, 33, 33, N'Địa chỉ rạp chiếu con 33, Tỉnh 33', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (34, 34, 34, N'Địa chỉ rạp chiếu con 34, Tỉnh 34', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (35, 35, 35, N'Địa chỉ rạp chiếu con 35, Tỉnh 35', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (36, 36, 36, N'Địa chỉ rạp chiếu con 36, Tỉnh 36', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (37, 37, 37, N'Địa chỉ rạp chiếu con 37, Tỉnh 37', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (38, 38, 38, N'Địa chỉ rạp chiếu con 38, Tỉnh 38', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (39, 39, 39, N'Địa chỉ rạp chiếu con 39, Tỉnh 39', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (40, 40, 40, N'Địa chỉ rạp chiếu con 40, Tỉnh 40', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (41, 41, 41, N'Địa chỉ rạp chiếu con 41, Tỉnh 41', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (42, 42, 42, N'Địa chỉ rạp chiếu con 42, Tỉnh 42', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (43, 43, 43, N'Địa chỉ rạp chiếu con 43, Tỉnh 43', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (44, 44, 44, N'Địa chỉ rạp chiếu con 44, Tỉnh 44', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (45, 45, 45, N'Địa chỉ rạp chiếu con 45, Tỉnh 45', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (46, 46, 46, N'Địa chỉ rạp chiếu con 46, Tỉnh 46', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (47, 47, 47, N'Địa chỉ rạp chiếu con 47, Tỉnh 47', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (48, 48, 48, N'Địa chỉ rạp chiếu con 48, Tỉnh 48', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (49, 49, 49, N'Địa chỉ rạp chiếu con 49, Tỉnh 49', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (50, 50, 50, N'Địa chỉ rạp chiếu con 50, Tỉnh 50', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (51, 51, 51, N'Địa chỉ rạp chiếu con 51, Tỉnh 51', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (52, 52, 52, N'Địa chỉ rạp chiếu con 52, Tỉnh 52', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (53, 53, 53, N'Địa chỉ rạp chiếu con 53, Tỉnh 53', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (54, 54, 54, N'Địa chỉ rạp chiếu con 54, Tỉnh 54', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (55, 55, 55, N'Địa chỉ rạp chiếu con 55, Tỉnh 55', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (56, 56, 56, N'Địa chỉ rạp chiếu con 56, Tỉnh 56', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (57, 57, 57, N'Địa chỉ rạp chiếu con 57, Tỉnh 57', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (58, 58, 58, N'Địa chỉ rạp chiếu con 58, Tỉnh 58', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (59, 59, 59, N'Địa chỉ rạp chiếu con 59, Tỉnh 59', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (60, 60, 60, N'Địa chỉ rạp chiếu con 60, Tỉnh 60', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (61, 61, 61, N'Địa chỉ rạp chiếu con 61, Tỉnh 61', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (62, 62, 62, N'Địa chỉ rạp chiếu con 62, Tỉnh 62', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (63, 63, 63, N'Địa chỉ rạp chiếu con 63, Tỉnh 63', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (64, 64, 62, N'Địa chỉ rạp chiếu con 64, Tỉnh 64', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (65, 65, 61, N'Địa chỉ rạp chiếu con 65, Tỉnh 65', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (66, 66, 60, N'Địa chỉ rạp chiếu con 66, Tỉnh 66', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (67, 67, 59, N'Địa chỉ rạp chiếu con 67, Tỉnh 67', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (68, 68, 58, N'Địa chỉ rạp chiếu con 68, Tỉnh 68', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (69, 69, 57, N'Địa chỉ rạp chiếu con 69, Tỉnh 69', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (70, 70, 40, N'Địa chỉ rạp chiếu con 70, Tỉnh 70', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (71, 71, 1, N'Địa chỉ rạp chiếu con 71, Tỉnh 71', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (72, 72, 2, N'Địa chỉ rạp chiếu con 72, Tỉnh 72', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (73, 73, 3, N'Địa chỉ rạp chiếu con 73, Tỉnh 73', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (74, 74, 4, N'Địa chỉ rạp chiếu con 74, Tỉnh 74', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (75, 75, 5, N'Địa chỉ rạp chiếu con 75, Tỉnh 75', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (76, 76, 6, N'Địa chỉ rạp chiếu con 76, Tỉnh 76', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (77, 77, 7, N'Địa chỉ rạp chiếu con 77, Tỉnh 77', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (78, 78, 8, N'Địa chỉ rạp chiếu con 78, Tỉnh 78', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (79, 79, 9, N'Địa chỉ rạp chiếu con 79, Tỉnh 79', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (80, 80, 10, N'Địa chỉ rạp chiếu con 80, Tỉnh 80', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (81, 81, 11, N'Địa chỉ rạp chiếu con 81, Tỉnh 81', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (82, 82, 12, N'Địa chỉ rạp chiếu con 82, Tỉnh 82', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (83, 83, 13, N'Địa chỉ rạp chiếu con 83, Tỉnh 83', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (84, 84, 14, N'Địa chỉ rạp chiếu con 84, Tỉnh 84', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (85, 85, 15, N'Địa chỉ rạp chiếu con 85, Tỉnh 85', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (86, 86, 6, N'Địa chỉ rạp chiếu con 86, Tỉnh 86', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (87, 87, 7, N'Địa chỉ rạp chiếu con 87, Tỉnh 87', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (88, 88, 8, N'Địa chỉ rạp chiếu con 88, Tỉnh 88', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (89, 89, 9, N'Địa chỉ rạp chiếu con 89, Tỉnh 89', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (90, 90, 20, N'Địa chỉ rạp chiếu con 90, Tỉnh 90', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (91, 91, 21, N'Địa chỉ rạp chiếu con 91, Tỉnh 91', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (92, 92, 2, N'Địa chỉ rạp chiếu con 92, Tỉnh 92', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (93, 93, 3, N'Địa chỉ rạp chiếu con 93, Tỉnh 93', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (94, 94, 4, N'Địa chỉ rạp chiếu con 94, Tỉnh 94', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (95, 95, 5, N'Địa chỉ rạp chiếu con 95, Tỉnh 95', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (96, 96, 6, N'Địa chỉ rạp chiếu con 96, Tỉnh 96', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (97, 97, 7, N'Địa chỉ rạp chiếu con 97, Tỉnh 97', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (98, 98, 8, N'Địa chỉ rạp chiếu con 98, Tỉnh 98', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (99, 99, 9, N'Địa chỉ rạp chiếu con 99, Tỉnh 99', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
GO
INSERT [dbo].[DiaChiRapChieuCon] ([MaDiaChiRapChieuCon], [MaRapChieuCon], [MaTinhThanh], [DiaChiRapChieu], [map]) VALUES (100, 100, 10, N'Địa chỉ rạp chiếu con 100, Tỉnh 100', N'https://maps.google.com/?q=Vincom+Đà+Nẵng')
SET IDENTITY_INSERT [dbo].[DiaChiRapChieuCon] OFF
GO
SET IDENTITY_INSERT [dbo].[DoiTuongApDung] ON 

INSERT [dbo].[DoiTuongApDung] ([MaDoiTuongApDung], [DoiTuongApDung], [MucApDung]) VALUES (1, N'Khách hàng mới, đơn hàng tối thiểu', 50000)
INSERT [dbo].[DoiTuongApDung] ([MaDoiTuongApDung], [DoiTuongApDung], [MucApDung]) VALUES (2, N'Đơn tối thiểu', 100000)
INSERT [dbo].[DoiTuongApDung] ([MaDoiTuongApDung], [DoiTuongApDung], [MucApDung]) VALUES (3, N'Đơn tối thiểu', 200000)
INSERT [dbo].[DoiTuongApDung] ([MaDoiTuongApDung], [DoiTuongApDung], [MucApDung]) VALUES (4, N'Đơn tối thiểu', 300000)
INSERT [dbo].[DoiTuongApDung] ([MaDoiTuongApDung], [DoiTuongApDung], [MucApDung]) VALUES (5, N'Giảm tối đa', 50000)
INSERT [dbo].[DoiTuongApDung] ([MaDoiTuongApDung], [DoiTuongApDung], [MucApDung]) VALUES (6, N'Giảm tối đa', 100000)
INSERT [dbo].[DoiTuongApDung] ([MaDoiTuongApDung], [DoiTuongApDung], [MucApDung]) VALUES (7, N'Giảm tối đa', 150000)
SET IDENTITY_INSERT [dbo].[DoiTuongApDung] OFF
GO
SET IDENTITY_INSERT [dbo].[KhachHang] ON 

INSERT [dbo].[KhachHang] ([MaKhachHang], [Email], [MatKhau], [AnhKhachHang], [TenKhachHang]) VALUES (1, N'leducthien@example.com', N'password1', N'AnhKhachHang-1', N'Lê Đức Thiện')
INSERT [dbo].[KhachHang] ([MaKhachHang], [Email], [MatKhau], [AnhKhachHang], [TenKhachHang]) VALUES (2, N'phancongquoc@example.com', N'password2', N'AnhKhachHang-2', N'Phan Công Quốc')
INSERT [dbo].[KhachHang] ([MaKhachHang], [Email], [MatKhau], [AnhKhachHang], [TenKhachHang]) VALUES (3, N'trangiathai@example.com', N'password3', N'AnhKhachHang-3', N'Trần Gia Thái')
SET IDENTITY_INSERT [dbo].[KhachHang] OFF
GO
SET IDENTITY_INSERT [dbo].[LichChieu] ON 

INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (1, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (2, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (3, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (4, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (5, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (6, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (7, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (8, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (9, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (10, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (11, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (12, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (13, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (14, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (15, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (16, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (17, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (18, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (19, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (20, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (21, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (22, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (23, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (24, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (25, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (26, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (27, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (28, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (29, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (30, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (31, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (32, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (33, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (34, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (35, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (36, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (37, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (38, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (39, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (40, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (41, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (42, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (43, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (44, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (45, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (46, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (47, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (48, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (49, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (50, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (51, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (52, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (53, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (54, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (55, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (56, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (57, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (58, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (59, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (60, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (61, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (62, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (63, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (64, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (65, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (66, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (67, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (68, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (69, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (70, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (71, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (72, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (73, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (74, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (75, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (76, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (77, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (78, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (79, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (80, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (81, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (82, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (83, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (84, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (85, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (86, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (87, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (88, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (89, 1, 9)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (90, 1, 10)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (91, 1, 1)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (92, 1, 2)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (93, 1, 3)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (94, 1, 4)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (95, 1, 5)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (96, 1, 6)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (97, 1, 7)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (98, 1, 8)
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (99, 1, 9)
GO
INSERT [dbo].[LichChieu] ([MaLichChieu], [MaRapChieuCon], [MaPhim]) VALUES (100, 1, 10)
SET IDENTITY_INSERT [dbo].[LichChieu] OFF
GO
SET IDENTITY_INSERT [dbo].[Phim] ON 

INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (1, N'poster1', N'Avengers: Endgame', 13, N'IMAX 3D', CAST(N'2024-12-01T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-30T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'03:01:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (2, N'poster2', N'Inception', 16, N'IMAX', CAST(N'2024-12-02T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'02:28:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (3, N'poster3', N'Titanic', 13, N'2D', CAST(N'2024-12-03T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-30T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'03:14:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (4, N'poster4', N'Avatar: The Way of Water', 16, N'3D', CAST(N'2024-12-04T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'03:12:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (5, N'poster5', N'Spider-Man: No Way Home', 13, N'IMAX', CAST(N'2024-12-05T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-30T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'02:28:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (6, N'poster6', N'The Dark Knight', 16, N'IMAX', CAST(N'2024-12-06T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'02:32:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (7, N'poster7', N'Frozen', 6, N'3D', CAST(N'2024-12-07T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'01:42:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (8, N'poster8', N'Toy Story 4', 6, N'2D', CAST(N'2024-12-08T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'01:40:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (9, N'poster9', N'Black Panther', 13, N'IMAX', CAST(N'2024-12-08T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'02:14:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (10, N'poster10', N'Jurassic World: Dominion', 13, N'IMAX', CAST(N'2024-12-08T00:00:00.0000000' AS DateTime2), CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Đang chiếu', CAST(N'02:26:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (11, N'poster11', N'Guardians of the Galaxy Vol. 3', 13, N'IMAX 3D', CAST(N'2025-01-01T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:30:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (12, N'poster12', N'The Lion King', 6, N'2D', CAST(N'2025-01-02T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:29:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (13, N'poster13', N'Pirates of the Caribbean: The Curse of the Black Pearl', 13, N'2D', CAST(N'2025-01-03T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:23:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (14, N'poster14', N'Dune', 13, N'IMAX', CAST(N'2025-01-04T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:35:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (15, N'poster15', N'Shrek', 6, N'3D', CAST(N'2025-01-05T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:30:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (16, N'poster16', N'Interstellar', 13, N'IMAX', CAST(N'2025-01-06T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:49:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (17, N'poster17', N'WALL-E', 6, N'2D', CAST(N'2025-01-07T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:38:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (18, N'poster18', N'Dr. Strange in the Multiverse of Madness', 13, N'IMAX 3D', CAST(N'2025-01-08T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:06:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (19, N'poster19', N'Coco', 6, N'2D', CAST(N'2025-01-09T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:49:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (20, N'poster20', N'Soul', 6, N'3D', CAST(N'2025-01-10T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:40:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (21, N'poster21', N'The Matrix Resurrections', 13, N'IMAX', CAST(N'2025-01-06T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:49:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (22, N'poster22', N'The Batman', 6, N'2D', CAST(N'2025-01-07T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:38:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (23, N'poster23', N'Tangled', 13, N'IMAX 3D', CAST(N'2025-01-08T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:06:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (24, N'poster24', N'The Secret Life of Pets', 6, N'2D', CAST(N'2025-01-09T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:49:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (25, N'poster25', N'Thor: Ragnarok', 6, N'3D', CAST(N'2025-01-10T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:40:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (26, N'poster26', N'Aladdin', 6, N'2D', CAST(N'2025-01-16T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:08:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (27, N'poster27', N'Moana', 6, N'2D', CAST(N'2025-01-17T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:47:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (28, N'poster28', N'Beauty and the Beast', 0, N'2D', CAST(N'2025-01-18T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:10:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (29, N'poster29', N'Mulan', 6, N'2D', CAST(N'2025-01-19T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'01:55:00' AS Time))
INSERT [dbo].[Phim] ([MaPhim], [AnhPhim], [TenPhim], [Tuoi], [DinhDangPhim], [NgayKhoiChieu], [NgayKetThuc], [TrangThaiChieu], [ThoiLuong]) VALUES (30, N'poster30', N'Star Wars: The Force Awakens', 13, N'IMAX', CAST(N'2025-01-20T00:00:00.0000000' AS DateTime2), CAST(N'2025-01-31T00:00:00.0000000' AS DateTime2), N'Sắp chiếu', CAST(N'02:18:00' AS Time))
SET IDENTITY_INSERT [dbo].[Phim] OFF
GO
SET IDENTITY_INSERT [dbo].[RapChieu] ON 

INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (1, N'rap1', N'Rạp Chiếu A', N'Rạp Chiếu hiện đại với các tiện ích cao cấp, không gian thoải mái.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (2, N'rap2', N'Rạp Chiếu B', N'Rạp Chiếu phim với màn hình lớn và âm thanh vòm chất lượng cao.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (3, N'rap3', N'Rạp Chiếu C', N'Rạp Chiếu được thiết kế sang trọng, phục vụ các bộ phim bom tấn.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (4, N'rap4', N'Rạp Chiếu D', N'Rạp Chiếu phim với không gian ấm cúng, thích hợp cho các buổi chiếu nhỏ.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (5, N'rap5', N'Rạp Chiếu E', N'Rạp Chiếu phim mới với các ghế ngồi thoải mái và hệ thống âm thanh hiện đại.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (6, N'rap6', N'Rạp Chiếu F', N'Rạp Chiếu 3D với màn hình cực lớn, mang lại trải nghiệm tuyệt vời.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (7, N'rap7', N'Rạp Chiếu G', N'Rạp Chiếu phục vụ cả phim quốc tế và nội địa với các lựa chọn phòng chiếu đa dạng.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (8, N'rap8', N'Rạp Chiếu H', N'Rạp Chiếu với dịch vụ ăn uống tại chỗ, mang lại sự tiện lợi cho người xem.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (9, N'rap9', N'Rạp Chiếu I', N'Rạp Chiếu công nghệ cao với hệ thống máy chiếu 4K và âm thanh Dolby Atmos.')
INSERT [dbo].[RapChieu] ([MaRapChieu], [AnhRapChieu], [TenRapChieu], [MoTaRapChieu]) VALUES (10, N'rap10', N'Rạp Chiếu J', N'Rạp Chiếu với không gian hiện đại và đội ngũ nhân viên phục vụ chuyên nghiệp.')
SET IDENTITY_INSERT [dbo].[RapChieu] OFF
GO
SET IDENTITY_INSERT [dbo].[RapChieuCon] ON 

INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (1, 1, N'Rạp Chiếu Con A1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (2, 1, N'Rạp Chiếu Con A2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (3, 1, N'Rạp Chiếu Con A3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (4, 1, N'Rạp Chiếu Con A4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (5, 1, N'Rạp Chiếu Con A5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (6, 1, N'Rạp Chiếu Con A6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (7, 1, N'Rạp Chiếu Con A7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (8, 1, N'Rạp Chiếu Con A8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (9, 1, N'Rạp Chiếu Con A9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (10, 1, N'Rạp Chiếu Con A10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (11, 2, N'Rạp Chiếu Con B1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (12, 2, N'Rạp Chiếu Con B2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (13, 2, N'Rạp Chiếu Con B3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (14, 2, N'Rạp Chiếu Con B4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (15, 2, N'Rạp Chiếu Con B5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (16, 2, N'Rạp Chiếu Con B6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (17, 2, N'Rạp Chiếu Con B7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (18, 2, N'Rạp Chiếu Con B8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (19, 2, N'Rạp Chiếu Con B9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (20, 2, N'Rạp Chiếu Con B10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (21, 3, N'Rạp Chiếu Con C1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (22, 3, N'Rạp Chiếu Con C2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (23, 3, N'Rạp Chiếu Con C3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (24, 3, N'Rạp Chiếu Con C4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (25, 3, N'Rạp Chiếu Con C5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (26, 3, N'Rạp Chiếu Con C6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (27, 3, N'Rạp Chiếu Con C7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (28, 3, N'Rạp Chiếu Con C8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (29, 3, N'Rạp Chiếu Con C9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (30, 3, N'Rạp Chiếu Con C10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (31, 4, N'Rạp Chiếu Con D1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (32, 4, N'Rạp Chiếu Con D2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (33, 4, N'Rạp Chiếu Con D3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (34, 4, N'Rạp Chiếu Con D4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (35, 4, N'Rạp Chiếu Con D5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (36, 4, N'Rạp Chiếu Con D6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (37, 4, N'Rạp Chiếu Con D7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (38, 4, N'Rạp Chiếu Con D8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (39, 4, N'Rạp Chiếu Con D9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (40, 4, N'Rạp Chiếu Con D10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (41, 5, N'Rạp Chiếu Con E1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (42, 5, N'Rạp Chiếu Con E2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (43, 5, N'Rạp Chiếu Con E3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (44, 5, N'Rạp Chiếu Con E4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (45, 5, N'Rạp Chiếu Con E5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (46, 5, N'Rạp Chiếu Con E6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (47, 5, N'Rạp Chiếu Con E7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (48, 5, N'Rạp Chiếu Con E8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (49, 5, N'Rạp Chiếu Con E9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (50, 5, N'Rạp Chiếu Con E10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (51, 6, N'Rạp Chiếu Con F1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (52, 6, N'Rạp Chiếu Con F2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (53, 6, N'Rạp Chiếu Con F3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (54, 6, N'Rạp Chiếu Con F4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (55, 6, N'Rạp Chiếu Con F5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (56, 6, N'Rạp Chiếu Con F6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (57, 6, N'Rạp Chiếu Con F7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (58, 6, N'Rạp Chiếu Con F8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (59, 6, N'Rạp Chiếu Con F9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (60, 6, N'Rạp Chiếu Con F10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (61, 7, N'Rạp Chiếu Con G1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (62, 7, N'Rạp Chiếu Con G2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (63, 7, N'Rạp Chiếu Con G3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (64, 7, N'Rạp Chiếu Con G4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (65, 7, N'Rạp Chiếu Con G5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (66, 7, N'Rạp Chiếu Con G6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (67, 7, N'Rạp Chiếu Con G7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (68, 7, N'Rạp Chiếu Con G8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (69, 7, N'Rạp Chiếu Con G9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (70, 7, N'Rạp Chiếu Con G10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (71, 8, N'Rạp Chiếu Con H1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (72, 8, N'Rạp Chiếu Con H2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (73, 8, N'Rạp Chiếu Con H3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (74, 8, N'Rạp Chiếu Con H4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (75, 8, N'Rạp Chiếu Con H5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (76, 8, N'Rạp Chiếu Con H6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (77, 8, N'Rạp Chiếu Con H7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (78, 8, N'Rạp Chiếu Con H8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (79, 8, N'Rạp Chiếu Con H9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (80, 8, N'Rạp Chiếu Con H10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (81, 9, N'Rạp Chiếu Con I1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (82, 9, N'Rạp Chiếu Con I2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (83, 9, N'Rạp Chiếu Con I3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (84, 9, N'Rạp Chiếu Con I4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (85, 9, N'Rạp Chiếu Con I5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (86, 9, N'Rạp Chiếu Con I6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (87, 9, N'Rạp Chiếu Con I7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (88, 9, N'Rạp Chiếu Con I8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (89, 9, N'Rạp Chiếu Con I9')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (90, 9, N'Rạp Chiếu Con I10')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (91, 10, N'Rạp Chiếu Con J1')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (92, 10, N'Rạp Chiếu Con J2')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (93, 10, N'Rạp Chiếu Con J3')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (94, 10, N'Rạp Chiếu Con J4')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (95, 10, N'Rạp Chiếu Con J5')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (96, 10, N'Rạp Chiếu Con J6')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (97, 10, N'Rạp Chiếu Con J7')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (98, 10, N'Rạp Chiếu Con J8')
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (99, 10, N'Rạp Chiếu Con J9')
GO
INSERT [dbo].[RapChieuCon] ([MaRapChieuCon], [MaRapChieu], [TenRapChieuCon]) VALUES (100, 10, N'Rạp Chiếu Con J10')
SET IDENTITY_INSERT [dbo].[RapChieuCon] OFF
GO
SET IDENTITY_INSERT [dbo].[ThanhToan] ON 

INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (1, 1, N'QR1234567890', 100000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (2, 2, N'QR2345678901', 120000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (3, 3, N'QR3456789012', 90000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (4, 4, N'QR4567890123', 110000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (5, 5, N'QR5678901234', 95000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (6, 6, N'QR6789012345', 105000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (7, 7, N'QR7890123456', 115000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (8, 8, N'QR8901234567', 125000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (9, 9, N'QR9012345678', 130000)
INSERT [dbo].[ThanhToan] ([MaThanhToan], [MaVe], [QRThanhToan], [TongTien]) VALUES (10, 10, N'QR0123456789', 100000)
SET IDENTITY_INSERT [dbo].[ThanhToan] OFF
GO
SET IDENTITY_INSERT [dbo].[TheLoai] ON 

INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (1, 1, 1)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (2, 2, 1)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (3, 2, 2)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (4, 3, 2)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (5, 3, 3)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (6, 4, 3)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (7, 1, 4)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (8, 3, 4)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (9, 4, 5)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (10, 5, 5)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (11, 1, 6)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (12, 2, 6)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (13, 3, 7)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (14, 4, 7)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (15, 2, 8)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (16, 5, 8)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (17, 1, 9)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (18, 4, 9)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (19, 5, 10)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (20, 2, 10)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (21, 3, 11)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (22, 1, 11)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (23, 4, 12)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (24, 2, 12)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (25, 5, 13)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (26, 3, 13)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (27, 1, 14)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (28, 5, 14)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (29, 2, 15)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (30, 3, 15)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (31, 4, 16)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (32, 1, 16)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (33, 5, 17)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (34, 2, 17)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (35, 3, 18)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (36, 1, 18)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (37, 4, 19)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (38, 5, 19)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (39, 1, 20)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (40, 2, 20)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (41, 3, 21)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (42, 4, 21)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (43, 2, 22)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (44, 5, 22)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (45, 1, 23)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (46, 4, 23)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (47, 5, 24)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (48, 2, 24)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (49, 3, 25)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (50, 1, 25)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (51, 4, 26)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (52, 2, 26)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (53, 5, 27)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (54, 3, 27)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (55, 1, 28)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (56, 5, 28)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (57, 2, 29)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (58, 3, 29)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (59, 4, 30)
INSERT [dbo].[TheLoai] ([MaTheLoai], [MaTheLoaiCha], [MaPhim]) VALUES (60, 1, 30)
SET IDENTITY_INSERT [dbo].[TheLoai] OFF
GO
SET IDENTITY_INSERT [dbo].[TheLoaiCha] ON 

INSERT [dbo].[TheLoaiCha] ([MaTheLoaiCha], [TenTheLoai]) VALUES (1, N'Hài hước')
INSERT [dbo].[TheLoaiCha] ([MaTheLoaiCha], [TenTheLoai]) VALUES (2, N'Kinh dị')
INSERT [dbo].[TheLoaiCha] ([MaTheLoaiCha], [TenTheLoai]) VALUES (3, N'Tâm lý')
INSERT [dbo].[TheLoaiCha] ([MaTheLoaiCha], [TenTheLoai]) VALUES (4, N'Hành động')
INSERT [dbo].[TheLoaiCha] ([MaTheLoaiCha], [TenTheLoai]) VALUES (5, N'Viễn tưởng')
SET IDENTITY_INSERT [dbo].[TheLoaiCha] OFF
GO
SET IDENTITY_INSERT [dbo].[ThoiGianChieu] ON 

INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (1, N'Thứ 2', CAST(N'2024-12-17T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (2, N'Thứ 3', CAST(N'2024-12-18T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (3, N'Thứ 4', CAST(N'2024-12-19T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (4, N'Thứ 5', CAST(N'2024-12-20T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (5, N'Thứ 6', CAST(N'2024-12-21T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (6, N'Thứ 7', CAST(N'2024-12-22T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (7, N'C.Nhật', CAST(N'2024-12-23T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (8, N'Thứ 2', CAST(N'2024-12-24T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (9, N'Thứ 3', CAST(N'2024-12-25T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (10, N'Thứ 4', CAST(N'2024-12-26T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (11, N'Thứ 5', CAST(N'2024-12-27T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (12, N'Thứ 6', CAST(N'2024-12-28T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (13, N'Thứ 7', CAST(N'2024-12-29T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[ThoiGianChieu] ([MaThoiGianChieu], [KieuNgay], [NgayChieu]) VALUES (14, N'C.Nhật', CAST(N'2024-12-30T00:00:00.0000000' AS DateTime2))
SET IDENTITY_INSERT [dbo].[ThoiGianChieu] OFF
GO
SET IDENTITY_INSERT [dbo].[TinhThanhPho] ON 

INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (1, N'Hà Nội')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (2, N'Hồ Chí Minh')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (3, N'Hải Phòng')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (4, N'Đà Nẵng')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (5, N'Cần Thơ')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (6, N'An Giang')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (7, N'Bà Rịa - Vũng Tàu')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (8, N'Bắc Giang')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (9, N'Bắc Kạn')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (10, N'Bạc Liêu')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (11, N'Bắc Ninh')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (12, N'Bến Tre')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (13, N'Bình Định')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (14, N'Bình Dương')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (15, N'Bình Phước')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (16, N'Bình Thuận')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (17, N'Cà Mau')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (18, N'Cao Bằng')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (19, N'Đắk Lắk')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (20, N'Đắk Nông')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (21, N'Điện Biên')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (22, N'Đồng Nai')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (23, N'Đồng Tháp')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (24, N'Gia Lai')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (25, N'Hà Giang')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (26, N'Hà Nam')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (27, N'Hà Tĩnh')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (28, N'Hải Dương')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (29, N'Hậu Giang')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (30, N'Hòa Bình')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (31, N'Hưng Yên')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (32, N'Khánh Hòa')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (33, N'Kiên Giang')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (34, N'Kon Tum')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (35, N'Lai Châu')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (36, N'Lâm Đồng')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (37, N'Lạng Sơn')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (38, N'Lào Cai')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (39, N'Long An')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (40, N'Nam Định')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (41, N'Nghệ An')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (42, N'Ninh Bình')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (43, N'Ninh Thuận')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (44, N'Phú Thọ')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (45, N'Phú Yên')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (46, N'Quảng Bình')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (47, N'Quảng Nam')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (48, N'Quảng Ngãi')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (49, N'Quảng Ninh')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (50, N'Quảng Trị')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (51, N'Sóc Trăng')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (52, N'Sơn La')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (53, N'Tây Ninh')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (54, N'Thái Bình')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (55, N'Thái Nguyên')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (56, N'Thanh Hóa')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (57, N'Thừa Thiên - Huế')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (58, N'Tiền Giang')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (59, N'Trà Vinh')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (60, N'Tuyên Quang')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (61, N'Vĩnh Long')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (62, N'Vĩnh Phúc')
INSERT [dbo].[TinhThanhPho] ([MaTinhThanh], [TenTinhThanh]) VALUES (63, N'Yên Bái')
SET IDENTITY_INSERT [dbo].[TinhThanhPho] OFF
GO
SET IDENTITY_INSERT [dbo].[TinhTrangVe] ON 

INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (1, 1, N'Đã sử dụng', CAST(N'2024-12-04T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (2, 2, N'Chưa sử dụng', CAST(N'2024-12-04T10:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (3, 3, N'Đã khứ hồi', CAST(N'2024-12-04T11:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (4, 4, N'Đã sử dụng', CAST(N'2024-12-04T11:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (5, 5, N'Chưa sử dụng', CAST(N'2024-12-04T12:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (6, 6, N'Đã khứ hồi', CAST(N'2024-12-04T12:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (7, 7, N'Đã sử dụng', CAST(N'2024-12-04T13:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (8, 8, N'Chưa sử dụng', CAST(N'2024-12-04T13:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (9, 9, N'Đã khứ hồi', CAST(N'2024-12-04T14:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (10, 10, N'Đã sử dụng', CAST(N'2024-12-04T14:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (11, 11, N'Chưa sử dụng', CAST(N'2024-12-04T15:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (12, 12, N'Đã khứ hồi', CAST(N'2024-12-04T15:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (13, 13, N'Đã sử dụng', CAST(N'2024-12-04T16:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (14, 14, N'Chưa sử dụng', CAST(N'2024-12-04T16:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (15, 15, N'Đã khứ hồi', CAST(N'2024-12-04T17:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (16, 16, N'Đã sử dụng', CAST(N'2024-12-04T17:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (17, 17, N'Chưa sử dụng', CAST(N'2024-12-04T18:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (18, 18, N'Đã khứ hồi', CAST(N'2024-12-04T18:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (19, 19, N'Đã sử dụng', CAST(N'2024-12-04T19:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (20, 20, N'Chưa sử dụng', CAST(N'2024-12-04T19:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (21, 21, N'Đã khứ hồi', CAST(N'2024-12-04T20:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (22, 22, N'Đã sử dụng', CAST(N'2024-12-04T20:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (23, 23, N'Chưa sử dụng', CAST(N'2024-12-04T21:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (24, 24, N'Đã khứ hồi', CAST(N'2024-12-04T21:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (25, 25, N'Đã sử dụng', CAST(N'2024-12-04T22:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (26, 26, N'Chưa sử dụng', CAST(N'2024-12-04T22:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (27, 27, N'Đã khứ hồi', CAST(N'2024-12-04T23:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (28, 28, N'Đã sử dụng', CAST(N'2024-12-04T23:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (29, 29, N'Chưa sử dụng', CAST(N'2024-12-05T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (30, 30, N'Đã khứ hồi', CAST(N'2024-12-05T00:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (31, 31, N'Đã sử dụng', CAST(N'2024-12-05T01:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (32, 32, N'Chưa sử dụng', CAST(N'2024-12-05T01:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (33, 33, N'Đã khứ hồi', CAST(N'2024-12-05T02:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (34, 34, N'Đã sử dụng', CAST(N'2024-12-05T02:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (35, 35, N'Chưa sử dụng', CAST(N'2024-12-05T03:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (36, 36, N'Đã khứ hồi', CAST(N'2024-12-05T03:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (37, 37, N'Đã sử dụng', CAST(N'2024-12-05T04:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (38, 38, N'Chưa sử dụng', CAST(N'2024-12-05T04:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (39, 39, N'Đã khứ hồi', CAST(N'2024-12-05T05:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (40, 40, N'Đã sử dụng', CAST(N'2024-12-05T05:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (41, 41, N'Chưa sử dụng', CAST(N'2024-12-05T06:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (42, 42, N'Đã khứ hồi', CAST(N'2024-12-05T06:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (43, 43, N'Đã sử dụng', CAST(N'2024-12-05T07:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (44, 44, N'Chưa sử dụng', CAST(N'2024-12-05T07:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (45, 45, N'Đã khứ hồi', CAST(N'2024-12-05T08:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (46, 46, N'Đã sử dụng', CAST(N'2024-12-05T08:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (47, 47, N'Chưa sử dụng', CAST(N'2024-12-05T09:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (48, 48, N'Đã khứ hồi', CAST(N'2024-12-05T09:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (49, 49, N'Đã sử dụng', CAST(N'2024-12-05T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (50, 50, N'Chưa sử dụng', CAST(N'2024-12-05T10:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (51, 51, N'Đã khứ hồi', CAST(N'2024-12-05T11:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (52, 52, N'Đã sử dụng', CAST(N'2024-12-05T11:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (53, 53, N'Chưa sử dụng', CAST(N'2024-12-05T12:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (54, 54, N'Đã khứ hồi', CAST(N'2024-12-05T12:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (55, 55, N'Đã sử dụng', CAST(N'2024-12-05T13:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (56, 56, N'Chưa sử dụng', CAST(N'2024-12-05T13:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (57, 57, N'Đã khứ hồi', CAST(N'2024-12-05T14:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (58, 58, N'Đã sử dụng', CAST(N'2024-12-05T14:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (59, 59, N'Chưa sử dụng', CAST(N'2024-12-05T15:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (60, 60, N'Đã khứ hồi', CAST(N'2024-12-05T15:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (61, 61, N'Đã sử dụng', CAST(N'2024-12-05T16:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (62, 62, N'Chưa sử dụng', CAST(N'2024-12-05T16:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (63, 63, N'Đã khứ hồi', CAST(N'2024-12-05T17:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (64, 64, N'Đã sử dụng', CAST(N'2024-12-05T17:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (65, 65, N'Chưa sử dụng', CAST(N'2024-12-05T18:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (66, 66, N'Đã khứ hồi', CAST(N'2024-12-05T18:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (67, 67, N'Đã sử dụng', CAST(N'2024-12-05T19:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (68, 68, N'Chưa sử dụng', CAST(N'2024-12-05T19:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (69, 69, N'Đã khứ hồi', CAST(N'2024-12-05T20:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (70, 70, N'Đã sử dụng', CAST(N'2024-12-05T20:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (71, 71, N'Chưa sử dụng', CAST(N'2024-12-05T21:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (72, 72, N'Đã khứ hồi', CAST(N'2024-12-05T21:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (73, 73, N'Đã sử dụng', CAST(N'2024-12-05T22:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (74, 74, N'Chưa sử dụng', CAST(N'2024-12-05T22:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (75, 75, N'Đã khứ hồi', CAST(N'2024-12-05T23:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (76, 76, N'Đã sử dụng', CAST(N'2024-12-05T23:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (77, 77, N'Chưa sử dụng', CAST(N'2024-12-06T00:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (78, 78, N'Đã khứ hồi', CAST(N'2024-12-06T00:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (79, 79, N'Đã sử dụng', CAST(N'2024-12-06T01:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (80, 80, N'Chưa sử dụng', CAST(N'2024-12-06T01:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (81, 81, N'Đã khứ hồi', CAST(N'2024-12-06T02:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (82, 82, N'Đã sử dụng', CAST(N'2024-12-06T02:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (83, 83, N'Chưa sử dụng', CAST(N'2024-12-06T03:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (84, 84, N'Đã khứ hồi', CAST(N'2024-12-06T03:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (85, 85, N'Đã sử dụng', CAST(N'2024-12-06T04:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (86, 86, N'Chưa sử dụng', CAST(N'2024-12-06T04:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (87, 87, N'Đã khứ hồi', CAST(N'2024-12-06T05:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (88, 88, N'Đã sử dụng', CAST(N'2024-12-06T05:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (89, 89, N'Chưa sử dụng', CAST(N'2024-12-06T06:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (90, 90, N'Đã khứ hồi', CAST(N'2024-12-06T06:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (91, 91, N'Đã sử dụng', CAST(N'2024-12-06T07:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (92, 92, N'Chưa sử dụng', CAST(N'2024-12-06T07:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (93, 93, N'Đã khứ hồi', CAST(N'2024-12-06T08:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (94, 94, N'Đã sử dụng', CAST(N'2024-12-06T08:30:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (95, 95, N'Chưa sử dụng', CAST(N'2024-12-06T09:00:00.0000000' AS DateTime2))
INSERT [dbo].[TinhTrangVe] ([MaTinhTrang], [MaVe], [TinhTrang], [ThoiGian]) VALUES (96, 96, N'Đã khứ hồi', CAST(N'2024-12-06T09:30:00.0000000' AS DateTime2))
SET IDENTITY_INSERT [dbo].[TinhTrangVe] OFF
GO
SET IDENTITY_INSERT [dbo].[VePhim] ON 

INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (1, 1, 1, 1, 5, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (2, 2, 2, 2, 6, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (3, 3, 3, 1, 7, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (4, 4, 1, 3, 8, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (5, 5, 2, 2, 9, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (6, 6, 3, 1, 10, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (7, 7, 1, 2, 11, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (8, 8, 2, 1, 12, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (9, 9, 3, 2, 13, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (10, 10, 1, 1, 14, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (11, 11, 2, 3, 15, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (12, 12, 3, 1, 16, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (13, 13, 1, 2, 17, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (14, 14, 2, 2, 18, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (15, 15, 3, 1, 19, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (16, 16, 1, 3, 20, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (17, 17, 2, 1, 21, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (18, 18, 3, 2, 22, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (19, 19, 1, 2, 23, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (20, 20, 2, 1, 24, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (21, 21, 3, 3, 25, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (22, 22, 1, 1, 26, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (23, 23, 2, 2, 27, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (24, 24, 3, 1, 28, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (25, 25, 1, 3, 29, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (26, 26, 2, 1, 30, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (27, 27, 3, 2, 31, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (28, 28, 1, 1, 32, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (29, 29, 2, 3, 33, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (30, 30, 3, 1, 34, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (31, 31, 1, 2, 35, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (32, 32, 2, 1, 36, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (33, 33, 3, 3, 37, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (34, 34, 1, 1, 38, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (35, 35, 2, 2, 39, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (36, 36, 3, 1, 40, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (37, 37, 1, 2, 41, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (38, 38, 2, 1, 42, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (39, 39, 3, 3, 43, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (40, 40, 1, 1, 44, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (41, 41, 2, 2, 45, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (42, 42, 3, 1, 46, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (43, 43, 1, 3, 47, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (44, 44, 2, 1, 48, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (45, 45, 3, 2, 49, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (46, 46, 1, 1, 50, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (47, 47, 2, 2, 51, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (48, 48, 3, 3, 52, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (49, 49, 1, 1, 53, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (50, 50, 2, 2, 54, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (51, 51, 3, 1, 55, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (52, 52, 1, 2, 56, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (53, 53, 2, 1, 57, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (54, 54, 3, 3, 58, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (55, 55, 1, 2, 59, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (56, 56, 2, 1, 60, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (57, 57, 3, 2, 61, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (58, 58, 1, 1, 62, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (59, 59, 2, 3, 63, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (60, 60, 3, 1, 64, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (61, 61, 1, 2, 65, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (62, 62, 2, 1, 66, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (63, 63, 3, 2, 67, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (64, 64, 1, 1, 68, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (65, 65, 2, 3, 69, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (66, 66, 3, 1, 70, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (67, 67, 1, 2, 71, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (68, 68, 2, 1, 72, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (69, 69, 3, 3, 73, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (70, 70, 1, 2, 74, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (71, 71, 2, 1, 75, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (72, 72, 3, 1, 76, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (73, 73, 1, 3, 77, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (74, 74, 2, 1, 78, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (75, 75, 3, 2, 79, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (76, 76, 1, 1, 80, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (77, 77, 2, 3, 81, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (78, 78, 3, 1, 82, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (79, 79, 1, 2, 83, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (80, 80, 2, 1, 84, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (81, 81, 3, 3, 85, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (82, 82, 1, 2, 86, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (83, 83, 2, 1, 87, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (84, 84, 3, 2, 88, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (85, 85, 1, 1, 89, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (86, 86, 2, 3, 90, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (87, 87, 3, 1, 91, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (88, 88, 1, 2, 92, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (89, 89, 2, 1, 93, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (90, 90, 3, 3, 94, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (91, 91, 1, 2, 95, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (92, 92, 2, 1, 96, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (93, 93, 3, 1, 97, 1)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (94, 94, 1, 3, 98, 2)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (95, 95, 2, 2, 99, 3)
INSERT [dbo].[VePhim] ([MaVe], [MaLichChieu], [MaKhachHang], [SoLuongVe], [GheNgoi], [PhongChieu]) VALUES (96, 96, 3, 1, 100, 1)
SET IDENTITY_INSERT [dbo].[VePhim] OFF
GO
SET IDENTITY_INSERT [dbo].[VoucherCuaToi] ON 

INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (1, 1, N'Voucher1', 1, N'Đã khứ hồi', 20, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 2, 1)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (2, 2, N'Voucher2', 2, N'Giá tiền', 50000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 3, 1)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (3, 3, N'Voucher3', 3, N'Đã khứ hồi', 15, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 5, 0)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (4, 1, N'Voucher4', 1, N'Giá tiền', 30000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 2, 0)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (5, 2, N'Voucher5', 2, N'Đã khứ hồi', 10, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 1, 0)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (6, 3, N'Voucher6', 3, N'Giá tiền', 20000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 8, 7)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (7, 1, N'Voucher7', 1, N'Đã khứ hồi', 25, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 10, 8)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (8, 2, N'Voucher8', 2, N'Giá tiền', 40000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 7, 4)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (9, 3, N'Voucher9', 3, N'Đã khứ hồi', 30, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 4, 0)
INSERT [dbo].[VoucherCuaToi] ([MaVoucherCuaToi], [MaKhachHang], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (10, 1, N'Voucher10', 1, N'Giá tiền', 35000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 2, 0)
SET IDENTITY_INSERT [dbo].[VoucherCuaToi] OFF
GO
SET IDENTITY_INSERT [dbo].[VoucherDoiTac] ON 

INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (1, 1, N'VOUCHER10NEW', 1, N'Đã khứ hồi', 10, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 100, 50)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (2, 2, N'VOUCHER15MINIMUM', 2, N'Đã khứ hồi', 15, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 150, 100)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (3, 3, N'VOUCHER20LARGE', 2, N'Đã khứ hồi', 20, CAST(N'2025-01-15T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 50, 27)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (4, 4, N'VOUCHER30VIP', 3, N'Giá tiền', 30000, CAST(N'2025-02-01T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 200, 196)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (5, 5, N'VOUCHER5OLD', 1, N'Giá tiền', 5000, CAST(N'2024-12-15T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 120, 92)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (6, 6, N'VOUCHER25HOLIDAY', 1, N'Đã khứ hồi', 25, CAST(N'2024-12-25T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 80, 76)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (7, 7, N'VOUCHER40NEWCUSTOMER', 1, N'Đã khứ hồi', 40, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 100, 12)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (8, 8, N'VOUCHER50MINIMUMORDER', 2, N'Giá tiền', 50000, CAST(N'2025-01-01T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 30, 7)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (9, 9, N'VOUCHER10VIPCUSTOMER', 3, N'Đã khứ hồi', 10, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 200, 183)
INSERT [dbo].[VoucherDoiTac] ([MaVoucherDoiTac], [MaRapChieu], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (10, 10, N'VOUCHER15EARLYBIRD', 2, N'Giá tiền', 15000, CAST(N'2025-01-10T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 150, 129)
SET IDENTITY_INSERT [dbo].[VoucherDoiTac] OFF
GO
SET IDENTITY_INSERT [dbo].[VoucherUngDung] ON 

INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (1, N'logo', N'VoucherApp1', 1, N'Đã khứ hồi', 10, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 100, 98)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (2, N'logo', N'VoucherApp2', 2, N'Giá tiền', 50000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 200, 99)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (3, N'logo', N'VoucherApp3', 3, N'Đã khứ hồi', 25, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 150, 100)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (4, N'logo', N'VoucherApp4', 4, N'Giá tiền', 100000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 75, 35)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (5, N'logo', N'VoucherApp5', 5, N'Đã khứ hồi', 15, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 200, 99)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (6, N'logo', N'VoucherApp6', 1, N'Giá tiền', 20000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 250, 25)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (7, N'logo', N'VoucherApp7', 2, N'Đã khứ hồi', 30, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 300, 296)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (8, N'logo', N'VoucherApp8', 3, N'Giá tiền', 70000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 120, 12)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (9, N'logo', N'VoucherApp9', 4, N'Đã khứ hồi', 20, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 400, 383)
INSERT [dbo].[VoucherUngDung] ([MaVoucherUngDung], [AnhUngDung], [TenVoucher], [MaDoiTuongApDung], [TrangThaiGiam], [MucGiam], [HanSuDung], [TrangThaiSuDung], [SoLuongToiDa], [SoLuongSuDung]) VALUES (10, N'logo', N'VoucherApp10', 5, N'Giá tiền', 150000, CAST(N'2024-12-31T00:00:00.0000000' AS DateTime2), N'Chưa sử dụng', 500, 432)
SET IDENTITY_INSERT [dbo].[VoucherUngDung] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_KhachHang_Email]    Script Date: 22/12/2024 22:41:06 ******/
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [UQ_KhachHang_Email] UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_VoucherCuaToi_TenVoucher]    Script Date: 22/12/2024 22:41:06 ******/
ALTER TABLE [dbo].[VoucherCuaToi] ADD  CONSTRAINT [UQ_VoucherCuaToi_TenVoucher] UNIQUE NONCLUSTERED 
(
	[TenVoucher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_VoucherDoiTac_TenVoucher]    Script Date: 22/12/2024 22:41:06 ******/
ALTER TABLE [dbo].[VoucherDoiTac] ADD  CONSTRAINT [UQ_VoucherDoiTac_TenVoucher] UNIQUE NONCLUSTERED 
(
	[TenVoucher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_VoucherUngDung_TenVoucher]    Script Date: 22/12/2024 22:41:06 ******/
ALTER TABLE [dbo].[VoucherUngDung] ADD  CONSTRAINT [UQ_VoucherUngDung_TenVoucher] UNIQUE NONCLUSTERED 
(
	[TenVoucher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[DanhGia] ADD  DEFAULT ((0)) FOR [LuotThich]
GO
ALTER TABLE [dbo].[VePhim] ADD  DEFAULT ((1)) FOR [SoLuongVe]
GO
ALTER TABLE [dbo].[VoucherCuaToi] ADD  DEFAULT ((0)) FOR [SoLuongSuDung]
GO
ALTER TABLE [dbo].[VoucherDoiTac] ADD  DEFAULT ((0)) FOR [SoLuongSuDung]
GO
ALTER TABLE [dbo].[VoucherUngDung] ADD  DEFAULT ((0)) FOR [SoLuongSuDung]
GO
ALTER TABLE [dbo].[ChiTietCapBac]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietCapBac_CapBacChiTieu] FOREIGN KEY([MaCapBacChiTieu])
REFERENCES [dbo].[CapBacChiTieu] ([MaCapBacChiTieu])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ChiTietCapBac] CHECK CONSTRAINT [FK_ChiTietCapBac_CapBacChiTieu]
GO
ALTER TABLE [dbo].[ChiTietCapBac]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietCapBac_KhachHang] FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ChiTietCapBac] CHECK CONSTRAINT [FK_ChiTietCapBac_KhachHang]
GO
ALTER TABLE [dbo].[ChiTietLichChieu]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietLichChieu_LichChieu] FOREIGN KEY([MaLichChieu])
REFERENCES [dbo].[LichChieu] ([MaLichChieu])
GO
ALTER TABLE [dbo].[ChiTietLichChieu] CHECK CONSTRAINT [FK_ChiTietLichChieu_LichChieu]
GO
ALTER TABLE [dbo].[ChiTietLichChieu]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietLichChieu_ThoiGianChieu] FOREIGN KEY([MaThoiGianChieu])
REFERENCES [dbo].[ThoiGianChieu] ([MaThoiGianChieu])
GO
ALTER TABLE [dbo].[ChiTietLichChieu] CHECK CONSTRAINT [FK_ChiTietLichChieu_ThoiGianChieu]
GO
ALTER TABLE [dbo].[DanhGia]  WITH CHECK ADD  CONSTRAINT [FK_DanhGia_KhachHang] FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[DanhGia] CHECK CONSTRAINT [FK_DanhGia_KhachHang]
GO
ALTER TABLE [dbo].[DanhGia]  WITH CHECK ADD  CONSTRAINT [FK_DanhGia_Phim] FOREIGN KEY([MaPhim])
REFERENCES [dbo].[Phim] ([MaPhim])
GO
ALTER TABLE [dbo].[DanhGia] CHECK CONSTRAINT [FK_DanhGia_Phim]
GO
ALTER TABLE [dbo].[DanhGiaRapChieu]  WITH CHECK ADD  CONSTRAINT [FK_DanhGiaRapChieu_KhachHang] FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[DanhGiaRapChieu] CHECK CONSTRAINT [FK_DanhGiaRapChieu_KhachHang]
GO
ALTER TABLE [dbo].[DanhGiaRapChieu]  WITH CHECK ADD  CONSTRAINT [FK_DanhGiaRapChieu_RapChieuCon] FOREIGN KEY([MaRapChieuCon])
REFERENCES [dbo].[RapChieuCon] ([MaRapChieuCon])
GO
ALTER TABLE [dbo].[DanhGiaRapChieu] CHECK CONSTRAINT [FK_DanhGiaRapChieu_RapChieuCon]
GO
ALTER TABLE [dbo].[DiaChiRapChieuCon]  WITH CHECK ADD  CONSTRAINT [FK_DiaChiRapChieuCon_RapChieuCon] FOREIGN KEY([MaRapChieuCon])
REFERENCES [dbo].[RapChieuCon] ([MaRapChieuCon])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DiaChiRapChieuCon] CHECK CONSTRAINT [FK_DiaChiRapChieuCon_RapChieuCon]
GO
ALTER TABLE [dbo].[DiaChiRapChieuCon]  WITH CHECK ADD  CONSTRAINT [FK_DiaChiRapChieuCon_TinhThanhPho] FOREIGN KEY([MaTinhThanh])
REFERENCES [dbo].[TinhThanhPho] ([MaTinhThanh])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DiaChiRapChieuCon] CHECK CONSTRAINT [FK_DiaChiRapChieuCon_TinhThanhPho]
GO
ALTER TABLE [dbo].[LichChieu]  WITH CHECK ADD  CONSTRAINT [FK_LichChieu_Phim] FOREIGN KEY([MaPhim])
REFERENCES [dbo].[Phim] ([MaPhim])
GO
ALTER TABLE [dbo].[LichChieu] CHECK CONSTRAINT [FK_LichChieu_Phim]
GO
ALTER TABLE [dbo].[LichChieu]  WITH CHECK ADD  CONSTRAINT [FK_LichChieu_RapChieuCon] FOREIGN KEY([MaRapChieuCon])
REFERENCES [dbo].[RapChieuCon] ([MaRapChieuCon])
GO
ALTER TABLE [dbo].[LichChieu] CHECK CONSTRAINT [FK_LichChieu_RapChieuCon]
GO
ALTER TABLE [dbo].[RapChieuCon]  WITH CHECK ADD  CONSTRAINT [FK_RapChieuCon_RapChieu] FOREIGN KEY([MaRapChieu])
REFERENCES [dbo].[RapChieu] ([MaRapChieu])
GO
ALTER TABLE [dbo].[RapChieuCon] CHECK CONSTRAINT [FK_RapChieuCon_RapChieu]
GO
ALTER TABLE [dbo].[ThanhToan]  WITH CHECK ADD  CONSTRAINT [FK_ThanhToan_VePhim] FOREIGN KEY([MaVe])
REFERENCES [dbo].[VePhim] ([MaVe])
GO
ALTER TABLE [dbo].[ThanhToan] CHECK CONSTRAINT [FK_ThanhToan_VePhim]
GO
ALTER TABLE [dbo].[TheLoai]  WITH CHECK ADD  CONSTRAINT [FK_TheLoai_Phim] FOREIGN KEY([MaPhim])
REFERENCES [dbo].[Phim] ([MaPhim])
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[TheLoai] CHECK CONSTRAINT [FK_TheLoai_Phim]
GO
ALTER TABLE [dbo].[TheLoai]  WITH CHECK ADD  CONSTRAINT [FK_TheLoai_TheLoaiCha] FOREIGN KEY([MaTheLoaiCha])
REFERENCES [dbo].[TheLoaiCha] ([MaTheLoaiCha])
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[TheLoai] CHECK CONSTRAINT [FK_TheLoai_TheLoaiCha]
GO
ALTER TABLE [dbo].[TinhTrangVe]  WITH CHECK ADD  CONSTRAINT [FK_TinhTrangVe_VePhim] FOREIGN KEY([MaVe])
REFERENCES [dbo].[VePhim] ([MaVe])
GO
ALTER TABLE [dbo].[TinhTrangVe] CHECK CONSTRAINT [FK_TinhTrangVe_VePhim]
GO
ALTER TABLE [dbo].[VePhim]  WITH CHECK ADD  CONSTRAINT [FK_VePhim_KhachHang] FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[VePhim] CHECK CONSTRAINT [FK_VePhim_KhachHang]
GO
ALTER TABLE [dbo].[VePhim]  WITH CHECK ADD  CONSTRAINT [FK_VePhim_LichChieu] FOREIGN KEY([MaLichChieu])
REFERENCES [dbo].[LichChieu] ([MaLichChieu])
GO
ALTER TABLE [dbo].[VePhim] CHECK CONSTRAINT [FK_VePhim_LichChieu]
GO
ALTER TABLE [dbo].[VoucherCuaToi]  WITH CHECK ADD  CONSTRAINT [FK_VoucherCuaToi_DoiTuongApDung] FOREIGN KEY([MaDoiTuongApDung])
REFERENCES [dbo].[DoiTuongApDung] ([MaDoiTuongApDung])
GO
ALTER TABLE [dbo].[VoucherCuaToi] CHECK CONSTRAINT [FK_VoucherCuaToi_DoiTuongApDung]
GO
ALTER TABLE [dbo].[VoucherCuaToi]  WITH CHECK ADD  CONSTRAINT [FK_VoucherCuaToi_KhachHang] FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[KhachHang] ([MaKhachHang])
GO
ALTER TABLE [dbo].[VoucherCuaToi] CHECK CONSTRAINT [FK_VoucherCuaToi_KhachHang]
GO
ALTER TABLE [dbo].[VoucherDoiTac]  WITH CHECK ADD  CONSTRAINT [FK_VoucherDoiTac_DoiTuongApDung] FOREIGN KEY([MaDoiTuongApDung])
REFERENCES [dbo].[DoiTuongApDung] ([MaDoiTuongApDung])
GO
ALTER TABLE [dbo].[VoucherDoiTac] CHECK CONSTRAINT [FK_VoucherDoiTac_DoiTuongApDung]
GO
ALTER TABLE [dbo].[VoucherDoiTac]  WITH CHECK ADD  CONSTRAINT [FK_VoucherDoiTac_RapChieu] FOREIGN KEY([MaRapChieu])
REFERENCES [dbo].[RapChieu] ([MaRapChieu])
GO
ALTER TABLE [dbo].[VoucherDoiTac] CHECK CONSTRAINT [FK_VoucherDoiTac_RapChieu]
GO
ALTER TABLE [dbo].[VoucherUngDung]  WITH CHECK ADD  CONSTRAINT [FK_VoucherUngDung_DoiTuongApDung] FOREIGN KEY([MaDoiTuongApDung])
REFERENCES [dbo].[DoiTuongApDung] ([MaDoiTuongApDung])
GO
ALTER TABLE [dbo].[VoucherUngDung] CHECK CONSTRAINT [FK_VoucherUngDung_DoiTuongApDung]
GO
ALTER TABLE [dbo].[CapBacChiTieu]  WITH CHECK ADD  CONSTRAINT [CK_HanMucChiTieu_Positive] CHECK  (([HanMucChiTieu]>(0)))
GO
ALTER TABLE [dbo].[CapBacChiTieu] CHECK CONSTRAINT [CK_HanMucChiTieu_Positive]
GO
/****** Object:  StoredProcedure [dbo].[GetChuaDung]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
			CREATE   PROCEDURE [dbo].[GetChuaDung]
				@MaVe INT
			AS
			BEGIN
				-- Trả về thông tin chi tiết của vé chưa sử dụng dựa trên MaVe
				SELECT 
					VC.MaVe,
					P.AnhPhim,  -- Ảnh phim
					P.TenPhim,  -- Tên phim
					FORMAT(TG.NgayChieu, 'dd-MM-yyyy') AS NgayChieu,  -- Ngày chiếu
					CONVERT(VARCHAR(5), CLC.ThoiGianBatDau, 108) AS ThoiGianBatDau,  -- Thời gian bắt đầu (HH:mm)
					CONVERT(VARCHAR(5), CLC.ThoiGianKetThuc, 108) AS ThoiGianKetThuc,  -- Thời gian kết thúc (HH:mm)
					VC.SoLuongVe,  -- Số lượng vé
					RC.AnhRapChieu AS IconRap,  -- Icon rạp
					DC.DiaChiRapChieu AS DiaChi,  -- Địa chỉ rạp
					TT.TongTien AS TienHoan,  -- Tiền hoàn trả
					MAX(TLC.TenTheLoai) AS TenTheLoai  -- Tên thể loại phim
				FROM VePhim VC
				INNER JOIN LichChieu LC ON VC.MaLichChieu = LC.MaLichChieu
				INNER JOIN ChiTietLichChieu CLC ON LC.MaLichChieu = CLC.MaLichChieu
				INNER JOIN ThoiGianChieu TG ON TG.MaThoiGianChieu = CLC.MaThoiGianChieu
				INNER JOIN Phim P ON LC.MaPhim = P.MaPhim
				INNER JOIN TheLoai TL ON P.MaPhim = TL.MaPhim
				INNER JOIN TheLoaiCha TLC ON TL.MaTheLoaiCha = TLC.MaTheLoaiCha
				INNER JOIN RapChieuCon RCC ON LC.MaRapChieuCon = RCC.MaRapChieuCon
				INNER JOIN DiaChiRapChieuCon DC ON RCC.MaRapChieuCon = DC.MaRapChieuCon
				INNER JOIN RapChieu RC ON RCC.MaRapChieu = RC.MaRapChieu
				INNER JOIN ThanhToan TT ON VC.MaVe = TT.MaVe  -- Tiền hoàn trả từ bảng ThanhToan
				WHERE VC.MaVe = @MaVe
				GROUP BY 
					VC.MaVe, P.AnhPhim, P.TenPhim, TG.NgayChieu, CLC.ThoiGianBatDau, CLC.ThoiGianKetThuc, 
					VC.SoLuongVe, RC.AnhRapChieu, DC.DiaChiRapChieu, TT.TongTien;
			END;	
GO
/****** Object:  StoredProcedure [dbo].[GetCustomerDetailsByMaKhachHang]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[GetCustomerDetailsByMaKhachHang]
    @MaKhachHang INT
AS
BEGIN
    SELECT 
        k.TenKhachHang,
        c.TenCapBac,
        c.HanMucChiTieu,
         CONVERT(DATE, cb.ThoiHanReset, 103) AS ThoiHanReset 
    FROM 
        KhachHang k
    JOIN 
        ChiTietCapBac cb ON k.MaKhachHang = cb.MaKhachHang
    JOIN 
        CapBacChiTieu c ON cb.MaCapBacChiTieu = c.MaCapBacChiTieu
    WHERE 
        k.MaKhachHang = @MaKhachHang;
END;
GO
/****** Object:  StoredProcedure [dbo].[GetVePhimDadung]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

			-- GetVePhimDadung
			CREATE   PROCEDURE [dbo].[GetVePhimDadung]
				@MaVe INT
			AS
			BEGIN
				-- Cập nhật tình trạng vé thành 'Đã sử dụng'
				UPDATE TinhTrangVe
				SET TinhTrang = N'Đã sử dụng',
					ThoiGian = GETDATE() -- Cập nhật thời gian hiện tại
				WHERE MaVe = @MaVe;

				-- Trả về thông tin chi tiết của vé sau khi cập nhật
				SELECT 
					VC.MaVe,
					QR.QRThanhToan,
					FORMAT(TV.ThoiGian, 'dd-MM-yyyy HH:mm') AS ThoiGian,  -- Lấy ngày và giờ
					P.AnhPhim,
					P.Tuoi,
					P.TenPhim,
					MAX(TLC.TenTheLoai) AS TenTheLoai,  -- Chỉ hiển thị 1 tên thể loại
					VC.SoLuongVe,
					VC.GheNgoi,
					VC.PhongChieu,
					RC.AnhRapChieu,
					DC.DiaChiRapChieu,
					CONVERT(VARCHAR(5), CLC.ThoiGianBatDau, 108) AS ThoiGianBatDau,  -- Convert to time format HH:mm
					CONVERT(VARCHAR(5), CLC.ThoiGianKetThuc, 108) AS ThoiGianKetThuc,  -- Convert to time format HH:mm
					FORMAT(TG.NgayChieu, 'dd-MM-yyyy') AS NgayChieu,  -- Lấy ngày
					P.DinhDangPhim,
					N'Đã sử dụng' AS TinhTrang 
				FROM VePhim VC
				JOIN ThanhToan QR ON VC.MaVe = QR.MaVe
				INNER JOIN LichChieu LC ON VC.MaLichChieu = LC.MaLichChieu
				INNER JOIN ChiTietLichChieu CLC ON LC.MaLichChieu = CLC.MaLichChieu
				INNER JOIN ThoiGianChieu TG ON TG.MaThoiGianChieu = CLC.MaThoiGianChieu
				INNER JOIN Phim P ON LC.MaPhim = P.MaPhim
				INNER JOIN TheLoai TL ON P.MaPhim = TL.MaPhim
				INNER JOIN TheLoaiCha TLC ON TL.MaTheLoaiCha = TLC.MaTheLoaiCha
				INNER JOIN RapChieuCon RCC ON LC.MaRapChieuCon = RCC.MaRapChieuCon
				INNER JOIN DiaChiRapChieuCon DC ON RCC.MaRapChieuCon = DC.MaRapChieuCon
				INNER JOIN RapChieu RC ON RCC.MaRapChieu = RC.MaRapChieu
				INNER JOIN TinhTrangVe TV ON VC.MaVe = TV.MaVe
				WHERE VC.MaVe = @MaVe
				GROUP BY 
					VC.MaVe, QR.QRThanhToan, TV.ThoiGian, P.AnhPhim, P.Tuoi, P.TenPhim, 
					VC.SoLuongVe, VC.GheNgoi, VC.PhongChieu, RC.AnhRapChieu,
					DC.DiaChiRapChieu, CLC.ThoiGianBatDau, CLC.ThoiGianKetThuc, 
					TG.NgayChieu, P.DinhDangPhim;
			END;
GO
/****** Object:  StoredProcedure [dbo].[GetVePhimDaKhuHoi]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

			-- GetVePhimDaKhuHoi
			CREATE   PROCEDURE [dbo].[GetVePhimDaKhuHoi]
				@MaVe INT
			AS
			BEGIN
				-- Cập nhật tình trạng vé thành 'Đã khứ hồi'
				UPDATE TinhTrangVe
				SET TinhTrang = N'Đã khứ hồi',
					ThoiGian = GETDATE() -- Cập nhật thời gian hiện tại
				WHERE MaVe = @MaVe;

				-- Trả về thông tin chi tiết của vé sau khi cập nhật
				SELECT 
					VC.MaVe,
					QR.QRThanhToan,
					FORMAT(TV.ThoiGian, 'dd-MM-yyyy HH:mm') AS ThoiGian,  -- Lấy ngày và giờ
					P.AnhPhim,
					P.Tuoi,
					P.TenPhim,
					MAX(TLC.TenTheLoai) AS TenTheLoai,  -- Chỉ hiển thị 1 tên thể loại
					VC.SoLuongVe,
					VC.GheNgoi,
					VC.PhongChieu,
					RC.AnhRapChieu,
					DC.DiaChiRapChieu,
					CONVERT(VARCHAR(5), CLC.ThoiGianBatDau, 108) AS ThoiGianBatDau,  -- Convert to time format HH:mm
					CONVERT(VARCHAR(5), CLC.ThoiGianKetThuc, 108) AS ThoiGianKetThuc,  -- Convert to time format HH:mm
					FORMAT(TG.NgayChieu, 'dd-MM-yyyy') AS NgayChieu,  -- Lấy ngày
					P.DinhDangPhim,
					N'Đã khứ hồi' AS TinhTrang
				FROM VePhim VC
				JOIN ThanhToan QR ON VC.MaVe = QR.MaVe
				INNER JOIN LichChieu LC ON VC.MaLichChieu = LC.MaLichChieu
				INNER JOIN ChiTietLichChieu CLC ON LC.MaLichChieu = CLC.MaLichChieu
				INNER JOIN ThoiGianChieu TG ON TG.MaThoiGianChieu = CLC.MaThoiGianChieu
				INNER JOIN Phim P ON LC.MaPhim = P.MaPhim
				INNER JOIN TheLoai TL ON P.MaPhim = TL.MaPhim
				INNER JOIN TheLoaiCha TLC ON TL.MaTheLoaiCha = TLC.MaTheLoaiCha
				INNER JOIN RapChieuCon RCC ON LC.MaRapChieuCon = RCC.MaRapChieuCon
				INNER JOIN DiaChiRapChieuCon DC ON RCC.MaRapChieuCon = DC.MaRapChieuCon
				INNER JOIN RapChieu RC ON RCC.MaRapChieu = RC.MaRapChieu
				INNER JOIN TinhTrangVe TV ON VC.MaVe = TV.MaVe
				WHERE VC.MaVe = @MaVe
				GROUP BY 
					VC.MaVe, QR.QRThanhToan, TV.ThoiGian, P.AnhPhim, P.Tuoi, P.TenPhim, 
					VC.SoLuongVe, VC.GheNgoi, VC.PhongChieu, RC.AnhRapChieu,
					DC.DiaChiRapChieu, CLC.ThoiGianBatDau, CLC.ThoiGianKetThuc, 
					TG.NgayChieu, P.DinhDangPhim;
			END;
GO
/****** Object:  StoredProcedure [dbo].[pr_BinhLuanNoiBat]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_BinhLuanNoiBat]
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
/****** Object:  StoredProcedure [dbo].[pr_Get7NgayChieuFromToday]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_Get7NgayChieuFromToday]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT TOP 7 
		MaThoiGianChieu,
        KieuNgay, 
        FORMAT(NgayChieu, 'dd') AS NgayChieu -- Định dạng ngày thành kiểu VARCHAR
    FROM ThoiGianChieu
    WHERE NgayChieu >= CAST(GETDATE() AS DATE) -- Lấy từ ngày hôm nay trở đi
    GROUP BY MaThoiGianChieu, KieuNgay, FORMAT(NgayChieu, 'dd') -- Nhóm theo KieuNgay và NgàyChieu đã định dạng
    ORDER BY NgayChieu ASC; -- Sắp xếp tăng dần theo ngày
END;
EXEC pr_Get7NgayChieuFromToday
GO
/****** Object:  StoredProcedure [dbo].[pr_GetRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_GetRapChieuCon]
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
/****** Object:  StoredProcedure [dbo].[pr_GetRapChieuConByMaRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_GetRapChieuConByMaRapChieuCon]
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
/****** Object:  StoredProcedure [dbo].[pr_GetRapChieuConByTenRapChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[pr_GetRapChieuConByTenRapChieu]
    @MaTinhThanh INT,
	@MaRapChieu INT,
    @TenRapChieuCon NVARCHAR(255)
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
		AND RCon.MaRapChieu = @MaRapChieu
        AND RCon.TenRapChieuCon LIKE N'%' + @TenRapChieuCon + N'%';
END;
EXEC pr_GetRapChieuConByTenRapChieu @MaTinhThanh = 1,  @MaRapChieu = 1, @TenRapChieuCon = N'Rạp Chiếu';
GO
/****** Object:  StoredProcedure [dbo].[pr_LayPhimDangChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- Đã sử dụng
CREATE PROCEDURE [dbo].[pr_LayPhimDangChieu]
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
/****** Object:  StoredProcedure [dbo].[pr_LayPhimSapChieu]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_LayPhimSapChieu]
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
GO
/****** Object:  StoredProcedure [dbo].[pr_LayTatCaPhim]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_LayTatCaPhim]
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
/****** Object:  StoredProcedure [dbo].[pr_LayThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_LayThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon]
    @MaPhim INT,
    @MaRapChieuCon INT,
	@MaThoiGianChieu INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Truy vấn lấy MaPhim, ThoiGianBatDau và ThoiGianKetThuc theo MaPhim và MaRapChieuCon với định dạng HH:mm
    SELECT 
        LichChieu.MaPhim,
        CONVERT(VARCHAR(5), ChiTietLichChieu.ThoiGianBatDau, 108) AS ThoiGianBatDau, -- Định dạng HH:mm
        CONVERT(VARCHAR(5), ChiTietLichChieu.ThoiGianKetThuc, 108) AS ThoiGianKetThuc -- Định dạng HH:mm
    FROM 
        ChiTietLichChieu
    JOIN 
        LichChieu ON ChiTietLichChieu.MaLichChieu = LichChieu.MaLichChieu
	JOIN ThoiGianChieu ON ChiTietLichChieu.MaThoiGianChieu = ThoiGianChieu.MaThoiGianChieu
    JOIN 
        RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    WHERE 
        LichChieu.MaPhim = @MaPhim
        AND LichChieu.MaRapChieuCon = @MaRapChieuCon
		AND ChiTietLichChieu.MaThoiGianChieu = @MaThoiGianChieu;
END;

EXEC pr_LayThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon @MaPhim = 2, @MaRapChieuCon = 1, @MaThoiGianChieu = 1;

-- END--
GO
/****** Object:  StoredProcedure [dbo].[pr_LayThongTinPhimTheoNgayChieuRapChieuCon]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_LayThongTinPhimTheoNgayChieuRapChieuCon]
    @MaRapChieuCon INT,
    @MaThoiGianChieu INT
AS
BEGIN
    SET NOCOUNT ON;
	DECLARE @NgayChieu VARCHAR(10);

	SELECT @NgayChieu = CONVERT(VARCHAR(10), ThoiGianChieu.NgayChieu, 103) FROM ThoiGianChieu;
	
    -- Truy vấn thông tin phim trực tiếp mà không cần dùng Cursor
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
EXEC pr_LayThongTinPhimTheoNgayChieuRapChieuCon @MaRapChieuCon = 1, @MaThoiGianChieu = 1;
GO
/****** Object:  StoredProcedure [dbo].[pr_LayThongTinPhimXepHangTheoNgay]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_LayThongTinPhimXepHangTheoNgay]
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
			JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND CAST(ThoiGianChieu.NgayChieu AS DATE) = CAST(GETDATE() AS DATE)
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
/****** Object:  StoredProcedure [dbo].[pr_LayThongTinPhimXepHangTheoThang]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_LayThongTinPhimXepHangTheoThang]
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
			JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND MONTH(ThoiGianChieu.NgayChieu) = MONTH(GETDATE())
            AND YEAR(ThoiGianChieu.NgayChieu) = YEAR(GETDATE())
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
/****** Object:  StoredProcedure [dbo].[pr_LayThongTinPhimXepHangTheoTuan]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_LayThongTinPhimXepHangTheoTuan]
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
			JOIN ThoiGianChieu ON ThoiGianChieu.MaThoiGianChieu = ChiTietLichChieu.MaThoiGianChieu
            WHERE LichChieu.MaPhim = Phim.MaPhim
            AND DATEPART(ISO_WEEK, ThoiGianChieu.NgayChieu) = DATEPART(ISO_WEEK, GETDATE())
            AND YEAR(ThoiGianChieu.NgayChieu) = YEAR(GETDATE())
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
/****** Object:  StoredProcedure [dbo].[pr_LayThongTinRapChieuCha]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_LayThongTinRapChieuCha]
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
/****** Object:  StoredProcedure [dbo].[pr_TimKiemPhim]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[pr_TimKiemPhim]
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
SELECT MaTinhThanh, TenTinhThanh FROM TinhThanhPho WHERE TenTinhThanh LIKE N'%Đà%'
GO
/****** Object:  StoredProcedure [dbo].[sp_GetVouchercapbacList]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE   PROCEDURE [dbo].[sp_GetVouchercapbacList]
AS
BEGIN
    SELECT 
        N'Voucher Của Tôi' AS LoaiVoucher,
        VoucherCuaToi.TenVoucher,
        VoucherCuaToi.MaDoiTuongApDung,
        VoucherCuaToi.TrangThaiGiam,
        VoucherCuaToi.MucGiam,
        CONVERT(DATE, VoucherCuaToi.HanSuDung, 103) AS HanSuDung, -- Lấy ngày tháng năm, không lấy giờ
        VoucherCuaToi.TrangThaiSuDung,
        VoucherCuaToi.SoLuongToiDa,
        VoucherCuaToi.SoLuongSuDung AS SoLuongSuDung, -- Nếu SoLuongSuDung không tồn tại, có thể thay thế hoặc loại bỏ dòng này
        CapBacChiTieu.AnhCapBac AS Icon -- Lấy icon từ cột AnhCapBac trong CapBacChiTieu
    FROM VoucherCuaToi
    JOIN ChiTietCapBac 
        ON VoucherCuaToi.MaKhachHang = ChiTietCapBac.MaKhachHang
    JOIN CapBacChiTieu
        ON ChiTietCapBac.MaCapBacChiTieu = CapBacChiTieu.MaCapBacChiTieu;
END;
GO
/****** Object:  StoredProcedure [dbo].[sp_GetVoucherList]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[sp_GetVoucherList]
AS
BEGIN
    SELECT 
        N'Voucher Đối Tác' AS LoaiVoucher,
        VoucherDoiTac.TenVoucher,
        VoucherDoiTac.MaDoiTuongApDung,
        VoucherDoiTac.TrangThaiGiam,
        VoucherDoiTac.MucGiam,
        CONVERT(DATE, VoucherDoiTac.HanSuDung, 103) AS HanSuDung, -- Lấy ngày tháng năm, không lấy giờ
        VoucherDoiTac.TrangThaiSuDung,
        VoucherDoiTac.SoLuongToiDa,
        VoucherDoiTac.SoLuongSuDung, -- Lấy trực tiếp từ cột SoLuongDaDung
        RapChieu.AnhRapChieu AS Icon
    FROM VoucherDoiTac
    INNER JOIN RapChieu ON VoucherDoiTac.MaRapChieu = RapChieu.MaRapChieu

    UNION ALL

    SELECT 
       N'Voucher Ứng Dụng' AS LoaiVoucher,
        VoucherUngDung.TenVoucher,
        VoucherUngDung.MaDoiTuongApDung,
        VoucherUngDung.TrangThaiGiam,
        VoucherUngDung.MucGiam,
        CONVERT(DATE, VoucherUngDung.HanSuDung, 103) AS HanSuDung, -- Lấy ngày tháng năm, không lấy giờ
        VoucherUngDung.TrangThaiSuDung,
        VoucherUngDung.SoLuongToiDa,
        VoucherUngDung.SoLuongSuDung, -- Lấy trực tiếp từ cột SoLuongDaDung
        VoucherUngDung.AnhUngDung AS Icon
    FROM VoucherUngDung


END;
GO
/****** Object:  StoredProcedure [dbo].[VePhimChuaDung]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

			-- VePhimChuaDung
			CREATE   PROCEDURE [dbo].[VePhimChuaDung]
			AS
			BEGIN
				SELECT 
					VC.MaVe,
					FORMAT(TV.ThoiGian, 'dd-MM-yyyy HH:mm') AS ThoiGian,  -- Lấy ngày và giờ
					P.AnhPhim,
					P.Tuoi,
					P.TenPhim,
					MAX(TLC.TenTheLoai) AS TenTheLoai,  -- Chỉ hiển thị 1 tên thể loại
					VC.SoLuongVe,
					RC.AnhRapChieu,
					DC.DiaChiRapChieu AS DiaChiRapChieu
				FROM 
					VePhim VC
				INNER JOIN 
					LichChieu LC ON VC.MaLichChieu = LC.MaLichChieu
				INNER JOIN 
					ChiTietLichChieu CLC ON LC.MaLichChieu = CLC.MaLichChieu
				INNER JOIN 
					Phim P ON LC.MaPhim = P.MaPhim
				INNER JOIN 
					TheLoai TL ON P.MaPhim = TL.MaPhim 
				INNER JOIN 
					TheLoaiCha TLC ON TL.MaTheLoaiCha = TLC.MaTheLoaiCha
				INNER JOIN 
					RapChieuCon RCC ON LC.MaRapChieuCon = RCC.MaRapChieuCon
				INNER JOIN 
					DiaChiRapChieuCon DC ON RCC.MaRapChieuCon = DC.MaRapChieuCon
				INNER JOIN 
					RapChieu RC ON RCC.MaRapChieu = RC.MaRapChieu
				INNER JOIN 
					TinhTrangVe TV ON VC.MaVe = TV.MaVe
				WHERE 
					TV.TinhTrang = N'Chưa sử dụng'
				GROUP BY 
					VC.MaVe,  
					TV.ThoiGian,  
					P.AnhPhim, 
					P.Tuoi, 
					P.TenPhim, 
					VC.SoLuongVe, 
					RC.AnhRapChieu, 
					DC.DiaChiRapChieu;
			END;
GO
/****** Object:  StoredProcedure [dbo].[VePhimDaDung]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

			-- VePhimDaDung
			CREATE   PROCEDURE [dbo].[VePhimDaDung]
			AS
			BEGIN
				SELECT 
					VC.MaVe,
					FORMAT(TV.ThoiGian, 'dd-MM-yyyy HH:mm') AS ThoiGian,  -- Lấy ngày và giờ
					P.AnhPhim,
					P.Tuoi,
					P.TenPhim,
					MAX(TLC.TenTheLoai) AS TenTheLoai,  -- Chỉ hiển thị 1 tên thể loại
					VC.SoLuongVe,
					RC.AnhRapChieu,
					DC.DiaChiRapChieu AS DiaChiRapChieu
				FROM 
					VePhim VC
				INNER JOIN 
					LichChieu LC ON VC.MaLichChieu = LC.MaLichChieu
				INNER JOIN 
					ChiTietLichChieu CLC ON LC.MaLichChieu = CLC.MaLichChieu
				INNER JOIN 
					Phim P ON LC.MaPhim = P.MaPhim
				INNER JOIN 
					TheLoai TL ON P.MaPhim = TL.MaPhim 
				INNER JOIN 
					TheLoaiCha TLC ON TL.MaTheLoaiCha = TLC.MaTheLoaiCha
				INNER JOIN 
					RapChieuCon RCC ON LC.MaRapChieuCon = RCC.MaRapChieuCon
				INNER JOIN 
					DiaChiRapChieuCon DC ON RCC.MaRapChieuCon = DC.MaRapChieuCon
				INNER JOIN 
					RapChieu RC ON RCC.MaRapChieu = RC.MaRapChieu
				INNER JOIN 
					TinhTrangVe TV ON VC.MaVe = TV.MaVe
				WHERE 
					TV.TinhTrang = N'Đã sử dụng'
				GROUP BY 
					VC.MaVe,  
					TV.ThoiGian,  
					P.AnhPhim, 
					P.Tuoi, 
					P.TenPhim, 
					VC.SoLuongVe, 
					RC.AnhRapChieu, 
					DC.DiaChiRapChieu;
			END;
GO
/****** Object:  StoredProcedure [dbo].[VePhimDaKhuHoi]    Script Date: 22/12/2024 22:41:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

			-- VePhimDaKhuHoi
			CREATE   PROCEDURE [dbo].[VePhimDaKhuHoi]
			AS
			BEGIN
				SELECT 
					VC.MaVe,
					FORMAT(TV.ThoiGian, 'dd-MM-yyyy HH:mm') AS ThoiGian,  -- Lấy ngày và giờ
					P.AnhPhim,
					P.Tuoi,
					P.TenPhim,
					MAX(TLC.TenTheLoai) AS TenTheLoai,  -- Chỉ hiển thị 1 tên thể loại
					VC.SoLuongVe,
					RC.AnhRapChieu,
					DC.DiaChiRapChieu AS DiaChiRapChieu
				FROM 
					VePhim VC
				INNER JOIN 
					LichChieu LC ON VC.MaLichChieu = LC.MaLichChieu
				INNER JOIN 
					ChiTietLichChieu CLC ON LC.MaLichChieu = CLC.MaLichChieu
				INNER JOIN 
					Phim P ON LC.MaPhim = P.MaPhim
				INNER JOIN 
					TheLoai TL ON P.MaPhim = TL.MaPhim 
				INNER JOIN 
					TheLoaiCha TLC ON TL.MaTheLoaiCha = TLC.MaTheLoaiCha
				INNER JOIN 
					RapChieuCon RCC ON LC.MaRapChieuCon = RCC.MaRapChieuCon
				INNER JOIN 
					DiaChiRapChieuCon DC ON RCC.MaRapChieuCon = DC.MaRapChieuCon
				INNER JOIN 
					RapChieu RC ON RCC.MaRapChieu = RC.MaRapChieu
				INNER JOIN 
					TinhTrangVe TV ON VC.MaVe = TV.MaVe
				WHERE 
					TV.TinhTrang = N'Đã khứ hồi'
				GROUP BY 
					VC.MaVe,  
					TV.ThoiGian,  
					P.AnhPhim, 
					P.Tuoi, 
					P.TenPhim, 
					VC.SoLuongVe, 
					RC.AnhRapChieu, 
					DC.DiaChiRapChieu;
			END;
GO
USE [master]
GO
ALTER DATABASE [dbQuanLyXemPhim] SET  READ_WRITE 
GO
