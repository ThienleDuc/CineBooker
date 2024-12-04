use dbQuanLyXemPhim
GO
CREATE FUNCTION dbo.fn_DiemDanhGiaTrungBinhTheoNgayChieuRapChieuCon (
    @MaPhim INT,
    @NgayChieu DATETIME,
    @TenRapChieuCon NVARCHAR(255)
)
RETURNS FLOAT
AS
BEGIN
    DECLARE @DiemTrungBinh FLOAT;

    -- Tính điểm trung bình từ bảng DanhGia cho bộ phim với MaPhim, NgayChieu và TenRapChieuCon tương ứng
    SELECT @DiemTrungBinh = AVG(DiemDanhGia)
    FROM DanhGia
    JOIN LichChieu ON DanhGia.MaPhim = LichChieu.MaPhim
    JOIN RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
    WHERE LichChieu.MaPhim = @MaPhim
    AND ChiTietLichChieu.NgayChieu = @NgayChieu
    AND RapChieuCon.TenRapChieuCon = @TenRapChieuCon;

    -- Trả về điểm trung bình
    RETURN @DiemTrungBinh;
END;
GO
CREATE FUNCTION dbo.fn_DiemDanhGiaPhimTrungBinh (@MaPhim INT)
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
CREATE FUNCTION dbo.fn_DiemDanhGiaPhimTrungBinhTheoNgay (@MaPhim INT)
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
    RETURN @DiemTrungBinh;
END;
GO
CREATE FUNCTION dbo.fn_DiemDanhGiaPhimTrungBinhTheoTuan (@MaPhim INT)
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
    RETURN @DiemTrungBinh;
END;
GO
CREATE FUNCTION dbo.fn_DiemDanhGiaPhimTrungBinhTheoThang (@MaPhim INT)
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
    RETURN @DiemTrungBinh;
