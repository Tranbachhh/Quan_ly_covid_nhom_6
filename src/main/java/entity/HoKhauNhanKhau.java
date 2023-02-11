package entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class HoKhauNhanKhau {
    private Integer idHoKhau;
    private Integer idNhanKhau;
    private String quanHeChuHo;
    private String hoTen;
    private Date ngaySinh;
    private String cmnd;

    public HoKhauNhanKhau(Integer idHoKhau, Integer idNhanKhau, String quanHeChuHo, String hoTen, Date ngaySinh, String cmnd) {
        this.idHoKhau = idHoKhau;
        this.idNhanKhau = idNhanKhau;
        this.quanHeChuHo = quanHeChuHo;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
    }

    public HoKhauNhanKhau(Integer idNhanKhau, String quanHeChuHo, String hoTen, Date ngaySinh) {
        this.idNhanKhau = idNhanKhau;
        this.quanHeChuHo = quanHeChuHo;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
    }

    public HoKhauNhanKhau(){}
}