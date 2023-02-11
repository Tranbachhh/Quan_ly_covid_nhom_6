package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NhanKhau {
    private int id;
    private String hoTen;
    private Date ngaySinh;
    private String noiSinh;
    private String gioiTinh;
    private String nguyenQuan;
    private String danToc;
    private String tonGiao;
    private String quocTich;
    private String ngheNghiep;
    private String noiLamViec;
    private String CMND;
    private Date ngayCap;
    private String noiCap;
    private Date chuyenDenNgay;
    private String noiThuongTruTruoc;
    private String trangThai;
    private String bieuDienNgaySinh;
    private String bieuDienChuyenDenNgay;
    private String bieuDienNgayCap;


    public NhanKhau(int id,String hoTen, Date ngaySinh, String noiSinh, String gioiTinh, String CMND, String danToc, String tonGiao, String quocTich, String trangThai) {
        this.id = id;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.gioiTinh = gioiTinh;
        this.CMND = CMND;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.trangThai = trangThai;
    }

    public boolean sosanh(HoKhauNhanKhau a){
        if(this.id == a.getIdNhanKhau()) return true;
        return false;
    }

    public NhanKhau(int id, String hoTen, String bieuDienNgaySinh, String gioiTinh, String CMND, String trangThai) {
        this.id = id;
        this.hoTen = hoTen;
        this.bieuDienNgaySinh = bieuDienNgaySinh;
        this.gioiTinh = gioiTinh;
        this.CMND = CMND;
        this.trangThai = trangThai;
    }


    public NhanKhau(int id,String hoTen, Date ngaySinh, String noiSinh, String gioiTinh, String nguyenQuan, String danToc, String tonGiao, String quocTich) {
        this.id = id;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.gioiTinh = gioiTinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
    }

    public NhanKhau(int id, String hoTen, String bieuDienNgaySinh, String noiSinh, String gioiTinh,String nguyenQuan,String danToc,
                    String tonGiao, String quocTich, String ngheNghiep, String noiLamViec,  String CMND, String bieuDienNgayCap,
                    String noiCap, String bieuDienChuyenDenNgay, String noiThuongTruTruoc, String trangThai) {
        this.id = id;
        this.hoTen = hoTen;
        this.bieuDienNgaySinh = bieuDienNgaySinh;
        this.gioiTinh = gioiTinh;
        this.noiSinh=noiSinh;
        this.CMND = CMND;
        this.trangThai = trangThai;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.noiCap = noiCap;
        this.bieuDienChuyenDenNgay = bieuDienChuyenDenNgay;
        this.bieuDienNgayCap = bieuDienNgayCap;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.noiThuongTruTruoc = noiThuongTruTruoc;
    }

    public NhanKhau(String hoTen, String bieuDienNgaySinh, String noiSinh, String gioiTinh,String nguyenQuan,String danToc,
                    String tonGiao, String quocTich, String ngheNghiep, String noiLamViec,  String CMND, String bieuDienNgayCap,
                    String noiCap, String bieuDienChuyenDenNgay, String noiThuongTruTruoc, String trangThai) {
        this.hoTen = hoTen;
        this.bieuDienNgaySinh = bieuDienNgaySinh;
        this.gioiTinh = gioiTinh;
        this.noiSinh=noiSinh;
        this.CMND = CMND;
        this.trangThai = trangThai;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.noiCap = noiCap;
        this.bieuDienChuyenDenNgay = bieuDienChuyenDenNgay;
        this.bieuDienNgayCap = bieuDienNgayCap;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.noiThuongTruTruoc = noiThuongTruTruoc;
    }

    public NhanKhau(String hoTen, String bieuDienNgaySinh, String noiSinh, String gioiTinh,String nguyenQuan,String danToc,
                    String tonGiao, String quocTich, String ngheNghiep, String noiLamViec,  String CMND, String bieuDienNgayCap,
                    String noiCap, String bieuDienChuyenDenNgay, String noiThuongTruTruoc) {
        this.hoTen = hoTen;
        this.bieuDienNgaySinh = bieuDienNgaySinh;
        this.gioiTinh = gioiTinh;
        this.noiSinh=noiSinh;
        this.CMND = CMND;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
        this.noiCap = noiCap;
        this.bieuDienChuyenDenNgay = bieuDienChuyenDenNgay;
        this.bieuDienNgayCap = bieuDienNgayCap;
        this.ngheNghiep = ngheNghiep;
        this.noiLamViec = noiLamViec;
        this.noiThuongTruTruoc = noiThuongTruTruoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }


    public String getBieuDienNgaySinh() {
        return bieuDienNgaySinh;
    }


    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
