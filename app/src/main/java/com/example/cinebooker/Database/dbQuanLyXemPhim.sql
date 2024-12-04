CREATE DATABASE dbQuanLyXemPhim
GO
USE dbQuanLyXemPhim
GO
-- Bảng KhachHang
CREATE TABLE KhachHang (
    MaKhachHang INT PRIMARY KEY IDENTITY(1,1),
    Email VARCHAR(255) NOT NULL,
    MatKhau VARCHAR(16) NOT NULL,
    AnhKhachHang VARCHAR(MAX),
    TenKhachHang NVARCHAR(50) NOT NULL
);

-- Bảng CapBacChiTieu
CREATE TABLE CapBacChiTieu (
    MaCapBacChiTieu INT PRIMARY KEY IDENTITY(1,1),
    AnhCapBac VARCHAR(MAX),
    TenCapBac NVARCHAR(50) NOT NULL,
    HanMucChiTieu INT NOT NULL
);

-- Bảng ChiTietCapBac
CREATE TABLE ChiTietCapBac (
    MaChiTietCapBac INT PRIMARY KEY IDENTITY(1,1),
    MaKhachHang INT NOT NULL, -- Liên kết đến bảng KhachHang
    MaCapBacChiTieu INT NOT NULL, -- Liên kết đến bảng CapBacChiTieu
    ThoiHanReset DATETIME
);

-- Bảng Phim
CREATE TABLE Phim (
    MaPhim INT PRIMARY KEY IDENTITY(1,1),
    AnhPhim VARCHAR(MAX),
    TenPhim NVARCHAR(255) NOT NULL,
	Tuoi int,
	DinhDangPhim NVARCHAR(255),
    MaTheLoai INT,
    NgayKhoiChieu DATETIME NOT NULL,
	NgayKetThuc DATETIME NOT NULL,
    TrangThaiChieu NVARCHAR(50),
	ThoiLuong Time
);

-- Bảng TheLoai
CREATE TABLE TheLoai (
    MaTheLoai INT PRIMARY KEY IDENTITY(1,1),
    TenTheLoai NVARCHAR(50) NOT NULL
);

-- Bảng DanhGia
CREATE TABLE DanhGia (
    MaDanhGia INT PRIMARY KEY IDENTITY(1,1),
    MaKhachHang INT,
    MaPhim INT,
    NgayDanhGia DATETIME NOT NULL,
    DanhGia NVARCHAR(1000),
    DiemDanhGia FLOAT,
    LuotThich INT DEFAULT 0
);

-- Bảng LichChieu
CREATE TABLE LichChieu (
    MaLichChieu INT PRIMARY KEY IDENTITY(1,1),
    MaRapChieuCon INT,
    MaPhim INT
);

-- Bảng ChiTietLichChieu
CREATE TABLE ChiTietLichChieu (
    MaChiTietLichChieu INT PRIMARY KEY IDENTITY(1,1),
    MaLichChieu INT,
    NgayChieu DATETIME NOT NULL,
    ThoiGianBatDau TIME NOT NULL,
    ThoiGianKetThuc TIME NOT NULL
);

-- Bảng TinhThanhPho
CREATE TABLE TinhThanhPho (
    MaTinhThanh INT PRIMARY KEY IDENTITY(1,1),
    TenTinhThanh NVARCHAR(255)
);

-- Bảng DiaChiRapChieu
CREATE TABLE DiaChiRapChieuCon (
    MaDiaChiRapChieuCon INT PRIMARY KEY IDENTITY(1,1),
	MaRapChieuCon INT,
    MaTinhThanh INT,
    DiaChiRapChieu NVARCHAR(255),
	map VARCHAR(MAX)
);


-- Bảng RapChieu
CREATE TABLE RapChieu (
    MaRapChieu INT PRIMARY KEY IDENTITY(1,1),
    AnhRapChieu VARCHAR(MAX),
    TenRapChieu NVARCHAR(255) NOT NULL,
    MoTaRapChieu NVARCHAR(255)
);

-- Bảng RapChieuCon
CREATE TABLE RapChieuCon (
    MaRapChieuCon INT PRIMARY KEY IDENTITY(1,1),
    MaRapChieu INT,
    TenRapChieuCon NVARCHAR(255) NOT NULL
);


-- Bảng DanhGiaRapChieu
CREATE TABLE DanhGiaRapChieu (
    MaDanhGiaRapChieu INT PRIMARY KEY IDENTITY(1,1),
    MaRapChieuCon INT,
    MaKhachHang INT,
    NgayDanhGia DATETIME NOT NULL,
    DanhGia NVARCHAR(1000),
    DiemDanhGia FLOAT
);

-- Bảng VePhim
CREATE TABLE VePhim (
    MaVe INT PRIMARY KEY IDENTITY(1,1),
    MaLichChieu INT,
    MaKhachHang INT,
    SoLuongVe INT DEFAULT 1,
    GheNgoi INT,
    PhongChieu INT
);

