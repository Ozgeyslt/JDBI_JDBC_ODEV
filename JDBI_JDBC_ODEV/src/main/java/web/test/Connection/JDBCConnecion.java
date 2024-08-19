package web.test.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnecion {

    String url = "jdbc:postgresql://localhost:5432/odev-database";
    private String user = "admin";
    private String password = "password";
    public Connection connect() throws SQLException {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}


