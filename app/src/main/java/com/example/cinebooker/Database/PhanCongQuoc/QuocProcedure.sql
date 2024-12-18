USE dbQuanLyXemPhim
GO

-- VePhimChuaDung
CREATE OR ALTER PROCEDURE VePhimChuaDung
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

-- VePhimDaDung
CREATE OR ALTER PROCEDURE VePhimDaDung
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

-- VePhimDaKhuHoi
CREATE OR ALTER PROCEDURE VePhimDaKhuHoi
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

-- GetVePhimDadung
CREATE OR ALTER PROCEDURE GetVePhimDadung
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

-- GetVePhimDaKhuHoi
CREATE OR ALTER PROCEDURE GetVePhimDaKhuHoi
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
INSERT INTO ThanhToan (MaVe, QRThanhToan, TongTien)
VALUES (1, 'QR_SAMPLE_CODE', 150000);
go
select *from ThanhToan;
EXEC GetVePhimDadung @MaVe = 3;