-- Bảng TinhTrangVe
CREATE TABLE TinhTrangVe (
    MaTinhTrang INT PRIMARY KEY IDENTITY(1,1),
	MaVe INT,
    TinhTrang NVARCHAR(50),
    ThoiGian DATETIME NOT NULL
);

-- Bảng ThanhToan
CREATE TABLE ThanhToan (
    MaThanhToan INT PRIMARY KEY IDENTITY(1,1),
    MaVe INT,
    QRThanhToan VARCHAR(MAX),
    TongTien FLOAT NOT NULL
);

-- Bảng VoucherDoiTac
CREATE TABLE VoucherDoiTac (
    MaVoucherDoiTac INT PRIMARY KEY IDENTITY(1,1),
    MaRapChieu INT,
    TenVoucher VARCHAR(50) NOT NULL,
    MaDoiTuongApDung INT,
    TrangThaiGiam NVARCHAR(50),
    MucGiam INT NOT NULL,
    HanSuDung DATETIME NOT NULL,
    TrangThaiSuDung NVARCHAR(50),
	SoLuongToiDa int
);

-- Bảng VoucherCuaToi
CREATE TABLE VoucherCuaToi (
    MaVoucherCuaToi INT PRIMARY KEY IDENTITY(1,1),
    MaCapBacChiTieu INT,
    TenVoucher VARCHAR(50) NOT NULL,
    MaDoiTuongApDung INT,
    TrangThaiGiam NVARCHAR(50),
    MucGiam INT NOT NULL,
    HanSuDung DATETIME NOT NULL,
    TrangThaiSuDung NVARCHAR(50),
	SoLuongToiDa int
);

-- Bảng VoucherUngDung
CREATE TABLE VoucherUngDung (
    MaVoucherUngDung INT PRIMARY KEY IDENTITY(1,1),
	AnhUngDung VARCHAR(MAX),
    TenVoucher VARCHAR(50) NOT NULL,
    MaDoiTuongApDung INT,
    TrangThaiGiam NVARCHAR(50),
    MucGiam INT NOT NULL,
    HanSuDung DATETIME NOT NULL,
    TrangThaiSuDung NVARCHAR(50),
	SoLuongToiDa int
);
-- Bảng DoiTuongApDung
CREATE TABLE DoiTuongApDung (
    MaDoiTuongApDung INT PRIMARY KEY IDENTITY(1,1),
    DoiTuongApDung NVARCHAR(255) NOT NULL,
    MucApDung INT NOT NULL
);

GO
-- Ràng buộc khóa ngoại cho bảng CapBacChiTieu (Không có khóa phụ, tạo trước)

-- Ràng buộc khóa ngoại cho bảng DoiTuongApDung (Không có khóa phụ, tạo trước)

-- Ràng buộc khóa ngoại cho bảng TheLoai (Không có khóa phụ, tạo trước)

-- Thêm ràng buộc UNIQUE cho Email trong bảng KhachHang
ALTER TABLE KhachHang
ADD CONSTRAINT UQ_KhachHang_Email UNIQUE (Email);

-- Thêm ràng buộc CHECK cho HanMucChiTieu trong bảng CapBacChiTieu
ALTER TABLE CapBacChiTieu
ADD CONSTRAINT CK_HanMucChiTieu_Positive CHECK (HanMucChiTieu > 0);

-- Thêm ràng buộc FOREIGN KEY cho MaKhachHang trong bảng ChiTietCapBac
ALTER TABLE ChiTietCapBac
ADD CONSTRAINT FK_ChiTietCapBac_KhachHang FOREIGN KEY (MaKhachHang) 
REFERENCES KhachHang (MaKhachHang)
ON DELETE CASCADE 
ON UPDATE CASCADE;

-- Thêm ràng buộc FOREIGN KEY cho MaCapBacChiTieu trong bảng ChiTietCapBac
ALTER TABLE ChiTietCapBac
ADD CONSTRAINT FK_ChiTietCapBac_CapBacChiTieu FOREIGN KEY (MaCapBacChiTieu) 
REFERENCES CapBacChiTieu (MaCapBacChiTieu)
ON DELETE NO ACTION 
ON UPDATE CASCADE;

-- Ràng buộc khóa ngoại cho bảng Phim
ALTER TABLE Phim
ADD CONSTRAINT FK_Phim_TheLoai
FOREIGN KEY (MaTheLoai) REFERENCES TheLoai(MaTheLoai);

-- Ràng buộc khóa ngoại cho bảng LichChieu
ALTER TABLE LichChieu
ADD CONSTRAINT FK_LichChieu_RapChieuCon
FOREIGN KEY (MaRapChieuCon) REFERENCES RapChieuCon(MaRapChieuCon),
CONSTRAINT FK_LichChieu_Phim
FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim);

