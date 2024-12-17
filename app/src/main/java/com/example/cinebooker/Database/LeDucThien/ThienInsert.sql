use dbQuanLyXemPhim
go

-- Thêm vào KhachHang
INSERT INTO KhachHang (Email, MatKhau, AnhKhachHang, TenKhachHang)
VALUES
('leducthien@example.com', 'password1', 'AnhKhachHang-1', N'Lê Đức Thiện'),
('phancongquoc@example.com', 'password2', 'AnhKhachHang-2', N'Phan Công Quốc'),
('trangiathai@example.com', 'password3', 'AnhKhachHang-3', N'Trần Gia Thái');
GO
-- Thêm vào bang CapBacChiTieu
INSERT INTO CapBacChiTieu (AnhCapBac, TenCapBac, HanMucChiTieu) 
VALUES
('anhcapbac1', N'đồng', 10000000),
('anhcapbac2', N'bạc', 20000000),
('anhcapbac3', N'vàng', 30000000),
('anhcapbac4', N'bạch kim', 40000000),
('anhcapbac5', N'kim cương', 50000000);
GO
-- Thêm vào bảng ChiTietCapBac
INSERT INTO ChiTietCapBac (MaKhachHang, MaCapBacChiTieu, ThoiHanReset)
VALUES
(1, 1, '2025-12-31'),
(2, 2, '2025-12-31'),
(3, 3, '2025-12-31');
GO
-- Thêm dữ liệu vào bảng TheLoaiCha
INSERT INTO TheLoaiCha (TenTheLoai)
VALUES
(N'Hài hước'),
(N'Kinh dị'),
(N'Tâm lý'),
(N'Hành động'),
(N'Viễn tưởng');

GO
-- Thêm vào bảng Phim
INSERT INTO Phim (AnhPhim, TenPhim, Tuoi, DinhDangPhim, NgayKhoiChieu, NgayKetThuc, ThoiLuong)
VALUES
('poster1', N'Avengers: Endgame', 13, N'IMAX 3D', '2024-12-01', '2024-12-30', '03:01:00'),
('poster2', N'Inception', 16, N'IMAX', '2024-12-02', '2024-12-31', '02:28:00'),
('poster3', N'Titanic', 13, N'2D', '2024-12-03', '2024-12-30', '03:14:00'),
('poster4', N'Avatar: The Way of Water', 16, N'3D', '2024-12-04', '2024-12-31', '03:12:00'),
('poster5', N'Spider-Man: No Way Home', 13, N'IMAX', '2024-12-05', '2024-12-30', '02:28:00');

INSERT INTO Phim (AnhPhim, TenPhim, Tuoi, DinhDangPhim, NgayKhoiChieu, NgayKetThuc, ThoiLuong)
VALUES
('poster6', N'The Dark Knight', 16, N'IMAX', '2024-12-06', '2024-12-31', '02:32:00'),
('poster7', N'Frozen', 6, N'3D', '2024-12-07', '2024-12-31', '01:42:00'),
('poster8', N'Toy Story 4', 6, N'2D', '2024-12-08', '2024-12-31', '01:40:00'),
('poster9', N'Black Panther', 13, N'IMAX','2024-12-08','2024-12-31', '02:14:00'),
('poster10', N'Jurassic World: Dominion', 13, N'IMAX', '2024-12-08', '2024-12-31', '02:26:00');

INSERT INTO Phim (AnhPhim, TenPhim, Tuoi, DinhDangPhim, NgayKhoiChieu, NgayKetThuc, ThoiLuong)
VALUES
('poster11', N'Guardians of the Galaxy Vol. 3', 13, N'IMAX 3D', '2025-01-01', '2025-01-31', '02:30:00'),
('poster12', N'The Lion King', 6, N'2D', '2025-01-02', '2025-01-31', '01:29:00'),
('poster13', N'Pirates of the Caribbean: The Curse of the Black Pearl', 13, N'2D', '2025-01-03', '2025-01-31', '02:23:00'),
('poster14', N'Dune', 13, N'IMAX', '2025-01-04', '2025-01-31', '02:35:00'),
('poster15', N'Shrek', 6, N'3D', '2025-01-05', '2025-01-31', '01:30:00');

INSERT INTO Phim (AnhPhim, TenPhim, Tuoi, DinhDangPhim, NgayKhoiChieu, NgayKetThuc, ThoiLuong)
VALUES
('poster16', N'Interstellar', 13, N'IMAX', '2025-01-06', '2025-01-31', '02:49:00'),
('poster17', N'WALL-E', 6, N'2D', '2025-01-07', '2025-01-31', '01:38:00'),
('poster18', N'Dr. Strange in the Multiverse of Madness', 13, N'IMAX 3D', '2025-01-08', '2025-01-31', '02:06:00'),
('poster19', N'Coco', 6, N'2D', '2025-01-09', '2025-01-31', '01:49:00'),
('poster20', N'Soul', 6, N'3D', '2025-01-10', '2025-01-31', '01:40:00');

INSERT INTO Phim (AnhPhim, TenPhim, Tuoi, DinhDangPhim, NgayKhoiChieu, NgayKetThuc, ThoiLuong)
VALUES
('poster21', N'The Matrix Resurrections', 13, N'IMAX', '2025-01-06', '2025-01-31', '02:49:00'),
('poster22', N'The Batman', 6, N'2D', '2025-01-07', '2025-01-31', '01:38:00'),
('poster23', N'Tangled', 13, N'IMAX 3D', '2025-01-08', '2025-01-31', '02:06:00'),
('poster24', N'The Secret Life of Pets', 6, N'2D', '2025-01-09', '2025-01-31', '01:49:00'),
('poster25', N'Thor: Ragnarok', 6, N'3D', '2025-01-10', '2025-01-31', '01:40:00');

INSERT INTO Phim (AnhPhim, TenPhim, Tuoi, DinhDangPhim, NgayKhoiChieu, NgayKetThuc, ThoiLuong)
VALUES
('poster26', N'Aladdin', 6, N'2D', '2025-01-16', '2025-01-31', '02:08:00'),
('poster27', N'Moana', 6, N'2D', '2025-01-17', '2025-01-31', '01:47:00'),
('poster28', N'Beauty and the Beast', 0, N'2D', '2025-01-18', '2025-01-31', '02:10:00'),
('poster29', N'Mulan', 6, N'2D', '2025-01-19', '2025-01-31', '01:55:00'),
('poster30', N'Star Wars: The Force Awakens', 13, N'IMAX', '2025-01-20', '2025-01-31', '02:18:00');
GO
-- Thêm dữ liệu vào bảng TheLoai, mỗi MaPhim có 2 MaTheLoaiCha
INSERT INTO TheLoai (MaTheLoaiCha, MaPhim)
VALUES
-- MaPhim 1
(1, 1),
(2, 1),

-- MaPhim 2
(2, 2),
(3, 2),

-- MaPhim 3
(3, 3),
(4, 3),

-- MaPhim 4
(1, 4),
(3, 4),

-- MaPhim 5
(4, 5),
(5, 5),

-- MaPhim 6
(1, 6),
(2, 6),

-- MaPhim 7
(3, 7),
(4, 7),

-- MaPhim 8
(2, 8),
(5, 8),

-- MaPhim 9
(1, 9),
(4, 9),

-- MaPhim 10
(5, 10),
(2, 10),

-- MaPhim 11
(3, 11),
(1, 11),

-- MaPhim 12
(4, 12),
(2, 12),

-- MaPhim 13
(5, 13),
(3, 13),

-- MaPhim 14
(1, 14),
(5, 14),

