use dbQuanLyXemPhim
go
CREATE TRIGGER trg_UpdateTrangThaiChieu
ON Phim
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE Phim
    SET TrangThaiChieu = N'Sắp chiếu'
    WHERE NgayKhoiChieu > GETDATE() 
      AND TrangThaiChieu IS NULL; -- Đảm bảo không ghi đè dữ liệu cũ
END;