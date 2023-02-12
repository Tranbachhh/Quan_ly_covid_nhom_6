package utility;

import java.sql.*;

public class DbUtil {
    private static DbUtil instance;
    private Connection connection;

    private DbUtil() {
        try {
            String driver="com.mysql.cj.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/demo";
            String userName="root";
            String password="bach20062002";
            Class.forName(driver);

            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DbUtil getInstance() throws SQLException {
        if(instance == null ||instance.getConnection().isClosed()) {
            instance = new DbUtil();
        }
        return instance;
    }

    public static void releaseResource(ResultSet rs, Statement stmt, PreparedStatement pstmt, CallableStatement cstmt, Connection conn) throws SQLException {
        if(rs != null) {
            rs.close();
        }
        if(stmt != null) {
            stmt.close();
        }
        if(pstmt != null) {
            pstmt.close();
        }
        if(cstmt != null) {
            cstmt.close();
        }
        if(conn != null) {
            conn.close();
        }
    }
}
