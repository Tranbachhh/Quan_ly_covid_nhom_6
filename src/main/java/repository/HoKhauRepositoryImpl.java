package repository;

import entity.HoKhau;
import entity.HoKhauNhanKhau;
import entity.NhanKhau;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;
import javafx.collections.FXCollections;

import java.sql.*;

public class HoKhauRepositoryImpl implements HoKhauRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public int tongHoKhau(){
        int tongHoKhau=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_TONG);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongHoKhau = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tongHoKhau;
    }

    //HoKhauController
    @Override
    public ObservableList<HoKhau> loadDataHKController(){
        ObservableList<HoKhau> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATAHOKHAUCONTROLLER);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id_hk = rs.getInt("idHoKhau");
                int id_chu_ho_hk = rs.getInt("idChuHo");
                String address_hk = rs.getString("diaChi");
                String thanhpho_hk = rs.getString("tinhThanhPho");
                String quanhuyen_hk = rs.getString("quanHuyen");
                String phuongxa_hk = rs.getString("phuongXa");
                Date ngaytao_hk =  rs.getDate("ngayTao");
                String hoten = rs.getString("hoTen");

                f.add(new HoKhau(id_hk,id_chu_ho_hk,hoten,thanhpho_hk,quanhuyen_hk,phuongxa_hk,address_hk,ngaytao_hk));
            }
            return f;
        } catch (SQLException e) {
            e.printStackTrace();
            return f;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //XemHoKhauController
    @Override
    public ObservableList<HoKhauNhanKhau> loadDataXemHoKhauController(int id_ho_khau){
        ObservableList<HoKhauNhanKhau> t = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATAXEMHOKHAUCONTROLLER);
            pstmt.setInt(1,id_ho_khau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idHoKhau = rs.getInt("idHoKhau");
                int idNhanKhau = rs.getInt("idNhanKhau");
                String quanHeChuHo = rs.getString("quanHeChuHo");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String cmnd = rs.getString("cmnd");

                t.add(new HoKhauNhanKhau(idHoKhau,idNhanKhau,quanHeChuHo,hoTen,ngaySinh,cmnd));
            }
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            return t;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String hoten_chu_ho(int idChuHo){
        String hoTen="";
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_HOTEN_CHU_HO);
            pstmt.setInt(1,idChuHo);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                hoTen = rs.getString("hoTen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return hoTen;
    }

    //ThemHoKhauController
    @Override
    public boolean check_chu_ho(int id_nk){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LAY_THONG_TIN);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idChuHo = rs.getInt("idChuHo");
                if(idChuHo == id_nk) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean check_nhan_khau_exist_nk(int id_nk){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int x = rs.getInt("idNhanKhau");
                if(id_nk == x) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public ObservableList<NhanKhau> loadDataThemHKController(){
        ObservableList<NhanKhau> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idNhanKhau = rs.getInt("idNhanKhau");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String noiSinh = rs.getString("noiSinh");
                String gioiTinh = rs.getString("gioiTinh");
                String CMND = rs.getString("cmnd");
                String danToc = rs.getString("danToc");
                String tonGiao = rs.getString("tonGiao");
                String quocTich = rs.getString("quocTich");
                String trangThai = rs.getString("trangThai");

                f.add(new NhanKhau(idNhanKhau,hoTen,ngaySinh,noiSinh,gioiTinh,CMND,danToc,tonGiao,quocTich,trangThai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    public int idHoKhau_moi_nhat(){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_IDHOKHAU_MOI_NHAT);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int a = rs.getInt("idHoKhau");
                return a;
            }
        } catch (SQLException ee){
            ee.printStackTrace();
            return 0;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void themNhanKhau(int id_hk, ObservableList<HoKhauNhanKhau> hknk_list){
        for(HoKhauNhanKhau x : hknk_list){
            try {
                conn = DbUtil.getInstance().getConnection();
                pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_THEMNHANKHAU_TO_nkhk);
                pstmt.setInt(1,id_hk);
                pstmt.setInt(2,x.getIdNhanKhau());
                pstmt.setString(3,x.getQuanHeChuHo());
                pstmt.executeUpdate();
            } catch (SQLException ee){
                ee.printStackTrace();
            }finally {
                try {
                    DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void them_ho_khau_moi(int idChuHo, String tinh, String huyen, String xa, String diaChi, Date ngayTao){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_THEMHOKHAU);
            pstmt.setInt(1,idChuHo);
            pstmt.setString(2,tinh);
            pstmt.setString(3,huyen);
            pstmt.setString(4,xa);
            pstmt.setString(5,diaChi);
            pstmt.setDate(6,ngayTao);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Sua ho khau
    @Override
    public void change_id_chuho(int id_hokhau,int id_chuhoMoi){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHANGE_ID_CHUHO);
            pstmt.setInt(1,id_chuhoMoi);
            pstmt.setInt(2,id_hokhau);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clear_hknk(int idHoKhau){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CLEAR_HKNK);
            pstmt.setInt(1,idHoKhau);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean check_nhan_khau_exist_nk_1(NhanKhau a,int idHoKhau){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK_1);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int x = rs.getInt("idNhanKhau");
                if(a.getId() == x) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean check_nhan_khau_exist_hk(NhanKhau a,int idHoKhau){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_HK);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int x = rs.getInt("idChuHo");
                if(a.getId() == x) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ObservableList<HoKhauNhanKhau> loadDataSuaHKController(int idHoKhau){
        ObservableList<HoKhauNhanKhau> fx = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATASUAHKCONTROLLER);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idhokhau = rs.getInt("idHoKhau");
                int idNhanKhau= rs.getInt("idNhanKhau");
                String quanHeChuHo = rs.getString("quanHeChuHo");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String cmnd = rs.getString("cmnd");

                HoKhauNhanKhau h = new HoKhauNhanKhau(idhokhau,idNhanKhau,quanHeChuHo,hoTen,ngaySinh,cmnd);
                fx.add(h);
            }
            return fx;
        } catch (SQLException e) {
            e.printStackTrace();
            return fx;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<NhanKhau> loadNKSuaHKController(){
        ObservableList<NhanKhau> fx = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idNhanKhau = rs.getInt("idNhanKhau");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String noiSinh = rs.getString("noiSinh");
                String gioiTinh = rs.getString("gioiTinh");
                String CMND = rs.getString("cmnd");
                String danToc = rs.getString("danToc");
                String tonGiao = rs.getString("tonGiao");
                String quocTich = rs.getString("quocTich");
                String trangThai = rs.getString("trangThai");

                fx.add(new NhanKhau(idNhanKhau,hoTen,ngaySinh,noiSinh,gioiTinh,CMND,danToc,tonGiao,quocTich,trangThai));
            }
            return fx;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return fx;
    }

    public int id_chu_ho_change(int idHoKhau){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int b = rs.getInt("idNhanKhau");
                return b;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String hoten_chu_ho_change(int idHoKhau){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String a = rs.getString("hoTen");
                return a;
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Xoá hộ khẩu
    public void delete_hk(int id_ho_khau){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_DELETE_HK);
            pstmt.setInt(1,id_ho_khau);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Tách hộ khẩu
    public ObservableList<HoKhauNhanKhau> loadDataNKTachHKController(int idHoKhau){
        ObservableList<HoKhauNhanKhau> fx = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATASUAHKCONTROLLER);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int m = rs.getInt("idNhanKhau");
                String n = rs.getString("quanHeChuHo");
                String p = rs.getString("hoTen");
                Date q = rs.getDate("ngaySinh");

                HoKhauNhanKhau x = new HoKhauNhanKhau(m,n,p,q);
                fx.add(x);
            }
            return fx;
        } catch (SQLException e) {
            e.printStackTrace();
            return fx;
        }finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
