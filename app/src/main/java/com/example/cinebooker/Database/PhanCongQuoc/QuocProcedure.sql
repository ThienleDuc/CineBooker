USE dbQuanLyXemPhim
GO

CREATE OR ALTER PROCEDURE VePhimChuaDung
AS
BEGIN
    SELECT 
	     VC.MaVe,
        TV.ThoiGian,
        P.AnhPhim,
        P.Tuoi,
        P.TenPhim,
        STRING_AGG(TLC.TenTheLoai, ', ') AS TenTheLoai, 
        VC.SoLuongVe,
        RC.AnhRapChieu,
        DC.DiaChiRapChieu AS DiaChiRapChieu
    FROM 
        VePhim VC
    INNER JOIN 
        LichChieu LC ON VC.MaLichChieu = LC.MaLichChix`eu
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
        TV.ThoiGian, 
        P.AnhPhim, 
        P.Tuoi, 
        P.TenPhim, 
        VC.SoLuongVe, 
        RC.AnhRapChieu, 
        DC.DiaChiRapChieu;
END;
GO

GO
CREATE OR ALTER PROCEDURE VePhimDaDung
AS
BEGIN
    SELECT 
         TV.ThoiGian,
        P.AnhPhim,
        P.Tuoi,
        P.TenPhim,
        STRING_AGG(TLC.TenTheLoai, ', ') AS TenTheLoai, 
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
         TV.ThoiGian,  
        P.AnhPhim, 
        P.Tuoi, 
        P.TenPhim, 
        VC.SoLuongVe, 
        RC.AnhRapChieu, 
        DC.DiaChiRapChieu;
END;
GO
CREATE PROCEDURE VePhimDaKhuHoi
AS
BEGIN
    SELECT 
        TV.ThoiGian,
        P.AnhPhim,
        P.Tuoi,
        P.TenPhim,
        STRING_AGG(TLC.TenTheLoai, ', ') AS TenTheLoai, 
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
         TV.ThoiGian,  
        P.AnhPhim, 
        P.Tuoi, 
        P.TenPhim, 
        VC.SoLuongVe, 
        RC.AnhRapChieu, 
        DC.DiaChiRapChieu;
END;


GO
CREATE OR ALTER  PROCEDURE GetVePhimDadung
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
        TV.ThoiGian,
        P.AnhPhim,
        P.Tuoi,
        P.TenPhim,
        VC.SoLuongVe,
        VC.GheNgoi,
        VC.PhongChieu,
        RC.AnhRapChieu,
        DC.DiaChiRapChieu,
        CLC.ThoiGianBatDau AS ThoiGianBatDau,
        CLC.ThoiGianKetThuc AS ThoiGianKetThuc,
        CLC.NgayChieu,
        P.DinhDangPhim,
        VC.GheNgoi,
        VC.PhongChieu,
        N'Đã sử dụng' AS TinhTrang, -- Trả về trạng thái đã sử dụng
        GETDATE() AS ThoiGian -- Trả về thời gian hiện tại
    FROM VePhim VC
    JOIN ThanhToan QR ON VC.MaVe = QR.MaVe
    INNER JOIN LichChieu LC ON VC.MaLichChieu = LC.MaLichChieu
    INNER JOIN ChiTietLichChieu CLC ON LC.MaLichChieu = CLC.MaLichChieu
    INNER JOIN Phim P ON LC.MaPhim = P.MaPhim
    INNER JOIN TheLoai TL ON P.MaPhim = TL.MaPhim
    INNER JOIN TheLoaiCha TLC ON TL.MaTheLoaiCha = TLC.MaTheLoaiCha
    INNER JOIN RapChieuCon RCC ON LC.MaRapChieuCon = RCC.MaRapChieuCon
    INNER JOIN DiaChiRapChieuCon DC ON RCC.MaRapChieuCon = DC.MaRapChieuCon
    INNER JOIN RapChieu RC ON RCC.MaRapChieu = RC.MaRapChieu
    INNER JOIN TinhTrangVe TV ON VC.MaVe = TV.MaVe
    WHERE VC.MaVe = @MaVe;
END;
GO



EXEC VePhimDaKhuHoi


EXEC VePhimChuaDung;
EXEC VePhimDaDung;