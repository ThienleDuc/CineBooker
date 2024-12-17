use dbQuanLyXemPhim
go
CREATE TRIGGER trg_UpdateTrangThaiChieu
ON Phim
AFTER INSERT, UPDATE
AS
BEGIN
    -- Cập nhật trạng thái "Đã chiếu" nếu NgayKetThuc đã qua
    UPDATE Phim
    SET TrangThaiChieu = N'Đã chiếu'
    WHERE GETDATE() > NgayKetThuc;

    -- Cập nhật trạng thái "Đang chiếu" nếu MaPhim tồn tại trong bảng LichChieu và ngày hiện tại nằm trong khoảng NgayKhoiChieu đến NgayKetThuc
    UPDATE Phim
    SET TrangThaiChieu = N'Đang chiếu'
    WHERE MaPhim IN (SELECT MaPhim FROM LichChieu) 
      AND GETDATE() BETWEEN NgayKhoiChieu AND NgayKetThuc;

    -- Cập nhật trạng thái "Sắp chiếu" nếu MaPhim không tồn tại trong bảng LichChieu hoặc ngày hiện tại nhỏ hơn NgayKhoiChieu
    UPDATE Phim
    SET TrangThaiChieu = N'Sắp chiếu'
    WHERE GETDATE() < NgayKhoiChieu 
END;
GO
CREATE TRIGGER trg_CheckAndInsertChiTietCapBac
ON ChiTietCapBac
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    -- Biến lưu MaCapBacChiTieu nhỏ nhất
    DECLARE @MinMaCapBacChiTieu INT;

    -- Lấy giá trị nhỏ nhất từ CapBacChiTieu
    SELECT @MinMaCapBacChiTieu = MIN(MaCapBacChiTieu)
    FROM CapBacChiTieu;

    -- Duyệt qua các khách hàng chưa có trong ChiTietCapBac
    INSERT INTO ChiTietCapBac (MaKhachHang, MaCapBacChiTieu, ThoiHanReset)
    SELECT KH.MaKhachHang, @MinMaCapBacChiTieu, DATEADD(MONTH, 6, GETDATE()) -- ThoiHanReset là ngày hiện tại cộng thêm 6 tháng
    FROM KhachHang KH
    WHERE NOT EXISTS (
        SELECT 1
        FROM ChiTietCapBac CT
        WHERE CT.MaKhachHang = KH.MaKhachHang
    );
END;
GO
CREATE TRIGGER trg_UpdateMaCapBacChiTieu
ON ThanhToan
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Biến lưu thông tin
    DECLARE @MaKhachHang INT, @TongTien FLOAT;

    -- Lấy danh sách các khách hàng có thay đổi thanh toán
    DECLARE Cursor_KhachHang CURSOR FOR
    SELECT DISTINCT VP.MaKhachHang
    FROM VePhim VP
    INNER JOIN INSERTED I ON VP.MaVe = I.MaVe
    UNION
    SELECT DISTINCT VP.MaKhachHang
    FROM VePhim VP
    INNER JOIN DELETED D ON VP.MaVe = D.MaVe;

    OPEN Cursor_KhachHang;
    FETCH NEXT FROM Cursor_KhachHang INTO @MaKhachHang;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Tính tổng tiền thanh toán cho khách hàng
        SELECT @TongTien = dbo.fn_TongTienThanhToan(@MaKhachHang);

        -- Lấy MaCapBacChiTieu tương ứng với hạn mức chi tiêu
        DECLARE @MaCapBacChiTieu INT;
        SELECT TOP 1 @MaCapBacChiTieu = MaCapBacChiTieu
        FROM CapBacChiTieu
        WHERE HanMucChiTieu >= @TongTien
        ORDER BY HanMucChiTieu ASC;

        -- Nếu tổng tiền vượt hạn mức cao nhất, lấy cấp bậc cao nhất
        IF @MaCapBacChiTieu IS NULL
        BEGIN
            SELECT @MaCapBacChiTieu = MAX(MaCapBacChiTieu)
            FROM CapBacChiTieu;
        END

        -- Cập nhật MaCapBacChiTieu trong ChiTietCapBac
        UPDATE ChiTietCapBac
        SET MaCapBacChiTieu = @MaCapBacChiTieu
        WHERE MaKhachHang = @MaKhachHang;

        FETCH NEXT FROM Cursor_KhachHang INTO @MaKhachHang;
    END;

    CLOSE Cursor_KhachHang;
    DEALLOCATE Cursor_KhachHang;
END;
GO
CREATE TRIGGER trg_UpdateThoiHanReset
ON ChiTietCapBac
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- Biến trung gian lưu giá trị ThoiHanReset ban đầu
    DECLARE @OldThoiHanReset DATETIME;

    -- Lấy giá trị ThoiHanReset ban đầu từ bảng trước khi cập nhật
    SELECT @OldThoiHanReset = ThoiHanReset
    FROM inserted;

    -- Kiểm tra nếu thời gian hiện tại > ThoiHanReset và cập nhật ThoiHanReset
    IF GETDATE() > @OldThoiHanReset
    BEGIN
        -- Cập nhật ThoiHanReset mới bằng ThoiHanReset ban đầu cộng thêm 6 tháng
        UPDATE ChiTietCapBac
        SET ThoiHanReset = DATEADD(MONTH, 6, @OldThoiHanReset)
        WHERE MaChiTietCapBac IN (SELECT MaChiTietCapBac FROM inserted);
    END
END;
GO