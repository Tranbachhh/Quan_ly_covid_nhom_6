package repository;
import entity.HoKhau;
import entity.HoKhauNhanKhau;
import entity.NhanKhau;
import javafx.collections.ObservableList;

import java.sql.Date;

public interface HoKhauRepository {
    public int tongHoKhau();

    //HoKhauController
    public ObservableList<HoKhau> loadDataHKController();

    //XemHoKhauController
    public ObservableList<HoKhauNhanKhau> loadDataXemHoKhauController(int idHoKhau);
    public String hoten_chu_ho(int id_ho_khau);

    //ThemHoKhauController
    public boolean check_chu_ho(int id_nk);
    public boolean check_nhan_khau_exist_nk(int id_nk);
    public ObservableList<NhanKhau> loadDataThemHKController();
    public int idHoKhau_moi_nhat();
    public void themNhanKhau(int id_hk, ObservableList<HoKhauNhanKhau> hknk_list);
    public void them_ho_khau_moi(int idChuHo, String tinh, String huyen, String xa, String diaChi, Date ngayTao);

    //SuaHoKhauController:
    public void change_id_chuho(int id_hokhau,int id_chuhoMoi);
    public void clear_hknk(int idHoKhau);
    public boolean check_nhan_khau_exist_nk_1(NhanKhau a,int idHoKhau);
    public boolean check_nhan_khau_exist_hk(NhanKhau a,int idHoKhau);
    public ObservableList<HoKhauNhanKhau> loadDataSuaHKController(int idHoKhau);
    public ObservableList<NhanKhau> loadNKSuaHKController();
    public int id_chu_ho_change(int idHoKhau);
    public String hoten_chu_ho_change(int idHoKhau);

    //Xoá Hộ Khẩu
    public void delete_hk(int id_ho_khau);

    //TachHoKhauController:
    public ObservableList<HoKhauNhanKhau> loadDataNKTachHKController(int idHoKhau);

}
