package repository;

import java.sql.*;
import utility.DbUtil;
import utility.SQLCommand;
import entity.User;

public class UserRepositoryImpl implements UserRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public boolean Login(String UserName, String Password) {
        Boolean checkLogin;
        User users = new User();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.USER_QUERY_DANG_NHAP);
            pstmt.setString(1, UserName);
            pstmt.setString(2, Password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.setId(rs.getInt("id"));
                users.setUserName(rs.getString("Username"));
                users.setPassword(rs.getString("Password"));
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
        if (users.getId() != null) {
            checkLogin = true;
        } else {
            checkLogin = false;
        }
        return checkLogin;
    }
}
