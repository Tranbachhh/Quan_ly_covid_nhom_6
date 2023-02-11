package repository;
import entity.TamTru;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class TamTruRepositoryImpl implements TamTruRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;
    @Override
    public void insertTamTru(TamTru f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_INSERT_TAMTRU);
            pstmt.setInt(1, f.getIdNhanKhau());
            pstmt.setString(2,f.getNoiThuongTru());
            pstmt.setString(3, f.getNoiTamTru());
            pstmt.setString(4, f.getBieuDienTuNgay());
            pstmt.setString(5, f.getBieuDienDenNgay());
            pstmt.setString(6, f.getLyDo());

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