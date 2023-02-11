package repository;

import entity.CachLy;
import entity.TestCovid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CachLyRepositoryImpl implements CachLyRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public ObservableList<CachLy> loadDataCachLyController(){
        ObservableList<CachLy> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_LOADDATACACHLYCONTROLLER);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int idNhanKhau = rs.getInt("idNhanKhau");
                String hoTen = rs.getString("hoTen");
                String cmnd = rs.getString("cmnd");
                String noiCachLy = rs.getString("noiCachLy");
                String mucDoCachLy = rs.getString("mucDoCachLy");
                Date ngayBatDau =  rs.getDate("ngayBatDau");
                String lyDoCachLy = rs.getString("lyDoCachLy");
                int soNgayCachLy = rs.getInt("soNgayCachLy");

                Calendar c = Calendar.getInstance();
                c.setTime(ngayBatDau);
                c.add(Calendar.DATE, soNgayCachLy);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date ngayKetThuc=Date.valueOf(formatter.format(c.getTime()));

                f.add(new CachLy(id,hoTen,idNhanKhau,cmnd,soNgayCachLy,ngayBatDau,ngayKetThuc,noiCachLy,mucDoCachLy,lyDoCachLy));
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
    public void delete_cl(int id){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_DELETE_CACHLY);
            pstmt.setInt(1,id);
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

    @Override
    public void themcachly(CachLy f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_THEMCACHLY);
            pstmt.setInt(1,f.getIdNhanKhau());
            pstmt.setDate(2,f.getNgayBatDau());
            pstmt.setInt(3,f.getSoNgayCachLy());
            pstmt.setString(4,f.getNoiCachLy());
            pstmt.setString(5,f.getMucDoCachLy());
            pstmt.setString(6,f.getLyDoCachLy());

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
    public int tongCachLy(){
        int tongCachLy=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_TONG);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongCachLy = rs.getInt(1);
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
        return tongCachLy;
    }

    @Override
    public int tongF0() {
        int tongF0 = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_TONG_FO);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongF0 = rs.getInt(1);
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
        return tongF0;
    }

    public int tongF1() {
        int tongF1 = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_TONG_F1);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongF1 = rs.getInt(1);
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
        return tongF1;
    }

    public int tongF2() {
        int tongF2 = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_TONG_F2);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongF2 = rs.getInt(1);
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
        return tongF2;
    }

    public int tongChuaRo() {
        int tongChuaRo = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_TONG_CHUARO);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongChuaRo = rs.getInt(1);
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
        return tongChuaRo;
    }

    public int tongTaiNha() {
        int tongTaiNha = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_TONG_TAINHA);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongTaiNha = rs.getInt(1);
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
        return tongTaiNha;
    }

    public int tongCachLyTheoThang(int month) {
        int tongCachLyTheoThang = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CACH_LY_QUERY_TONG_THEOTHANG);
            pstmt.setInt(1,month);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongCachLyTheoThang = rs.getInt(1);
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
        return tongCachLyTheoThang;
    }

}