END;
GO
CREATE FUNCTION dbo.fn_TongLuotMuaPhim (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim với điều kiện TinhTrang = N'Đã dùng'
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN PhimDangChieu ON VePhim.MaPhimDangChieu = PhimDangChieu.MaPhimDangChieu
    JOIN LichChieu ON PhimDangChieu.MaLichChieu = LichChieu.MaLichChieu
    WHERE LichChieu.MaPhim = @MaPhim

    RETURN @TongLuotMua;
END;
GO
CREATE FUNCTION dbo.fn_TongLuotMuaPhimTrungBinhTheoNgayChieuRapChieuCon (
    @MaPhim INT,
    @NgayChieu DATETIME,
    @TenRapChieuCon NVARCHAR(255)
)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng lượt mua phim trung bình theo NgàyChiếu và TenRapChieuCon
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN PhimDangChieu ON VePhim.MaPhimDangChieu = PhimDangChieu.MaPhimDangChieu
    JOIN LichChieu ON PhimDangChieu.MaLichChieu = LichChieu.MaLichChieu
    JOIN RapChieuCon ON LichChieu.MaRapChieuCon = RapChieuCon.MaRapChieuCon
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
    WHERE LichChieu.MaPhim = @MaPhim
    AND ChiTietLichChieu.NgayChieu = @NgayChieu
    AND RapChieuCon.TenRapChieuCon = @TenRapChieuCon;

    -- Trả về tổng lượt mua
    RETURN @TongLuotMua;
END;
GO
CREATE FUNCTION dbo.fn_TongLuotMuaPhimTheoNgay (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim theo ngày hiện tại (không xét tình trạng vé)
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN PhimDangChieu ON VePhim.MaPhimDangChieu = PhimDangChieu.MaPhimDangChieu
    JOIN LichChieu ON PhimDangChieu.MaLichChieu = LichChieu.MaLichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
    WHERE LichChieu.MaPhim = @MaPhim
    AND CAST(ChiTietLichChieu.NgayChieu AS DATE) = CAST(GETDATE() AS DATE);  -- Sử dụng ngày hiện tại

    RETURN @TongLuotMua;
END;
GO
CREATE FUNCTION dbo.fn_TongLuotMuaPhimTheoTuan (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim trong tuần hiện tại (không xét tình trạng vé)
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN PhimDangChieu ON VePhim.MaPhimDangChieu = PhimDangChieu.MaPhimDangChieu
    JOIN LichChieu ON PhimDangChieu.MaLichChieu = LichChieu.MaLichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
    WHERE LichChieu.MaPhim = @MaPhim
    AND DATEPART(ISO_WEEK, ChiTietLichChieu.NgayChieu) = DATEPART(ISO_WEEK, GETDATE())  -- So sánh tuần hiện tại
    AND YEAR(ChiTietLichChieu.NgayChieu) = YEAR(GETDATE());  -- Đảm bảo năm trùng với năm hiện tại

    RETURN @TongLuotMua;
END;
GO
CREATE FUNCTION dbo.fn_TongLuotMuaPhimTheoThang (@MaPhim INT)
RETURNS INT
AS
BEGIN
    DECLARE @TongLuotMua INT;

    -- Tính tổng số lượt mua cho bộ phim trong tháng hiện tại (không xét tình trạng vé)
    SELECT @TongLuotMua = COUNT(DISTINCT VePhim.MaVe)
    FROM VePhim
    JOIN PhimDangChieu ON VePhim.MaPhimDangChieu = PhimDangChieu.MaPhimDangChieu
    JOIN LichChieu ON PhimDangChieu.MaLichChieu = LichChieu.MaLichChieu
    JOIN ChiTietLichChieu ON LichChieu.MaLichChieu = ChiTietLichChieu.MaLichChieu
    WHERE LichChieu.MaPhim = @MaPhim
    AND MONTH(ChiTietLichChieu.NgayChieu) = MONTH(GETDATE())  -- So sánh tháng hiện tại
    AND YEAR(ChiTietLichChieu.NgayChieu) = YEAR(GETDATE());  -- Đảm bảo năm trùng với năm hiện tại

    RETURN @TongLuotMua;
END;
GO
CREATE FUNCTION dbo.fn_TongLuotDanhGiaPhimTheoNgayChieuRapChieuCon (
    @MaPhim INT,
    @NgayChieu DATETIME,
    @TenRapChieuCon NVARCHAR(255)
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
    WHERE DanhGia.MaPhim = @MaPhim
    AND ChiTietLichChieu.NgayChieu = @NgayChieu
    AND RapChieuCon.TenRapChieuCon = @TenRapChieuCon;

    -- Trả về tổng lượt đánh giá
    RETURN @TongLuotDanhGia;
END;
GO
CREATE FUNCTION dbo.fn_TongLuotDanhGiaPhimTheoNgay (@MaPhim INT)
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
CREATE FUNCTION dbo.fn_TongLuotDanhGiaPhimTheoTuan (@MaPhim INT)
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
CREATE FUNCTION dbo.fn_TongLuotDanhGiaPhimTheoThang (@MaPhim INT)
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
CREATE FUNCTION dbo.fn_TongLuotDanhGiaPhim (@MaPhim INT)
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
CREATE FUNCTION dbo.fn_TongLuotDanhGiaRapChieuCon (@MaRapChieuCon INT)
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
CREATE FUNCTION dbo.fn_TongLuotDanhGiaRapChieuCha (@MaRapChieu INT)
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
CREATE FUNCTION dbo.fn_DiemDanhGiaTrungBinhRapChieuCon (@MaRapChieuCon INT)
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
CREATE FUNCTION dbo.fn_DiemDanhGiaTrungBinhRapChieuCha (@MaRapChieu INT)
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
CREATE FUNCTION dbo.fn_TongSoDiaDiemRapChieuCha (@MaRapChieu INT)
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
-- Function tính tổng số lượng VoucherDoiTac
CREATE FUNCTION dbo.fn_TongSoLuongVoucherDoiTac()
RETURNS INT
AS
BEGIN
    DECLARE @TongSoLuong INT;

    SELECT @TongSoLuong = SUM(SoLuongToiDa)
    FROM VoucherDoiTac
    WHERE TrangThaiSuDung = N'Chưa sử dụng'
    AND SoLuongToiDa > 0
    AND HanSuDung > GETDATE();

    RETURN @TongSoLuong;
END;
GO

-- Function tính tổng số lượng VoucherCuaToi
CREATE FUNCTION dbo.fn_TongSoLuongVoucherCuaToi()
RETURNS INT
AS
BEGIN
    DECLARE @TongSoLuong INT;

    SELECT @TongSoLuong = SUM(SoLuongToiDa)
    FROM VoucherCuaToi
    WHERE TrangThaiSuDung = N'Chưa sử dụng'
    AND SoLuongToiDa > 0
    AND HanSuDung > GETDATE();

    RETURN @TongSoLuong;
END;
GO

-- Function tính tổng số lượng VoucherUngDung
CREATE FUNCTION dbo.fn_TongSoLuongVoucherUngDung()
RETURNS INT
AS
BEGIN
    DECLARE @TongSoLuong INT;

    SELECT @TongSoLuong = SUM(SoLuongToiDa)
    FROM VoucherUngDung
    WHERE TrangThaiSuDung = N'Chưa sử dụng'
    AND SoLuongToiDa > 0
    AND HanSuDung > GETDATE();

    RETURN @TongSoLuong;
END;
GO

CREATE FUNCTION dbo.fn_PhanTramSuDung (@SoLuongToiDa INT)
RETURNS INT
AS
BEGIN
    DECLARE @PhanTramSuDung INT;

    -- Tính phần trăm đã sử dụng theo công thức: SoLuongToiDa - (SoLuongToiDa / 100)
    SET @PhanTramSuDung = @SoLuongToiDa - (@SoLuongToiDa / 100);

    -- Trả về kết quả tính toán
    RETURN @PhanTramSuDung;
END;
GO
CREATE FUNCTION dbo.fn_TongTienThanhToan (
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
