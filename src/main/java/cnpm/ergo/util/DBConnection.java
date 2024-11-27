package cnpm.ergo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // Thông tin kết nối
        String hostName = "localhost";
        String dbName = "cnpm";
        String userName = "root";
        String password = "123456";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                 String userName, String password)
            throws SQLException, ClassNotFoundException {

        // Sử dụng driver mới
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Định dạng URL kết nối MySQL
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName
                + "?useSSL=false&serverTimezone=UTC";

        // Tạo kết nối
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                System.out.println("Kết nối thành công: " + connection);
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
