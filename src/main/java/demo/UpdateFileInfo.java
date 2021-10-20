package demo;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class UpdateFileInfo {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo?"
            + "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            int col = 1;

//            String sql = "INSERT INTO storage_file (name, last_modified, created_at) VALUES (?, utc_timestamp(), utc_timestamp())";
//            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setString(col++, "hello");
//            statement.executeUpdate();

            // query records.
            String querySql = "select id, name, last_modified, created_at from storage_file where name = ?";
            statement = connection.prepareStatement(querySql);

            col = 1;
            statement.setString(col++, "hello");

            resultSet = statement.executeQuery();
            FileInfo fileInfo = null;

            if (resultSet.next()) {
                col = 1;

                var id = resultSet.getInt(col++);
                var name = resultSet.getString(col++);
                var lastModified = resultSet.getTimestamp(col++, Calendar.getInstance(TimeZone.getTimeZone("UTC")));
                var createdAt = resultSet.getTimestamp(col++, Calendar.getInstance(TimeZone.getTimeZone("UTC")));

                fileInfo = new FileInfo();
                fileInfo.setId(id);
                fileInfo.setName(name);
                fileInfo.setLastModified(ZonedDateTime.ofInstant(lastModified.toInstant(), ZoneId.of("UTC")));
                fileInfo.setCreatedAt(ZonedDateTime.ofInstant(createdAt.toInstant(), ZoneId.of("UTC")));

                System.out.println(fileInfo);
            }

            if (fileInfo != null) {

                String insertSql = "INSERT INTO storage_file (name, last_modified, created_at) VALUES (?, utc_timestamp(), ?)";
                statement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

                col = 1;
                statement.setString(col++, fileInfo.getName());
                statement.setObject(col++, fileInfo.getCreatedAt());

                var rows = statement.executeUpdate();
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
