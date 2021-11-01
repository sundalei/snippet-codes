package demo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class InsertDataInfo {

    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static void main(String[] args) {

        try (InputStream in = InsertDataInfo.class.getClassLoader().getResourceAsStream("mssql.properties")) {

            Properties props = new Properties();
            props.load(in);

            var url = props.getProperty("url");
            var username = props.getProperty("username");
            var password = props.getProperty("password");

            Connection connection = null;
            PreparedStatement statement;

            try {
                Class.forName(JDBC_DRIVER);

                String insertSql = "INSERT INTO storage_file (name, last_modified, created_at)" +
                        " VALUES (?, getutcdate(), getutcdate())";

                connection = DriverManager.getConnection(url, username, password);
                statement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

                int col = 1;
                statement.setString(col++, "hello_insert");

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
            } catch (Exception ex) {
                ex.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
