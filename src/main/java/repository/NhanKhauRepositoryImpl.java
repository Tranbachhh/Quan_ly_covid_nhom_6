package repository;

import utility.DbUtil;
import utility.SQLCommand;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class NhanKhauRepositoryImpl implements NhanKhauRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public int tongNhanKhauThuongTru(){
        int tongNhanKhauThuongTru =0;
        try{
            conn=DbUtil.getInstance().getConnection();
            pstmt=conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_THUONG_TRU);
            rs=pstmt.executeQuery();
            while (rs.next()){
                tongNhanKhauThuongTru=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                DbUtil.releaseResource(rs,stmt,pstmt,cstmt,conn);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return tongNhanKhauThuongTru;
    }
    @Override
    public int tongNhanKhauDaChuyenDi() {
        int tongNhanKhauDaChuyenDi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauDaChuyenDi = rs.getInt(1);
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
        return tongNhanKhauDaChuyenDi;
    }

    @Override
    public int tongNhanKhauDaMat() {
        int tongNhanKhauDaMat = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_DA_MAT);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauDaMat = rs.getInt(1);
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
        return tongNhanKhauDaMat;
    }
    @Override
    public int tongNhanKhauTamTru() {
        int tongNhanKhauTamTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_TAM_TRU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauTamTru = rs.getInt(1);
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
        return tongNhanKhauTamTru;
    }

    @Override
    public int tongNhanKhauTamVang() {
        int tongNhanKhauTamVang = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_TAM_VANG);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauTamVang = rs.getInt(1);
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
        return tongNhanKhauTamVang;
    }
    @Override
    public int tongNam() {
        int tongNam = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNam = rs.getInt(1);
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
        return tongNam;
    }

    @Override
    public int tongNu() {
        int tongNu = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_NU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNu = rs.getInt(1);
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
        return tongNu;
    }
    @Override
    public int tongDuoiTuoiLaoDong() {
        int tongDuoiTuoiLaoDong = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_DUOI_TUOI_LAO_DONG);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongDuoiTuoiLaoDong = rs.getInt(1);
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
        return tongDuoiTuoiLaoDong;
    }
    @Override
    public int tongDoTuoiLaoDong() {
        int tongDoTuoiLaoDong = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongDoTuoiLaoDong = rs.getInt(1);
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
        return tongDoTuoiLaoDong;
    }

    @Override
    public int tongTrenTuoiLaoDong() {
        int tongTrenTuoiLaoDong = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TREN_TUOI_LAO_DONG);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongTrenTuoiLaoDong = rs.getInt(1);
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
        return tongTrenTuoiLaoDong;
    }
    @Override
    public ObservableList<NhanKhau> loadDataNhanKhau(){
        ObservableList<NhanKhau> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id_nk = rs.getInt("idNhanKhau");
                String bieuDienNgaySinh = rs.getString("ngaySinh").substring(8)+"-"+rs.getString("ngaySinh").substring(5,7)+"-"+rs.getString("ngaySinh").substring(0,4);
                String hoten = rs.getString("hoTen");
                String gioitinh = rs.getString("gioiTinh");
                String CMND = rs.getString("CMND");
                String trangThai = rs.getString("trangThai");

                f.add(new NhanKhau(id_nk,hoten,bieuDienNgaySinh,gioitinh,CMND,trangThai));

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
    @Override
    public ObservableList<NhanKhau> findNhanKhau(String queryTheoTruongTimKiem,String duLieuTraCuu){
        ObservableList<NhanKhau> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(queryTheoTruongTimKiem);
            pstmt.setString(1, "%"+duLieuTraCuu+"%" );
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id_nk = rs.getInt("idNhanKhau");
                String bieuDienNgaySinh = rs.getString("ngaySinh").substring(8)+"-"+rs.getString("ngaySinh").substring(5,7)+"-"+rs.getString("ngaySinh").substring(0,4);
                String hoten = rs.getString("hoTen");
                String gioitinh = rs.getString("gioiTinh");
                String CMND = rs.getString("CMND");
                String trangThai = rs.getString("trangThai");

                f.add(new NhanKhau(id_nk,hoten,bieuDienNgaySinh,gioitinh,CMND,trangThai));

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
    @Override
    public NhanKhau chiTietNhanKhauData(int id_NK){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_ID);
            pstmt.setInt(1, id_NK );
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id_nk = rs.getInt("idNhanKhau");
                String bieuDienNgaySinh = rs.getString("ngaySinh").substring(8)+"-"+rs.getString("ngaySinh").substring(5,7)+"-"+rs.getString("ngaySinh").substring(0,4);
                String hoten = rs.getString("hoTen");
                String gioiTinh = rs.getString("gioiTinh");
                String noiSinh = rs.getString("noiSinh");
                String CMND = rs.getString("CMND");
                String danToc = rs.getString("danToc");
                String tonGiao = rs.getString("tonGiao");
                String nguyenQuan = rs.getString("nguyenQuan");
                String quocTich = rs.getString("quocTich");
                String trangThai = rs.getString("trangThai");
                String noiThuongTruTruoc=rs.getString("noiThuongTruTruoc");
                String ngheNghiep=rs.getString("ngheNghiep");
                String noiLamViec=rs.getString("noiLamViec");
                String noiCap=rs.getString("noiCap");
                String ngayCap="";
                String chuyenDenNgay="";
                if(rs.getString("ngayCap")!=null&&rs.getString("ngayCap")!=""){
                    ngayCap = rs.getString("ngayCap").substring(8)+"-"+rs.getString("ngayCap").substring(5,7)+"-"+rs.getString("ngayCap").substring(0,4);
                }
                if(rs.getString("chuyenDenNgay")!=null&&rs.getString("chuyenDenNgay")!=""){
                    chuyenDenNgay = rs.getString("chuyenDenNgay").substring(8)+"-"+rs.getString("chuyenDenNgay").substring(5,7)+"-"+rs.getString("chuyenDenNgay").substring(0,4);
                }
                return new NhanKhau(id_nk,hoten,bieuDienNgaySinh,noiSinh,gioiTinh,
                        nguyenQuan,danToc,tonGiao,quocTich,ngheNghiep,noiLamViec,
                        CMND,ngayCap,noiCap,chuyenDenNgay,noiThuongTruTruoc,trangThai);

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
        return new NhanKhau();
    }
    @Override
    public void themNhanKhau(NhanKhau f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_INSERT_NHANKHAU);
            pstmt.setString(1, f.getHoTen());
            pstmt.setString(2, f.getBieuDienNgaySinh());
            pstmt.setString(3, f.getNoiSinh());
            pstmt.setString(4, f.getGioiTinh());
            pstmt.setString(5, f.getNguyenQuan());
            pstmt.setString(6, f.getDanToc());
            pstmt.setString(7, f.getTonGiao());
            pstmt.setString(8, f.getQuocTich());
            pstmt.setString(9, f.getNgheNghiep());
            pstmt.setString(10, f.getNoiLamViec());
            pstmt.setString(11, f.getCMND());
            pstmt.setString(12, f.getBieuDienNgayCap());
            pstmt.setString(13, f.getNoiCap());
            pstmt.setString(14, f.getBieuDienChuyenDenNgay());
            pstmt.setString(15, f.getNoiThuongTruTruoc());
            pstmt.setString(16, f.getTrangThai());

            pstmt.execute();
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
    @Override
    public void updateTrangThai(String trangThaiUpdate,int idUpdate){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_UPDATE_TRANGTHAI+idUpdate);
            pstmt.setString(1, trangThaiUpdate );
            pstmt.execute();
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
    @Override
    public void updateNhanKhau(int id_NK, NhanKhau f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_UPDATE+id_NK);
            pstmt.setString(1, f.getHoTen());
            pstmt.setString(2, f.getBieuDienNgaySinh());
            pstmt.setString(3, f.getNoiSinh());
            pstmt.setString(4, f.getGioiTinh());
            pstmt.setString(5, f.getNguyenQuan());
            pstmt.setString(6, f.getDanToc());
            pstmt.setString(7, f.getTonGiao());
            pstmt.setString(8, f.getQuocTich());
            pstmt.setString(9, f.getNgheNghiep());
            pstmt.setString(10, f.getNoiLamViec());
            pstmt.setString(11, f.getCMND());
            pstmt.setString(12, f.getBieuDienNgayCap());
            pstmt.setString(13, f.getNoiCap());
            pstmt.setString(14, f.getBieuDienChuyenDenNgay());
            pstmt.setString(15, f.getNoiThuongTruTruoc());

            pstmt.execute();
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

    @Override
    public void deleteNhanKhau(int id_NK){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_DELETE_NK);
            pstmt.setInt(1, id_NK );
            pstmt.execute();
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


}
