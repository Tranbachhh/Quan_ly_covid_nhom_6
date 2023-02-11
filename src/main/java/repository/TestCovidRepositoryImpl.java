package repository;
import entity.TestCovid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class TestCovidRepositoryImpl implements TestCovidRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public ObservableList<TestCovid> loadDataTestController(){
        ObservableList<TestCovid> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TEST_COVID_QUERY_LOADDATATESTCONTROLLER);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                int idNhanKhau = rs.getInt("idNhanKhau");
                String hoTen = rs.getString("hoTen");
                String cmnd = rs.getString("cmnd");
                String hinhThucTest = rs.getString("hinhThucTest");
                String ketQua = rs.getString("ketQua");
                Date thoiDiemTest =  rs.getDate("thoiDiemTest");

                f.add(new TestCovid(id,hoTen,idNhanKhau,cmnd,thoiDiemTest,hinhThucTest,ketQua));
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
    public int tongTest(){
        int tongTest=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TEST_COVID_QUERY_TONG);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongTest = rs.getInt(1);
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
        return tongTest;
    }

    @Override
    public void delete_test(int id){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TEST_COVID_QUERY_DELETE_TEST);
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
    public void themTest(TestCovid f){
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TEST_COVID_QUERY_THEMTEST);
            pstmt.setInt(1,f.getIdNhanKhau());
            pstmt.setDate(2,f.getThoiDiemTest());
            pstmt.setString(3,f.getHinhThucTest());
            pstmt.setString(4,f.getKetQua());

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
    public int tongDuongTinh(){
        int tongDuongTinh=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TEST_COVID_QUERY_TONG_DUONGTINH);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongDuongTinh = rs.getInt(1);
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
        return tongDuongTinh;
    }

    @Override
    public int tongPCR(){
        int tongPCR=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TEST_COVID_QUERY_TONG_PCR);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongPCR = rs.getInt(1);
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
        return tongPCR;
    }

    public int tongTestCovidTheoThang(int month) {
        int tongTestCovidTheoThang = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.TEST_COVID_QUERY_TONG_THEOTHANG);
            pstmt.setInt(1,month);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongTestCovidTheoThang = rs.getInt(1);
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
        return tongTestCovidTheoThang;
    }
}
