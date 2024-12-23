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
CREATE OR ALTER PROCEDURE GetChuaDung
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
CREATE OR ALTER PROCEDURE sp_GetVoucherList
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
go
CREATE OR ALTER PROCEDURE GetCustomerDetailsByMaKhachHang
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

CREATE OR ALTER PROCEDURE sp_GetVouchercapbacList
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
CREATE OR ALTER PROCEDURE chuyenTinhTrangVeKhuHoi
    @MaVe INT
AS
BEGIN
    -- Cập nhật tình trạng vé thành 'Đã khứ hồi'
    UPDATE TinhTrangVe
    SET TinhTrang = N'Đã khứ hồi',
        ThoiGian = GETDATE() -- Cập nhật thời gian hiện tại
    WHERE MaVe = @MaVe;
END;
GO

-- Thực thi thủ tục để kiểm tra
EXEC sp_GetVouchercapbacList;



