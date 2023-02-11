package entity;

import java.sql.Date;

public class HoKhau {
    private Integer idHoKhau;
    private Integer idChuHo;
    private String hotenChuho;
    private String tinhThanhPho;
    private String quanHuyen;
    private String phuongXa;
    private String diachi;
    private Date ngayTao;

    public Integer getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(Integer idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public Integer getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(Integer idChuHo) {
        this.idChuHo = idChuHo;
    }

    public String getTinhThanhPho() {
        return tinhThanhPho;
    }

    public void setTinhThanhPho(String tinhThanhPho) {
        this.tinhThanhPho = tinhThanhPho;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public HoKhau(){

    }

    public String getHotenChuho() {
        return hotenChuho;
    }

    public void setHotenChuho(String hotenChuho) {
        this.hotenChuho = hotenChuho;
    }

    public HoKhau(Integer idHoKhau, Integer idChuHo, String hotenChuho, String tinhThanhPho, String quanHuyen, String phuongXa, String diachi, Date ngayTao) {
        this.idHoKhau = idHoKhau;
        this.idChuHo = idChuHo;
        this.hotenChuho = hotenChuho;
        this.tinhThanhPho = tinhThanhPho;
        this.quanHuyen = quanHuyen;
        this.phuongXa = phuongXa;
        this.diachi = diachi;
        this.ngayTao = ngayTao;
    }

    public void copy_hk(HoKhau a){
        this.idHoKhau = a.getIdHoKhau();
        this.idChuHo = a.getIdChuHo();
        this.hotenChuho = a.getHotenChuho();
        this.tinhThanhPho = a.getTinhThanhPho();
        this.quanHuyen = a.getQuanHuyen();
        this.phuongXa = a.getPhuongXa();
        this.diachi = a.getDiachi();
        this.ngayTao = a.getNgayTao();
    }
}
