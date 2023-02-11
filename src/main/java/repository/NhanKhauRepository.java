package repository;

import entity.NhanKhau;
import javafx.collections.ObservableList;

public interface NhanKhauRepository {
    public int tongNhanKhauThuongTru();

    public int tongNhanKhauDaChuyenDi();

    public int tongNhanKhauDaMat();

    public int tongNhanKhauTamTru();

    public int tongNhanKhauTamVang();

    public int tongNam();

    public int tongNu();

    public int tongDuoiTuoiLaoDong();

    public int tongDoTuoiLaoDong();

    public int tongTrenTuoiLaoDong();

    public ObservableList<NhanKhau> loadDataNhanKhau();
    public ObservableList<NhanKhau> findNhanKhau( String queryTheoTruongTimKiem,String duLieuTraCuu);
    public NhanKhau chiTietNhanKhauData(int id_NK);
    public void themNhanKhau(NhanKhau f);
    public void updateTrangThai(String trangThaiUpdate,int idUpdate);
    public void updateNhanKhau(int id_NK, NhanKhau f);
    public void deleteNhanKhau(int id_NK);
}