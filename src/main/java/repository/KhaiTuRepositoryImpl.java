package repository;
import utility.DbUtil;
import utility.SQLCommand;
import entity.KhaiTu;

import java.sql.*;

public class KhaiTuRepositoryImpl implements KhaiTuRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public void insertKhaiTu(KhaiTu f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_INSERT_KHAITU);
            pstmt.setInt(1, f.getIdNguoiMat());
            pstmt.setInt(2, f.getIdNguoiKhai());
            pstmt.setString(3, f.getBieuDienNgayKhai());
            pstmt.setString(4, f.getBieuDienNgayMat());
            pstmt.setString(5, f.getLiDoMat());

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