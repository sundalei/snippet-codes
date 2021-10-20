package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataInfo {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo?"
            + "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            Class.forName(JDBC_DRIVER);

            String insertSql = "INSERT INTO storage_file (name, last_modified, created_at) VALUES (?, utc_timestamp(), utc_timestamp())";
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

            int col = 1;
            statement.setString(col++, "hello");

            var rows = statement.executeUpdate();
            statement.executeUpdate();

            if (rows == 1) {
                ResultSet rs = statement.getGeneratedKeys();
                
                if (rs != null && rs.next()) {
                    System.out.println(rs.getInt(1));
                }
            }

            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
