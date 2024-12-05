package com.example.cinebooker.LeDucThien.entity;

public class heThongRapChieuEntity {
    private int moviePoster;
    private String ten, moTa;
    private Double vote,location, comment;

    public heThongRapChieuEntity() {
    }

    public heThongRapChieuEntity(int moviePoster, String ten, String moTa, Double vote, Double location, Double comment) {
        this.moviePoster = moviePoster;
        this.ten = ten;
        this.moTa = moTa;
        this.vote = vote;
        this.location = location;
        this.comment = comment;
    }

    public int getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(int moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Double getVote() {
        return vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    public Double getLocation() {
        return location;
    }

    public void setLocation(Double location) {
        this.location = location;
    }

    public Double getComment() {
        return comment;
    }

    public void setComment(Double comment) {
        this.comment = comment;
    }

    public static class ent_PhimSapChieu {

        private int maPhim;
        private String anhPhim;
        private String tenPhim;
        private int tuoi;
        private String dinhDangPhim;
        private String tenTheLoai;
        private String ngayKhoiChieu;  // Ngày khởi chiếu dưới dạng String
        private String ngayKetThuc;    // Ngày kết thúc dưới dạng String
        private String trangThaiChieu;
        private String thoiLuong;      // Thời lượng dưới dạng String (ví dụ: "02:30")

        // Constructor mặc định
        public ent_PhimSapChieu() {
        }

        // Constructor đầy đủ
        public ent_PhimSapChieu(int maPhim, String anhPhim, String tenPhim, int tuoi, String dinhDangPhim,
                                String tenTheLoai, String ngayKhoiChieu, String ngayKetThuc, String trangThaiChieu,
                                String thoiLuong) {
            this.maPhim = maPhim;
            this.anhPhim = anhPhim;
            this.tenPhim = tenPhim;
            this.tuoi = tuoi;
            this.dinhDangPhim = dinhDangPhim;
            this.tenTheLoai = tenTheLoai;
            this.ngayKhoiChieu = ngayKhoiChieu;
            this.ngayKetThuc = ngayKetThuc;
            this.trangThaiChieu = trangThaiChieu;
            this.thoiLuong = thoiLuong;
        }

        public int getMaPhim() {
            return maPhim;
        }

        public void setMaPhim(int maPhim) {
            this.maPhim = maPhim;
        }

        public String getAnhPhim() {
            return anhPhim;
        }

        public void setAnhPhim(String anhPhim) {
            this.anhPhim = anhPhim;
        }

        public String getTenPhim() {
            return tenPhim;
        }

        public void setTenPhim(String tenPhim) {
            this.tenPhim = tenPhim;
        }

        public int getTuoi() {
            return tuoi;
        }

        public void setTuoi(int tuoi) {
            this.tuoi = tuoi;
        }

        public String getDinhDangPhim() {
            return dinhDangPhim;
        }

        public void setDinhDangPhim(String dinhDangPhim) {
            this.dinhDangPhim = dinhDangPhim;
        }

        public String getTenTheLoai() {
            return tenTheLoai;
        }

        public void setTenTheLoai(String tenTheLoai) {
            this.tenTheLoai = tenTheLoai;
        }

        public String getNgayKhoiChieu() {
            return ngayKhoiChieu;
        }

        public void setNgayKhoiChieu(String ngayKhoiChieu) {
            this.ngayKhoiChieu = ngayKhoiChieu;
        }

        public String getNgayKetThuc() {
            return ngayKetThuc;
        }

        public void setNgayKetThuc(String ngayKetThuc) {
            this.ngayKetThuc = ngayKetThuc;
        }

        public String getTrangThaiChieu() {
            return trangThaiChieu;
        }

        public void setTrangThaiChieu(String trangThaiChieu) {
            this.trangThaiChieu = trangThaiChieu;
        }

        public String getThoiLuong() {
            return thoiLuong;
        }

        public void setThoiLuong(String thoiLuong) {
            this.thoiLuong = thoiLuong;
        }
    }
}
