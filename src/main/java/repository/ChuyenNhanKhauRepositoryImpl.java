package repository;
import entity.ChuyenNhanKhau;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class ChuyenNhanKhauRepositoryImpl implements ChuyenNhanKhauRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;
    @Override
    public void insertChuyenNhanKhau(ChuyenNhanKhau f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_INSERT_CHUYENNHANKHAU);
            pstmt.setInt(1, f.getIdNhanKhau());
            pstmt.setString(2,f.getBieuDienNgayChuyenDi());
            pstmt.setString(3, f.getNoiChuyenDen());
            pstmt.setString(4, f.getGhiChu());

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