-- MaPhim 15
(2, 15),
(3, 15),

-- MaPhim 16
(4, 16),
(1, 16),

-- MaPhim 17
(5, 17),
(2, 17),

-- MaPhim 18
(3, 18),
(1, 18),

-- MaPhim 19
(4, 19),
(5, 19),

-- MaPhim 20
(1, 20),
(2, 20),

-- MaPhim 21
(3, 21),
(4, 21),

-- MaPhim 22
(2, 22),
(5, 22),

-- MaPhim 23
(1, 23),
(4, 23),

-- MaPhim 24
(5, 24),
(2, 24),

-- MaPhim 25
(3, 25),
(1, 25),

-- MaPhim 26
(4, 26),
(2, 26),

-- MaPhim 27
(5, 27),
(3, 27),

-- MaPhim 28
(1, 28),
(5, 28),

-- MaPhim 29
(2, 29),
(3, 29),

-- MaPhim 30
(4, 30),
(1, 30);

GO
-- Dữ liệu cho MaKhachHang = 1
INSERT INTO DanhGia (MaKhachHang, MaPhim, NgayDanhGia, DanhGia, DiemDanhGia, LuotThich) 
VALUES (1, 1, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 1', 7, 0),
       (1, 2, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 2', 8, 0),
       (1, 3, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 3', 7, 0),
       (1, 4, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 4', 7, 0),
       (1, 5, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 5', 9, 0),
       (1, 6, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 6', 8, 0),
       (1, 7, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 7', 6, 0),
       (1, 8, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 8', 7, 0),
       (1, 9, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 9', 8, 0),
       (1, 10, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 10', 7, 0),
       (1, 11, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 11', 7, 0),
       (1, 12, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 12', 8, 0),
       (1, 13, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 13', 8, 0),
       (1, 14, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 14', 7, 0),
       (1, 15, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 15', 7, 0),
       (1, 16, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 16', 8, 0),
       (1, 17, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 17', 7, 0),
       (1, 18, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 18', 8, 0),
       (1, 19, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 19', 8, 0),
       (1, 20, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 20', 7, 0),
       (1, 21, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 21', 8, 0),
       (1, 22, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 22', 8, 0),
       (1, 23, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 23', 7, 0),
       (1, 24, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 24', 8, 0),
       (1, 25, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 25', 8, 0),
       (1, 26, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 26', 8, 0),
       (1, 27, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 27', 7, 0),
       (1, 28, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 28', 7, 0),
       (1, 29, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 29', 8, 0),
       (1, 30, GETDATE(), N'Đánh giá của khách hàng 1 cho phim 30', 8, 0);

-- Dữ liệu cho MaKhachHang = 2
INSERT INTO DanhGia (MaKhachHang, MaPhim, NgayDanhGia, DanhGia, DiemDanhGia, LuotThich) 
VALUES (2, 1, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 1', 7, 0),
       (2, 2, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 2', 7, 0),
       (2, 3, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 3', 8, 0),
       (2, 4, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 4', 8, 0),
       (2, 5, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 5', 7, 0),
       (2, 6, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 6', 7, 0),
       (2, 7, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 7', 8, 0),
       (2, 8, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 8', 7, 0),
       (2, 9, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 9', 7, 0),
       (2, 10, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 10', 8, 0),
       (2, 11, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 11', 7, 0),
       (2, 12, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 12', 8, 0),
       (2, 13, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 13', 8, 0),
       (2, 14, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 14', 7, 0),
       (2, 15, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 15', 8, 0),
       (2, 16, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 16', 7, 0),
       (2, 17, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 17', 8, 0),
       (2, 18, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 18', 7, 0),
       (2, 19, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 19', 7, 0),
       (2, 20, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 20', 8, 0),
       (2, 21, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 21', 7, 0),
       (2, 22, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 22', 8, 0),
       (2, 23, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 23', 7, 0),
       (2, 24, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 24', 8, 0),
       (2, 25, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 25', 8, 0),
       (2, 26, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 26', 8, 0),
       (2, 27, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 27', 7, 0),
       (2, 28, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 28', 7, 0),
       (2, 29, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 29', 7, 0),
       (2, 30, GETDATE(), N'Đánh giá của khách hàng 2 cho phim 30', 8, 0);
GO
-- Dữ liệu cho MaKhachHang = 3
INSERT INTO DanhGia (MaKhachHang, MaPhim, NgayDanhGia, DanhGia, DiemDanhGia, LuotThich) 
VALUES (3, 1, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 1', 8, 0),
       (3, 2, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 2', 7, 0),
       (3, 3, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 3', 8, 0),
       (3, 4, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 4', 7, 0),
       (3, 5, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 5', 8, 0),
       (3, 6, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 6', 7, 0),
       (3, 7, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 7', 8, 0),
       (3, 8, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 8', 7, 0),
       (3, 9, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 9', 8, 0),
       (3, 10, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 10', 7, 0),
       (3, 11, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 11', 8, 0),
       (3, 12, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 12', 7, 0),
       (3, 13, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 13', 8, 0),
       (3, 14, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 14', 7, 0),
       (3, 15, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 15', 8, 0),
       (3, 16, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 16', 7, 0),
       (3, 17, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 17', 8, 0),
       (3, 18, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 18', 7, 0),
       (3, 19, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 19', 8, 0),
       (3, 20, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 20', 7, 0),
       (3, 21, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 21', 8, 0),
       (3, 22, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 22', 7, 0),
       (3, 23, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 23', 8, 0),
       (3, 24, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 24', 7, 0),
       (3, 25, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 25', 8, 0),
       (3, 26, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 26', 7, 0),
       (3, 27, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 27', 8, 0),
       (3, 28, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 28', 7, 0),
       (3, 29, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 29', 8, 0),
       (3, 30, GETDATE(), N'Đánh giá của khách hàng 3 cho phim 30', 7, 0);
GO
INSERT INTO RapChieu (AnhRapChieu, TenRapChieu, MoTaRapChieu) 
VALUES ('rap1', N'Rạp Chiếu A', N'Rạp Chiếu hiện đại với các tiện ích cao cấp, không gian thoải mái.'),
       ('rap2', N'Rạp Chiếu B', N'Rạp Chiếu phim với màn hình lớn và âm thanh vòm chất lượng cao.'),
       ('rap3', N'Rạp Chiếu C', N'Rạp Chiếu được thiết kế sang trọng, phục vụ các bộ phim bom tấn.'),
       ('rap4', N'Rạp Chiếu D', N'Rạp Chiếu phim với không gian ấm cúng, thích hợp cho các buổi chiếu nhỏ.'),
       ('rap5', N'Rạp Chiếu E', N'Rạp Chiếu phim mới với các ghế ngồi thoải mái và hệ thống âm thanh hiện đại.'),
       ('rap6', N'Rạp Chiếu F', N'Rạp Chiếu 3D với màn hình cực lớn, mang lại trải nghiệm tuyệt vời.'),
       ('rap7', N'Rạp Chiếu G', N'Rạp Chiếu phục vụ cả phim quốc tế và nội địa với các lựa chọn phòng chiếu đa dạng.'),
       ('rap8', N'Rạp Chiếu H', N'Rạp Chiếu với dịch vụ ăn uống tại chỗ, mang lại sự tiện lợi cho người xem.'),
       ('rap9', N'Rạp Chiếu I', N'Rạp Chiếu công nghệ cao với hệ thống máy chiếu 4K và âm thanh Dolby Atmos.'),
       ('rap10', N'Rạp Chiếu J', N'Rạp Chiếu với không gian hiện đại và đội ngũ nhân viên phục vụ chuyên nghiệp.');
GO
 -- Thêm 10 rạp chiếu con cho MaRapChieu = 1
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (1, N'Rạp Chiếu Con A1'), (1, N'Rạp Chiếu Con A2'), (1, N'Rạp Chiếu Con A3'),
       (1, N'Rạp Chiếu Con A4'), (1, N'Rạp Chiếu Con A5'), (1, N'Rạp Chiếu Con A6'),
       (1, N'Rạp Chiếu Con A7'), (1, N'Rạp Chiếu Con A8'), (1, N'Rạp Chiếu Con A9'),
       (1, N'Rạp Chiếu Con A10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 2
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (2, N'Rạp Chiếu Con B1'), (2, N'Rạp Chiếu Con B2'), (2, N'Rạp Chiếu Con B3'),
       (2, N'Rạp Chiếu Con B4'), (2, N'Rạp Chiếu Con B5'), (2, N'Rạp Chiếu Con B6'),
       (2, N'Rạp Chiếu Con B7'), (2, N'Rạp Chiếu Con B8'), (2, N'Rạp Chiếu Con B9'),
       (2, N'Rạp Chiếu Con B10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 3
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (3, N'Rạp Chiếu Con C1'), (3, N'Rạp Chiếu Con C2'), (3, N'Rạp Chiếu Con C3'),
       (3, N'Rạp Chiếu Con C4'), (3, N'Rạp Chiếu Con C5'), (3, N'Rạp Chiếu Con C6'),
       (3, N'Rạp Chiếu Con C7'), (3, N'Rạp Chiếu Con C8'), (3, N'Rạp Chiếu Con C9'),
       (3, N'Rạp Chiếu Con C10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 4
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (4, N'Rạp Chiếu Con D1'), (4, N'Rạp Chiếu Con D2'), (4, N'Rạp Chiếu Con D3'),
       (4, N'Rạp Chiếu Con D4'), (4, N'Rạp Chiếu Con D5'), (4, N'Rạp Chiếu Con D6'),
       (4, N'Rạp Chiếu Con D7'), (4, N'Rạp Chiếu Con D8'), (4, N'Rạp Chiếu Con D9'),
       (4, N'Rạp Chiếu Con D10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 5
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (5, N'Rạp Chiếu Con E1'), (5, N'Rạp Chiếu Con E2'), (5, N'Rạp Chiếu Con E3'),
       (5, N'Rạp Chiếu Con E4'), (5, N'Rạp Chiếu Con E5'), (5, N'Rạp Chiếu Con E6'),
       (5, N'Rạp Chiếu Con E7'), (5, N'Rạp Chiếu Con E8'), (5, N'Rạp Chiếu Con E9'),
       (5, N'Rạp Chiếu Con E10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 6
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (6, N'Rạp Chiếu Con F1'), (6, N'Rạp Chiếu Con F2'), (6, N'Rạp Chiếu Con F3'),
       (6, N'Rạp Chiếu Con F4'), (6, N'Rạp Chiếu Con F5'), (6, N'Rạp Chiếu Con F6'),
       (6, N'Rạp Chiếu Con F7'), (6, N'Rạp Chiếu Con F8'), (6, N'Rạp Chiếu Con F9'),
       (6, N'Rạp Chiếu Con F10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 7
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (7, N'Rạp Chiếu Con G1'), (7, N'Rạp Chiếu Con G2'), (7, N'Rạp Chiếu Con G3'),
       (7, N'Rạp Chiếu Con G4'), (7, N'Rạp Chiếu Con G5'), (7, N'Rạp Chiếu Con G6'),
       (7, N'Rạp Chiếu Con G7'), (7, N'Rạp Chiếu Con G8'), (7, N'Rạp Chiếu Con G9'),
       (7, N'Rạp Chiếu Con G10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 8
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (8, N'Rạp Chiếu Con H1'), (8, N'Rạp Chiếu Con H2'), (8, N'Rạp Chiếu Con H3'),
       (8, N'Rạp Chiếu Con H4'), (8, N'Rạp Chiếu Con H5'), (8, N'Rạp Chiếu Con H6'),
       (8, N'Rạp Chiếu Con H7'), (8, N'Rạp Chiếu Con H8'), (8, N'Rạp Chiếu Con H9'),
       (8, N'Rạp Chiếu Con H10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 9
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (9, N'Rạp Chiếu Con I1'), (9, N'Rạp Chiếu Con I2'), (9, N'Rạp Chiếu Con I3'),
       (9, N'Rạp Chiếu Con I4'), (9, N'Rạp Chiếu Con I5'), (9, N'Rạp Chiếu Con I6'),
       (9, N'Rạp Chiếu Con I7'), (9, N'Rạp Chiếu Con I8'), (9, N'Rạp Chiếu Con I9'),
       (9, N'Rạp Chiếu Con I10');

-- Thêm 10 rạp chiếu con cho MaRapChieu = 10
INSERT INTO RapChieuCon (MaRapChieu, TenRapChieuCon)
VALUES (10, N'Rạp Chiếu Con J1'), (10, N'Rạp Chiếu Con J2'), (10, N'Rạp Chiếu Con J3'),
       (10, N'Rạp Chiếu Con J4'), (10, N'Rạp Chiếu Con J5'), (10, N'Rạp Chiếu Con J6'),
       (10, N'Rạp Chiếu Con J7'), (10, N'Rạp Chiếu Con J8'), (10, N'Rạp Chiếu Con J9'),
       (10, N'Rạp Chiếu Con J10');

GO
-- Thêm 100 lịch chiếu với mã phim từ 1 đến 30 và mã rạp chiếu con từ 1 đến 100
INSERT INTO LichChieu (MaRapChieuCon, MaPhim)
VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5),
(6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
(11, 11), (12, 12), (13, 13), (14, 14), (15, 15),
(16, 16), (17, 17), (18, 18), (19, 19), (20, 20),
(21, 21), (22, 22), (23, 23), (24, 24), (25, 25),
(26, 26), (27, 27), (28, 28), (29, 29), (30, 30),
(31, 1), (32, 2), (33, 3), (34, 4), (35, 5),
(36, 6), (37, 7), (38, 8), (39, 9), (40, 10),
(41, 11), (42, 12), (43, 13), (44, 14), (45, 15),
(46, 16), (47, 17), (48, 18), (49, 19), (50, 20),
(51, 21), (52, 22), (53, 23), (54, 24), (55, 25),
(56, 26), (57, 27), (58, 28), (59, 29), (60, 30),
(61, 1), (62, 2), (63, 3), (64, 4), (65, 5),
(66, 6), (67, 7), (68, 8), (69, 9), (70, 10),
(71, 11), (72, 12), (73, 13), (74, 14), (75, 15),
(76, 16), (77, 17), (78, 18), (79, 19), (80, 20),
(81, 21), (82, 22), (83, 23), (84, 24), (85, 25),
(86, 26), (87, 27), (88, 28), (89, 29), (90, 30),
(91, 1), (92, 2), (93, 3), (94, 4), (95, 5),
(96, 6), (97, 7), (98, 8), (99, 9), (100, 10);

GO
INSERT INTO ThoiGianChieu (KieuNgay, NgayChieu) VALUES 
(N'Thứ 2', '2024-12-17'),
(N'Thứ 3', '2024-12-18'),
(N'Thứ 4', '2024-12-19'),
(N'Thứ 5', '2024-12-20'),
(N'Thứ 6', '2024-12-21'),
(N'Thứ 7', '2024-12-22'),
(N'C.Nhật', '2024-12-23'),
(N'Thứ 2', '2024-12-24'),
(N'Thứ 3', '2024-12-25'),
(N'Thứ 4', '2024-12-26');
GO
-- Thêm chi tiết lịch chiếu với MaLichChieu từ 1 đến 100
INSERT INTO ChiTietLichChieu (MaLichChieu, MaThoiGianChieu, ThoiGianBatDau, ThoiGianKetThuc)
VALUES
(1, 1, '10:00', '12:00'),
(2, 1, '11:00', '13:00'),
(3, 1, '12:00', '14:00'),
(4, 2, '13:00', '15:00'),
(5, 2, '14:00', '16:00'),
(6, 2, '15:00', '17:00'),
(7, 3, '16:00', '18:00'),
(8, 3, '17:00', '19:00'),
(9, 3, '18:00', '20:00'),
(10, 4, '19:00', '21:00'),
(11, 4, '10:00', '12:00'),
(12, 4, '11:00', '13:00'),
(13, 5, '12:00', '14:00'),
(14, 5, '13:00', '15:00'),
(15, 5, '14:00', '16:00'),
(16, 6, '15:00', '17:00'),
(17, 6, '16:00', '18:00'),
(18, 6, '17:00', '19:00'),
(19, 7, '18:00', '20:00'),
(20, 7, '19:00', '21:00'),
(21, 7, '10:00', '12:00'),
(22, 8, '11:00', '13:00'),
(23, 8, '12:00', '14:00'),
(24, 8, '13:00', '15:00'),
(25, 9, '14:00', '16:00'),
(26, 9, '15:00', '17:00'),
(27, 9, '16:00', '18:00'),
(28, 10, '17:00', '19:00'),
(29, 10, '18:00', '20:00'),
(30, 10, '19:00', '21:00'),
(31, 1, '10:00', '12:00'),
(32, 1, '11:00', '13:00'),
(33, 1, '12:00', '14:00'),
(34, 2, '13:00', '15:00'),
(35, 2, '14:00', '16:00'),
(36, 2, '15:00', '17:00'),
(37, 3, '16:00', '18:00'),
(38, 3, '17:00', '19:00'),
(39, 3, '18:00', '20:00'),
(40, 4, '19:00', '21:00'),
(41, 4, '10:00', '12:00'),
(42, 4, '11:00', '13:00'),
(43, 5, '12:00', '14:00'),
(44, 5, '13:00', '15:00'),
(45, 5, '14:00', '16:00'),
(46, 6, '15:00', '17:00'),
(47, 6, '16:00', '18:00'),
(48, 6, '17:00', '19:00'),
(49, 7, '18:00', '20:00'),
(50, 7, '19:00', '21:00'),
(51, 7, '10:00', '12:00'),
(52, 8, '11:00', '13:00'),
(53, 8, '12:00', '14:00'),
(54, 8, '13:00', '15:00'),
(55, 9, '14:00', '16:00'),
(56, 9, '15:00', '17:00'),
(57, 9, '16:00', '18:00'),
(58, 10, '17:00', '19:00'),
(59, 10, '18:00', '20:00'),
(60, 1, '19:00', '21:00'),
(61, 1, '10:00', '12:00'),
(62, 1, '11:00', '13:00'),
(63, 2, '12:00', '14:00'),
(64, 2, '13:00', '15:00'),
(65, 2, '14:00', '16:00'),
(66, 3, '15:00', '17:00'),
(67, 3, '16:00', '18:00'),
(68, 3, '17:00', '19:00'),
(69, 4, '18:00', '20:00'),
(70, 4, '19:00', '21:00'),
(71, 4, '10:00', '12:00'),
(72, 5, '11:00', '13:00'),
(73, 5, '12:00', '14:00'),
(74, 5, '13:00', '15:00'),
(75, 6, '14:00', '16:00'),
(76, 6, '15:00', '17:00'),
(77, 6, '16:00', '18:00'),
(78, 7, '17:00', '19:00'),
(79, 7, '18:00', '20:00'),
(80, 7, '19:00', '21:00'),
(81, 8, '10:00', '12:00'),
(82, 8, '11:00', '13:00'),
(83, 8, '12:00', '14:00'),
(84, 9, '13:00', '15:00'),
(85, 9, '14:00', '16:00'),
(86, 9, '15:00', '17:00'),
(87, 10, '16:00', '18:00'),
(88, 10, '17:00', '19:00'),
(89, 10, '18:00', '20:00'),
(90, 1, '19:00', '21:00'),
(91, 1, '10:00', '12:00'),
(92, 1, '11:00', '13:00'),
(93, 2, '12:00', '14:00'),
(94, 2, '13:00', '15:00'),
(95, 2, '14:00', '16:00'),
(96, 3, '15:00', '17:00'),
(97, 3, '16:00', '18:00'),
(98, 3, '17:00', '19:00'),
(99, 4, '18:00', '20:00'),
(100, 4, '19:00', '21:00');

GO
INSERT INTO TinhThanhPho (TenTinhThanh) VALUES
(N'Hà Nội'),
(N'Hồ Chí Minh'),
(N'Hải Phòng'),
(N'Đà Nẵng'),
(N'Cần Thơ'),
(N'An Giang'),
(N'Bà Rịa - Vũng Tàu'),
(N'Bắc Giang'),
(N'Bắc Kạn'),
(N'Bạc Liêu'),
(N'Bắc Ninh'),
(N'Bến Tre'),
(N'Bình Định'),
(N'Bình Dương'),
(N'Bình Phước'),
(N'Bình Thuận'),
(N'Cà Mau'),
(N'Cao Bằng'),
(N'Đắk Lắk'),
(N'Đắk Nông'),
(N'Điện Biên'),
(N'Đồng Nai'),
(N'Đồng Tháp'),
(N'Gia Lai'),
(N'Hà Giang'),
(N'Hà Nam'),
(N'Hà Tĩnh'),
(N'Hải Dương'),
(N'Hậu Giang'),
(N'Hòa Bình'),
(N'Hưng Yên'),
(N'Khánh Hòa'),
(N'Kiên Giang'),
(N'Kon Tum'),
(N'Lai Châu'),
(N'Lâm Đồng'),
(N'Lạng Sơn'),
(N'Lào Cai'),
(N'Long An'),
(N'Nam Định'),
(N'Nghệ An'),
(N'Ninh Bình'),
(N'Ninh Thuận'),
(N'Phú Thọ'),
(N'Phú Yên'),
(N'Quảng Bình'),
(N'Quảng Nam'),
(N'Quảng Ngãi'),
(N'Quảng Ninh'),
(N'Quảng Trị'),
(N'Sóc Trăng'),
(N'Sơn La'),
(N'Tây Ninh'),
(N'Thái Bình'),
(N'Thái Nguyên'),
(N'Thanh Hóa'),
(N'Thừa Thiên - Huế'),
(N'Tiền Giang'),
(N'Trà Vinh'),
(N'Tuyên Quang'),
(N'Vĩnh Long'),
(N'Vĩnh Phúc'),
(N'Yên Bái');

GO
INSERT INTO DiaChiRapChieuCon (MaRapChieuCon, MaTinhThanh, DiaChiRapChieu, map)
VALUES
(1, 1, N'Địa chỉ rạp chiếu con 1, Tỉnh 1', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(2, 2, N'Địa chỉ rạp chiếu con 2, Tỉnh 2', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(3, 3, N'Địa chỉ rạp chiếu con 3, Tỉnh 3', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(4, 4, N'Địa chỉ rạp chiếu con 4, Tỉnh 4', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(5, 5, N'Địa chỉ rạp chiếu con 5, Tỉnh 5', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(6, 6, N'Địa chỉ rạp chiếu con 6, Tỉnh 6', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(7, 7, N'Địa chỉ rạp chiếu con 7, Tỉnh 7', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(8, 8, N'Địa chỉ rạp chiếu con 8, Tỉnh 8', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(9, 9, N'Địa chỉ rạp chiếu con 9, Tỉnh 9', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(10, 10, N'Địa chỉ rạp chiếu con 10, Tỉnh 10', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(11, 11, N'Địa chỉ rạp chiếu con 11, Tỉnh 11', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(12, 12, N'Địa chỉ rạp chiếu con 12, Tỉnh 12', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(13, 13, N'Địa chỉ rạp chiếu con 13, Tỉnh 13', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(14, 14, N'Địa chỉ rạp chiếu con 14, Tỉnh 14', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(15, 15, N'Địa chỉ rạp chiếu con 15, Tỉnh 15', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(16, 16, N'Địa chỉ rạp chiếu con 16, Tỉnh 16', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(17, 17, N'Địa chỉ rạp chiếu con 17, Tỉnh 17', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(18, 18, N'Địa chỉ rạp chiếu con 18, Tỉnh 18', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(19, 19, N'Địa chỉ rạp chiếu con 19, Tỉnh 19', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(20, 20, N'Địa chỉ rạp chiếu con 20, Tỉnh 20', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(21, 21, N'Địa chỉ rạp chiếu con 21, Tỉnh 21', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(22, 22, N'Địa chỉ rạp chiếu con 22, Tỉnh 22', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(23, 23, N'Địa chỉ rạp chiếu con 23, Tỉnh 23', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(24, 24, N'Địa chỉ rạp chiếu con 24, Tỉnh 24', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(25, 25, N'Địa chỉ rạp chiếu con 25, Tỉnh 25', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(26, 26, N'Địa chỉ rạp chiếu con 26, Tỉnh 26', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(27, 27, N'Địa chỉ rạp chiếu con 27, Tỉnh 27', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(28, 28, N'Địa chỉ rạp chiếu con 28, Tỉnh 28', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(29, 29, N'Địa chỉ rạp chiếu con 29, Tỉnh 29', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(30, 30, N'Địa chỉ rạp chiếu con 30, Tỉnh 30', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(31, 31, N'Địa chỉ rạp chiếu con 31, Tỉnh 31', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(32, 32, N'Địa chỉ rạp chiếu con 32, Tỉnh 32', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(33, 33, N'Địa chỉ rạp chiếu con 33, Tỉnh 33', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(34, 34, N'Địa chỉ rạp chiếu con 34, Tỉnh 34', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(35, 35, N'Địa chỉ rạp chiếu con 35, Tỉnh 35', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(36, 36, N'Địa chỉ rạp chiếu con 36, Tỉnh 36', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(37, 37, N'Địa chỉ rạp chiếu con 37, Tỉnh 37', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(38, 38, N'Địa chỉ rạp chiếu con 38, Tỉnh 38', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(39, 39, N'Địa chỉ rạp chiếu con 39, Tỉnh 39', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(40, 40, N'Địa chỉ rạp chiếu con 40, Tỉnh 40', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(41, 41, N'Địa chỉ rạp chiếu con 41, Tỉnh 41', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(42, 42, N'Địa chỉ rạp chiếu con 42, Tỉnh 42', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(43, 43, N'Địa chỉ rạp chiếu con 43, Tỉnh 43', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(44, 44, N'Địa chỉ rạp chiếu con 44, Tỉnh 44', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(45, 45, N'Địa chỉ rạp chiếu con 45, Tỉnh 45', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(46, 46, N'Địa chỉ rạp chiếu con 46, Tỉnh 46', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(47, 47, N'Địa chỉ rạp chiếu con 47, Tỉnh 47', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(48, 48, N'Địa chỉ rạp chiếu con 48, Tỉnh 48', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(49, 49, N'Địa chỉ rạp chiếu con 49, Tỉnh 49', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(50, 50, N'Địa chỉ rạp chiếu con 50, Tỉnh 50', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(51, 51, N'Địa chỉ rạp chiếu con 51, Tỉnh 51', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(52, 52, N'Địa chỉ rạp chiếu con 52, Tỉnh 52', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(53, 53, N'Địa chỉ rạp chiếu con 53, Tỉnh 53', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(54, 54, N'Địa chỉ rạp chiếu con 54, Tỉnh 54', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(55, 55, N'Địa chỉ rạp chiếu con 55, Tỉnh 55', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(56, 56, N'Địa chỉ rạp chiếu con 56, Tỉnh 56', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(57, 57, N'Địa chỉ rạp chiếu con 57, Tỉnh 57', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(58, 58, N'Địa chỉ rạp chiếu con 58, Tỉnh 58', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(59, 59, N'Địa chỉ rạp chiếu con 59, Tỉnh 59', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(60, 60, N'Địa chỉ rạp chiếu con 60, Tỉnh 60', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(61, 61, N'Địa chỉ rạp chiếu con 61, Tỉnh 61', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(62, 62, N'Địa chỉ rạp chiếu con 62, Tỉnh 62', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(63, 63, N'Địa chỉ rạp chiếu con 63, Tỉnh 63', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(64, 62, N'Địa chỉ rạp chiếu con 64, Tỉnh 64', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(65, 61, N'Địa chỉ rạp chiếu con 65, Tỉnh 65', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(66, 60, N'Địa chỉ rạp chiếu con 66, Tỉnh 66', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(67, 59, N'Địa chỉ rạp chiếu con 67, Tỉnh 67', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(68, 58, N'Địa chỉ rạp chiếu con 68, Tỉnh 68', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(69, 57, N'Địa chỉ rạp chiếu con 69, Tỉnh 69', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(70, 40, N'Địa chỉ rạp chiếu con 70, Tỉnh 70', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(71, 1, N'Địa chỉ rạp chiếu con 71, Tỉnh 71', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(72, 2, N'Địa chỉ rạp chiếu con 72, Tỉnh 72', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(73, 3, N'Địa chỉ rạp chiếu con 73, Tỉnh 73', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(74, 4, N'Địa chỉ rạp chiếu con 74, Tỉnh 74', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(75, 5, N'Địa chỉ rạp chiếu con 75, Tỉnh 75', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(76, 6, N'Địa chỉ rạp chiếu con 76, Tỉnh 76', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(77, 7, N'Địa chỉ rạp chiếu con 77, Tỉnh 77', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(78, 8, N'Địa chỉ rạp chiếu con 78, Tỉnh 78', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(79, 9, N'Địa chỉ rạp chiếu con 79, Tỉnh 79', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(80, 10, N'Địa chỉ rạp chiếu con 80, Tỉnh 80', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(81, 11, N'Địa chỉ rạp chiếu con 81, Tỉnh 81', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(82, 12, N'Địa chỉ rạp chiếu con 82, Tỉnh 82', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(83, 13, N'Địa chỉ rạp chiếu con 83, Tỉnh 83', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(84, 14, N'Địa chỉ rạp chiếu con 84, Tỉnh 84', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(85, 15, N'Địa chỉ rạp chiếu con 85, Tỉnh 85', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(86, 6, N'Địa chỉ rạp chiếu con 86, Tỉnh 86', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(87, 7, N'Địa chỉ rạp chiếu con 87, Tỉnh 87', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(88, 8, N'Địa chỉ rạp chiếu con 88, Tỉnh 88', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(89, 9, N'Địa chỉ rạp chiếu con 89, Tỉnh 89', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(90, 20, N'Địa chỉ rạp chiếu con 90, Tỉnh 90', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(91, 21, N'Địa chỉ rạp chiếu con 91, Tỉnh 91', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(92, 2, N'Địa chỉ rạp chiếu con 92, Tỉnh 92', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(93, 3, N'Địa chỉ rạp chiếu con 93, Tỉnh 93', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(94, 4, N'Địa chỉ rạp chiếu con 94, Tỉnh 94', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(95, 5, N'Địa chỉ rạp chiếu con 95, Tỉnh 95', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(96, 6, N'Địa chỉ rạp chiếu con 96, Tỉnh 96', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(97, 7, N'Địa chỉ rạp chiếu con 97, Tỉnh 97', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(98, 8, N'Địa chỉ rạp chiếu con 98, Tỉnh 98', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(99, 9, N'Địa chỉ rạp chiếu con 99, Tỉnh 99', N'https://maps.google.com/?q=Vincom+Đà+Nẵng'),
(100, 10, N'Địa chỉ rạp chiếu con 100, Tỉnh 100', N'https://maps.google.com/?q=Vincom+Đà+Nẵng');
GO
INSERT INTO DanhGiaRapChieu (MaRapChieuCon, MaKhachHang, NgayDanhGia, DanhGia, DiemDanhGia)
VALUES
(1, 1, '2024-12-04', N'Rạp Chiếu con 1 rất tuyệt vời!', 5),
(2, 1, '2024-12-04', N'Rạp Chiếu con 2 dịch vụ khá ổn.', 4),
(3, 1, '2024-12-04', N'Rạp Chiếu con 3 cần cải thiện chất lượng âm thanh.', 3),
(4, 1, '2024-12-04', N'Rạp Chiếu con 4 không gian rất thoải mái.', 4),
(5, 1, '2024-12-04', N'Rạp Chiếu con 5 ánh sáng trong phòng chiếu không tốt.', 2),
(6, 1, '2024-12-04', N'Rạp Chiếu con 6 rất đẹp, đội ngũ nhân viên thân thiện.', 5),
(7, 1, '2024-12-04', N'Rạp Chiếu con 7 thiết bị chiếu hơi lỗi thời.', 3),
(8, 1, '2024-12-04', N'Rạp Chiếu con 8 dịch vụ tốt nhưng cần cải thiện vệ sinh.', 4),
(9, 1, '2024-12-04', N'Rạp Chiếu con 9 quá đông, cần thêm ghế.', 2),
(10, 1, '2024-12-04', N'Rạp Chiếu con 10 rất tuyệt vời, sẽ quay lại!', 5);

INSERT INTO DanhGiaRapChieu (MaRapChieuCon, MaKhachHang, NgayDanhGia, DanhGia, DiemDanhGia)
VALUES
(11, 2, '2024-12-04', N'Rạp Chiếu con 11 không gian thoải mái nhưng thiếu ghế VIP.', 3),
(12, 2, '2024-12-04', N'Rạp Chiếu con 12 chất lượng chiếu hình ảnh tốt.', 4),
(13, 2, '2024-12-04', N'Rạp Chiếu con 13 cần nâng cấp hệ thống điều hòa.', 2),
(14, 2, '2024-12-04', N'Rạp Chiếu con 14 là một lựa chọn tuyệt vời!', 5),
(15, 2, '2024-12-04', N'Rạp Chiếu con 15 chưa đạt chất lượng, ghế không thoải mái.', 2),
(16, 2, '2024-12-04', N'Rạp Chiếu con 16 rất tiện lợi và sạch sẽ.', 5),
(17, 2, '2024-12-04', N'Rạp Chiếu con 17 ánh sáng yếu quá, khó nhìn.', 3),
(18, 2, '2024-12-04', N'Rạp Chiếu con 18 nhân viên lịch sự, môi trường sạch sẽ.', 4),
(19, 2, '2024-12-04', N'Rạp Chiếu con 19 chất lượng âm thanh tuyệt vời!', 5),
(20, 2, '2024-12-04', N'Rạp Chiếu con 20 cần cải thiện hệ thống chiếu phim.', 3);

INSERT INTO DanhGiaRapChieu (MaRapChieuCon, MaKhachHang, NgayDanhGia, DanhGia, DiemDanhGia)
VALUES
(21, 3, '2024-12-04', N'Rạp Chiếu con 21 không gian nhỏ, không đủ chỗ ngồi.', 2),
(22, 3, '2024-12-04', N'Rạp Chiếu con 22 dịch vụ tốt nhưng có thể cải thiện.', 4),
(23, 3, '2024-12-04', N'Rạp Chiếu con 23 âm thanh chưa đủ lớn.', 3),
(24, 3, '2024-12-04', N'Rạp Chiếu con 24 chất lượng hình ảnh ổn.', 3),
(25, 3, '2024-12-04', N'Rạp Chiếu con 25 rất đẹp và thoải mái.', 5),
(26, 3, '2024-12-04', N'Rạp Chiếu con 26 cần cải thiện ghế ngồi.', 2),
(27, 3, '2024-12-04', N'Rạp Chiếu con 27 âm thanh rất hay, sẽ quay lại!', 5),
(28, 3, '2024-12-04', N'Rạp Chiếu con 28 nhân viên không thân thiện lắm.', 2),
(29, 3, '2024-12-04', N'Rạp Chiếu con 29 quá đông, không có đủ không gian.', 3),
(30, 3, '2024-12-04', N'Rạp Chiếu con 30 rất tuyệt vời, ghế ngồi thoải mái.', 4);
GO

INSERT INTO VePhim (MaLichChieu, MaKhachHang, SoLuongVe, GheNgoi, PhongChieu)
VALUES
(1, 1, 1, 5, 1),
(2, 2, 2, 6, 2),
(3, 3, 1, 7, 1),
(4, 1, 3, 8, 2),
(5, 2, 2, 9, 3),
(6, 3, 1, 10, 1),
(7, 1, 2, 11, 2),
(8, 2, 1, 12, 3),
(9, 3, 2, 13, 1),
(10, 1, 1, 14, 2),
(11, 2, 3, 15, 3),
(12, 3, 1, 16, 1),
(13, 1, 2, 17, 2),
(14, 2, 2, 18, 3),
(15, 3, 1, 19, 1),
(16, 1, 3, 20, 2),
(17, 2, 1, 21, 3),
(18, 3, 2, 22, 1),
(19, 1, 2, 23, 2),
(20, 2, 1, 24, 3),
(21, 3, 3, 25, 1),
(22, 1, 1, 26, 2),
(23, 2, 2, 27, 3),
(24, 3, 1, 28, 1),
(25, 1, 3, 29, 2),
(26, 2, 1, 30, 3),
(27, 3, 2, 31, 1),
(28, 1, 1, 32, 2),
(29, 2, 3, 33, 3),
(30, 3, 1, 34, 1),
(31, 1, 2, 35, 2),
(32, 2, 1, 36, 3),
(33, 3, 3, 37, 1),
(34, 1, 1, 38, 2),
(35, 2, 2, 39, 3),
(36, 3, 1, 40, 1),
(37, 1, 2, 41, 2),
(38, 2, 1, 42, 3),
(39, 3, 3, 43, 1),
(40, 1, 1, 44, 2),
(41, 2, 2, 45, 3),
(42, 3, 1, 46, 1),
(43, 1, 3, 47, 2),
(44, 2, 1, 48, 3),
(45, 3, 2, 49, 1),
(46, 1, 1, 50, 2),
(47, 2, 2, 51, 3),
(48, 3, 3, 52, 1),
(49, 1, 1, 53, 2),
(50, 2, 2, 54, 3),
(51, 3, 1, 55, 1),
(52, 1, 2, 56, 2),
(53, 2, 1, 57, 3),
(54, 3, 3, 58, 1),
(55, 1, 2, 59, 2),
(56, 2, 1, 60, 3),
(57, 3, 2, 61, 1),
(58, 1, 1, 62, 2),
(59, 2, 3, 63, 3),
(60, 3, 1, 64, 1),
(61, 1, 2, 65, 2),
(62, 2, 1, 66, 3),
(63, 3, 2, 67, 1),
(64, 1, 1, 68, 2),
(65, 2, 3, 69, 3),
(66, 3, 1, 70, 1),
(67, 1, 2, 71, 2),
(68, 2, 1, 72, 3),
(69, 3, 3, 73, 1),
(70, 1, 2, 74, 2),
(71, 2, 1, 75, 3),
(72, 3, 1, 76, 1),
(73, 1, 3, 77, 2),
(74, 2, 1, 78, 3),
(75, 3, 2, 79, 1),
(76, 1, 1, 80, 2),
(77, 2, 3, 81, 3),
(78, 3, 1, 82, 1),
(79, 1, 2, 83, 2),
(80, 2, 1, 84, 3),
(81, 3, 3, 85, 1),
(82, 1, 2, 86, 2),
(83, 2, 1, 87, 3),
(84, 3, 2, 88, 1),
(85, 1, 1, 89, 2),
(86, 2, 3, 90, 3),
(87, 3, 1, 91, 1),
(88, 1, 2, 92, 2),
(89, 2, 1, 93, 3),
(90, 3, 3, 94, 1),
(91, 1, 2, 95, 2),
(92, 2, 1, 96, 3),
(93, 3, 1, 97, 1),
(94, 1, 3, 98, 2),
(95, 2, 2, 99, 3),
(96, 3, 1, 100, 1);

GO
INSERT INTO TinhTrangVe (MaVe, TinhTrang, ThoiGian)
VALUES
(1, N'Đã sử dụng', '2024-12-04 10:00:00'),
(2, N'Chưa sử dụng', '2024-12-04 10:30:00'),
(3, N'Đã khứ hồi', '2024-12-04 11:00:00'),
(4, N'Đã sử dụng', '2024-12-04 11:30:00'),
(5, N'Chưa sử dụng', '2024-12-04 12:00:00'),
(6, N'Đã khứ hồi', '2024-12-04 12:30:00'),
(7, N'Đã sử dụng', '2024-12-04 13:00:00'),
(8, N'Chưa sử dụng', '2024-12-04 13:30:00'),
(9, N'Đã khứ hồi', '2024-12-04 14:00:00'),
(10, N'Đã sử dụng', '2024-12-04 14:30:00'),
(11, N'Chưa sử dụng', '2024-12-04 15:00:00'),
(12, N'Đã khứ hồi', '2024-12-04 15:30:00'),
(13, N'Đã sử dụng', '2024-12-04 16:00:00'),
(14, N'Chưa sử dụng', '2024-12-04 16:30:00'),
(15, N'Đã khứ hồi', '2024-12-04 17:00:00'),
(16, N'Đã sử dụng', '2024-12-04 17:30:00'),
(17, N'Chưa sử dụng', '2024-12-04 18:00:00'),
(18, N'Đã khứ hồi', '2024-12-04 18:30:00'),
(19, N'Đã sử dụng', '2024-12-04 19:00:00'),
(20, N'Chưa sử dụng', '2024-12-04 19:30:00'),
(21, N'Đã khứ hồi', '2024-12-04 20:00:00'),
(22, N'Đã sử dụng', '2024-12-04 20:30:00'),
(23, N'Chưa sử dụng', '2024-12-04 21:00:00'),
(24, N'Đã khứ hồi', '2024-12-04 21:30:00'),
(25, N'Đã sử dụng', '2024-12-04 22:00:00'),
(26, N'Chưa sử dụng', '2024-12-04 22:30:00'),
(27, N'Đã khứ hồi', '2024-12-04 23:00:00'),
(28, N'Đã sử dụng', '2024-12-04 23:30:00'),
(29, N'Chưa sử dụng', '2024-12-05 00:00:00'),
(30, N'Đã khứ hồi', '2024-12-05 00:30:00'),
(31, N'Đã sử dụng', '2024-12-05 01:00:00'),
(32, N'Chưa sử dụng', '2024-12-05 01:30:00'),
(33, N'Đã khứ hồi', '2024-12-05 02:00:00'),
(34, N'Đã sử dụng', '2024-12-05 02:30:00'),
(35, N'Chưa sử dụng', '2024-12-05 03:00:00'),
(36, N'Đã khứ hồi', '2024-12-05 03:30:00'),
(37, N'Đã sử dụng', '2024-12-05 04:00:00'),
(38, N'Chưa sử dụng', '2024-12-05 04:30:00'),
(39, N'Đã khứ hồi', '2024-12-05 05:00:00'),
(40, N'Đã sử dụng', '2024-12-05 05:30:00'),
(41, N'Chưa sử dụng', '2024-12-05 06:00:00'),
(42, N'Đã khứ hồi', '2024-12-05 06:30:00'),
(43, N'Đã sử dụng', '2024-12-05 07:00:00'),
(44, N'Chưa sử dụng', '2024-12-05 07:30:00'),
(45, N'Đã khứ hồi', '2024-12-05 08:00:00'),
(46, N'Đã sử dụng', '2024-12-05 08:30:00'),
(47, N'Chưa sử dụng', '2024-12-05 09:00:00'),
(48, N'Đã khứ hồi', '2024-12-05 09:30:00'),
(49, N'Đã sử dụng', '2024-12-05 10:00:00'),
(50, N'Chưa sử dụng', '2024-12-05 10:30:00'),
(51, N'Đã khứ hồi', '2024-12-05 11:00:00'),
(52, N'Đã sử dụng', '2024-12-05 11:30:00'),
(53, N'Chưa sử dụng', '2024-12-05 12:00:00'),
(54, N'Đã khứ hồi', '2024-12-05 12:30:00'),
(55, N'Đã sử dụng', '2024-12-05 13:00:00'),
(56, N'Chưa sử dụng', '2024-12-05 13:30:00'),
(57, N'Đã khứ hồi', '2024-12-05 14:00:00'),
(58, N'Đã sử dụng', '2024-12-05 14:30:00'),
(59, N'Chưa sử dụng', '2024-12-05 15:00:00'),
(60, N'Đã khứ hồi', '2024-12-05 15:30:00'),
(61, N'Đã sử dụng', '2024-12-05 16:00:00'),
(62, N'Chưa sử dụng', '2024-12-05 16:30:00'),
(63, N'Đã khứ hồi', '2024-12-05 17:00:00'),
(64, N'Đã sử dụng', '2024-12-05 17:30:00'),
(65, N'Chưa sử dụng', '2024-12-05 18:00:00'),
(66, N'Đã khứ hồi', '2024-12-05 18:30:00'),
(67, N'Đã sử dụng', '2024-12-05 19:00:00'),
(68, N'Chưa sử dụng', '2024-12-05 19:30:00'),
(69, N'Đã khứ hồi', '2024-12-05 20:00:00'),
(70, N'Đã sử dụng', '2024-12-05 20:30:00'),
(71, N'Chưa sử dụng', '2024-12-05 21:00:00'),
(72, N'Đã khứ hồi', '2024-12-05 21:30:00'),
(73, N'Đã sử dụng', '2024-12-05 22:00:00'),
(74, N'Chưa sử dụng', '2024-12-05 22:30:00'),
(75, N'Đã khứ hồi', '2024-12-05 23:00:00'),
(76, N'Đã sử dụng', '2024-12-05 23:30:00'),
(77, N'Chưa sử dụng', '2024-12-06 00:00:00'),
(78, N'Đã khứ hồi', '2024-12-06 00:30:00'),
(79, N'Đã sử dụng', '2024-12-06 01:00:00'),
(80, N'Chưa sử dụng', '2024-12-06 01:30:00'),
(81, N'Đã khứ hồi', '2024-12-06 02:00:00'),
(82, N'Đã sử dụng', '2024-12-06 02:30:00'),
(83, N'Chưa sử dụng', '2024-12-06 03:00:00'),
(84, N'Đã khứ hồi', '2024-12-06 03:30:00'),
(85, N'Đã sử dụng', '2024-12-06 04:00:00'),
(86, N'Chưa sử dụng', '2024-12-06 04:30:00'),
(87, N'Đã khứ hồi', '2024-12-06 05:00:00'),
(88, N'Đã sử dụng', '2024-12-06 05:30:00'),
(89, N'Chưa sử dụng', '2024-12-06 06:00:00'),
(90, N'Đã khứ hồi', '2024-12-06 06:30:00'),
(91, N'Đã sử dụng', '2024-12-06 07:00:00'),
(92, N'Chưa sử dụng', '2024-12-06 07:30:00'),
(93, N'Đã khứ hồi', '2024-12-06 08:00:00'),
(94, N'Đã sử dụng', '2024-12-06 08:30:00'),
(95, N'Chưa sử dụng', '2024-12-06 09:00:00'),
(96, N'Đã khứ hồi', '2024-12-06 09:30:00');
GO
INSERT INTO ThanhToan (MaVe, QRThanhToan, TongTien)
VALUES
(1, 'QR1234567890', 100000),
(2, 'QR2345678901', 120000),
(3, 'QR3456789012', 90000),
(4, 'QR4567890123', 110000),
(5, 'QR5678901234', 95000),
(6, 'QR6789012345', 105000),
(7, 'QR7890123456', 115000),
(8, 'QR8901234567', 125000),
(9, 'QR9012345678', 130000),
(10, 'QR0123456789', 100000);
GO
INSERT INTO DoiTuongApDung (DoiTuongApDung, MucApDung)
VALUES
(N'Khách hàng mới, đơn hàng tối thiểu', 50000),  
(N'Đơn tối thiểu', 100000), 
(N'Đơn tối thiểu', 200000), 
(N'Đơn tối thiểu', 300000), 
(N'Giảm tối đa', 50000),
(N'Giảm tối đa', 100000),
(N'Giảm tối đa', 150000); 
GO
INSERT INTO VoucherDoiTac (MaRapChieu, TenVoucher, MaDoiTuongApDung, TrangThaiGiam, MucGiam, HanSuDung, TrangThaiSuDung, SoLuongToiDa)
VALUES
(1, 'VOUCHER10NEW', 1, N'Đã khứ hồi', 10, '2024-12-31', N'Chưa sử dụng', 100),
(2, 'VOUCHER15MINIMUM', 2, N'Đã khứ hồi', 15, '2024-12-31', N'Chưa sử dụng', 150),
(3, 'VOUCHER20LARGE', 2, N'Đã khứ hồi', 20, '2025-01-15', N'Chưa sử dụng', 50),
(4, 'VOUCHER30VIP', 3, N'Giá tiền', 30000, '2025-02-01', N'Chưa sử dụng', 200),
(5, 'VOUCHER5OLD', 1, N'Giá tiền', 5000, '2024-12-15', N'Chưa sử dụng', 120),
(6, 'VOUCHER25HOLIDAY', 1, N'Đã khứ hồi', 25, '2024-12-25', N'Chưa sử dụng', 80),
(7, 'VOUCHER40NEWCUSTOMER', 1, N'Đã khứ hồi', 40, '2024-12-31', N'Chưa sử dụng', 100),
(8, 'VOUCHER50MINIMUMORDER', 2, N'Giá tiền', 50000, '2025-01-01', N'Chưa sử dụng', 30),
(9, 'VOUCHER10VIPCUSTOMER', 3, N'Đã khứ hồi', 10, '2024-12-31', N'Chưa sử dụng', 200),
(10, 'VOUCHER15EARLYBIRD', 2, N'Giá tiền', 15000, '2025-01-10', N'Chưa sử dụng', 150);
GO
INSERT INTO VoucherCuaToi (MaKhachHang, TenVoucher, MaDoiTuongApDung, TrangThaiGiam, MucGiam, HanSuDung, TrangThaiSuDung, SoLuongToiDa)
VALUES 
(1, 'Voucher1', 1, N'Đã khứ hồi', 20, '2024-12-31', N'Chưa sử dụng', 100),
(2, 'Voucher2', 2, N'Giá tiền', 50000, '2024-12-31', N'Chưa sử dụng', 50),
(3, 'Voucher3', 3, N'Đã khứ hồi', 15, '2024-12-31', N'Chưa sử dụng', 200),
(1, 'Voucher4', 1, N'Giá tiền', 30000, '2024-12-31', N'Chưa sử dụng', 150),
(2, 'Voucher5', 2, N'Đã khứ hồi', 10, '2024-12-31', N'Chưa sử dụng', 120),
(3, 'Voucher6', 3, N'Giá tiền', 20000, '2024-12-31', N'Chưa sử dụng', 80),
(1, 'Voucher7', 1, N'Đã khứ hồi', 25, '2024-12-31', N'Chưa sử dụng', 130),
(2, 'Voucher8', 2, N'Giá tiền', 40000, '2024-12-31', N'Chưa sử dụng', 100),
(3, 'Voucher9', 3, N'Đã khứ hồi', 30, '2024-12-31', N'Chưa sử dụng', 150),
(1, 'Voucher10', 1, N'Giá tiền', 35000, '2024-12-31', N'Chưa sử dụng', 180);
Go
INSERT INTO VoucherUngDung (AnhUngDung, TenVoucher, MaDoiTuongApDung, TrangThaiGiam, MucGiam, HanSuDung, TrangThaiSuDung, SoLuongToiDa)
VALUES 
('logo', 'VoucherApp1', 1, N'Đã khứ hồi', 10, '2024-12-31', N'Chưa sử dụng', 100),
('logo', 'VoucherApp2', 2, N'Giá tiền', 50000, '2024-12-31', N'Chưa sử dụng', 200),
('logo', 'VoucherApp3', 3, N'Đã khứ hồi', 25, '2024-12-31', N'Chưa sử dụng', 150),
('logo', 'VoucherApp4', 4, N'Giá tiền', 100000, '2024-12-31', N'Chưa sử dụng', 75),
('logo', 'VoucherApp5', 5, N'Đã khứ hồi', 15, '2024-12-31', N'Chưa sử dụng', 200),
('logo', 'VoucherApp6', 1, N'Giá tiền', 20000, '2024-12-31', N'Chưa sử dụng', 250),
('logo', 'VoucherApp7', 2, N'Đã khứ hồi', 30, '2024-12-31', N'Chưa sử dụng', 300),
('logo', 'VoucherApp8', 3, N'Giá tiền', 70000, '2024-12-31', N'Chưa sử dụng', 120),
('logo', 'VoucherApp9', 4, N'Đã khứ hồi', 20, '2024-12-31', N'Chưa sử dụng', 400),
('logo', 'VoucherApp10', 5, N'Giá tiền', 150000, '2024-12-31', N'Chưa sử dụng', 500);
