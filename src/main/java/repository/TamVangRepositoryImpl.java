package repository;

import entity.TamVang;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class TamVangRepositoryImpl implements TamVangRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public void insertTamVang(TamVang f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_INSERT_TAMVANG);
            pstmt.setInt(1, f.getIdNhanKhau());
            pstmt.setString(2, f.getNoiTamTru());
            pstmt.setString(3, f.getBieuDienTuNgay());
            pstmt.setString(4, f.getBieuDienDenNgay());
            pstmt.setString(5, f.getLyDo());

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