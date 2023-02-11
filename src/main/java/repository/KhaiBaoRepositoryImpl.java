package repository;

import entity.KhaiBao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class KhaiBaoRepositoryImpl implements KhaiBaoRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public ObservableList<KhaiBao> loadDataKhaiBaoController(){
        ObservableList<KhaiBao> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_LOADDATAKHAIBAOCONTROLLER);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int idNhanKhau = rs.getInt("idNhanKhau");
                String hoTen = rs.getString("hoTen");
                String cmnd = rs.getString("cmnd");
                String trieuChung = rs.getString("trieuChung");
                String tinhTrangSucKhoe = rs.getString("tinhTrangSucKhoe");
                Date ngayKhaiBao =  rs.getDate("ngayKhaiBao");

                f.add(new KhaiBao(id,hoTen,idNhanKhau,cmnd,ngayKhaiBao,trieuChung,tinhTrangSucKhoe));
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
    public void themKhaiBao(KhaiBao f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_THEMKHAIBAO);
            pstmt.setInt(1,f.getIdNhanKhau());
            pstmt.setDate(2,f.getNgayKhaiBao());
            pstmt.setString(3,f.getTrieuChung());
            pstmt.setString(4,f.getTinhTrangSucKhoe());

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
    public void delete_kb(int id){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_DELETE_KB);
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
    public int tongKhaiBao(){
        int tongKhaiBao=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_TONG);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongKhaiBao = rs.getInt(1);
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
        return tongKhaiBao;
    }

    @Override
    public int tongTrieuChungCo(){
        int tongTrieuChungCo=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_TONG_TRIEUCHUNGCO);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongTrieuChungCo = rs.getInt(1);
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
        return tongTrieuChungCo;
    }

    @Override
    public int tongBinhThuong(){
        int tongBinhThuong=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_TONG_BINHTHUONG);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongBinhThuong = rs.getInt(1);
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
        return tongBinhThuong;
    }

    @Override
    public int tongHo(){
        int tongHo=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_TONG_HO);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongHo = rs.getInt(1);
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
        return tongHo;
    }

    @Override
    public int tongSot(){
        int tongSot=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_TONG_SOT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongSot = rs.getInt(1);
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
        return tongSot;
    }

    public int tongKhaiBaoTheoThang(int month) {
        int tongKhaiBaoTheoThang = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.KHAI_BAO_QUERY_TONG_THEOTHANG);
            pstmt.setInt(1,month);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongKhaiBaoTheoThang = rs.getInt(1);
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
        return tongKhaiBaoTheoThang;
    }
}