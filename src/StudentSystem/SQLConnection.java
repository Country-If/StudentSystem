package StudentSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static final String database = "student_system";
    private static final String url = "jdbc:mysql://localhost:3306/"+database+"?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=true";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection Connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("The driver was not loaded successfully, please check whether the driver was imported!");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("fail to connect database");
            e.printStackTrace();
        }
        return connection;
    }

    public static void Close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                connection = null;
                e.printStackTrace();
            }
        }
    }
}
