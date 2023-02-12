package repository;

import entity.CachLy;
import entity.TiemCovid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class TiemCovidRepositoryImpl implements TiemCovidRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public ObservableList<TiemCovid> loadDataTiemCovidController(){
        ObservableList<TiemCovid> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TIEM_COVID_QUERY_LOADDATATIEMCOVIDCONTROLLER);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int soMui = rs.getInt("soMui");
                int idNhanKhau = rs.getInt("idNhanKhau");
                String hoTen = rs.getString("hoTen");
                String noiTiem = rs.getString("noiTiem");

                f.add(new TiemCovid(id,idNhanKhau,hoTen,noiTiem,soMui));
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
            pstmt = conn.prepareStatement(SQLCommand.TIEM_COVID_QUERY_DELETE_TIEMCOVID);
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
    public void themTiemCoivd(TiemCovid f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TIEM_COVID_QUERY_THEMTIEMCOVIDLY);
            pstmt.setInt(1,f.getIdNhanKhau());
            pstmt.setString(2,f.getNoiTiem());
            pstmt.setInt(3,f.getSoMui());

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