-- Ràng buộc khóa ngoại cho bảng RapChieuCon
ALTER TABLE RapChieuCon
ADD CONSTRAINT FK_RapChieuCon_RapChieu
FOREIGN KEY (MaRapChieu) REFERENCES RapChieu(MaRapChieu);

-- Tạo ràng buộc giữa bảng DiaChiRapChieuCon và TinhThanhPho
ALTER TABLE DiaChiRapChieuCon
ADD CONSTRAINT FK_DiaChiRapChieuCon_TinhThanhPho
FOREIGN KEY (MaTinhThanh) REFERENCES TinhThanhPho(MaTinhThanh)
ON DELETE CASCADE;  -- Xóa các địa chỉ nếu thành phố bị xóa

-- Tạo ràng buộc giữa bảng DiaChiRapChieuCon và RapChieuCon
ALTER TABLE DiaChiRapChieuCon
ADD CONSTRAINT FK_DiaChiRapChieuCon_RapChieuCon
FOREIGN KEY (MaRapChieuCon) REFERENCES RapChieuCon(MaRapChieuCon)
ON DELETE CASCADE;  -- Xóa các địa chỉ nếu RapChieuCon bị xóa


-- Ràng buộc khóa ngoại cho bảng ChiTietLichChieu
ALTER TABLE ChiTietLichChieu
ADD CONSTRAINT FK_ChiTietLichChieu_LichChieu
FOREIGN KEY (MaLichChieu) REFERENCES LichChieu(MaLichChieu);

-- Ràng buộc khóa ngoại cho bảng DanhGia
ALTER TABLE DanhGia
ADD CONSTRAINT FK_DanhGia_KhachHang
FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
CONSTRAINT FK_DanhGia_Phim
FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim);

-- Ràng buộc khóa ngoại cho bảng DanhGiaRapChieu
ALTER TABLE DanhGiaRapChieu
ADD CONSTRAINT FK_DanhGiaRapChieu_RapChieuCon
FOREIGN KEY (MaRapChieuCon) REFERENCES RapChieuCon(MaRapChieuCon),
CONSTRAINT FK_DanhGiaRapChieu_KhachHang
FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang);

-- Ràng buộc khóa ngoại cho bảng VePhim
ALTER TABLE VePhim
ADD CONSTRAINT FK_VePhim_LichChieu
FOREIGN KEY (MaLichChieu) REFERENCES LichChieu(MaLichChieu),
CONSTRAINT FK_VePhim_KhachHang
FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang);

-- Ràng buộc khóa ngoại cho bảng TinhTrangVe
ALTER TABLE TinhTrangVe
ADD CONSTRAINT FK_TinhTrangVe_VePhim
FOREIGN KEY (MaVe) REFERENCES VePhim(MaVe);

-- Ràng buộc khóa ngoại cho bảng ThanhToan
ALTER TABLE ThanhToan
ADD CONSTRAINT FK_ThanhToan_VePhim
FOREIGN KEY (MaVe) REFERENCES VePhim(MaVe);

-- Ràng buộc khóa ngoại cho bảng VoucherCuaToi
ALTER TABLE VoucherCuaToi
ADD CONSTRAINT FK_VoucherCuaToi_CapBacChiTieu
FOREIGN KEY (MaCapBacChiTieu) REFERENCES CapBacChiTieu(MaCapBacChiTieu),
CONSTRAINT FK_VoucherCuaToi_DoiTuongApDung
FOREIGN KEY (MaDoiTuongApDung) REFERENCES DoiTuongApDung(MaDoiTuongApDung);

ALTER TABLE VoucherCuaToi
ADD CONSTRAINT UQ_VoucherCuaToi_TenVoucher UNIQUE (TenVoucher);

-- Ràng buộc khóa ngoại cho bảng VoucherDoiTac
ALTER TABLE VoucherDoiTac
ADD CONSTRAINT FK_VoucherDoiTac_RapChieu
FOREIGN KEY (MaRapChieu) REFERENCES RapChieu(MaRapChieu),
CONSTRAINT FK_VoucherDoiTac_DoiTuongApDung
FOREIGN KEY (MaDoiTuongApDung) REFERENCES DoiTuongApDung(MaDoiTuongApDung);

ALTER TABLE VoucherDoiTac
ADD CONSTRAINT UQ_VoucherDoiTac_TenVoucher UNIQUE (TenVoucher);

-- Ràng buộc khóa ngoại cho bảng VoucherUngDung
ALTER TABLE VoucherUngDung
ADD CONSTRAINT FK_VoucherUngDung_DoiTuongApDung
FOREIGN KEY (MaDoiTuongApDung) REFERENCES DoiTuongApDung(MaDoiTuongApDung);

ALTER TABLE VoucherUngDung
ADD CONSTRAINT UQ_VoucherUngDung_TenVoucher UNIQUE (TenVoucher);
