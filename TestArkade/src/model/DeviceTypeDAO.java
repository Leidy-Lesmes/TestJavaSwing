package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeviceTypeDAO {

    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1434;databaseName=prealert_db;encrypt=false";
    private static final String USER = "user_prealert";
    private static final String PASSWORD = "a1234";

    public void insertDeviceType(DeviceType deviceType) {
        String sql = "INSERT INTO DeviceType (name, serialLength, macLength) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, deviceType.getName());
            pstmt.setInt(2, deviceType.getSerialLength());
            pstmt.setInt(3, deviceType.getMacLength());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Tipo de dispositivo insertado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al insertar el tipo de dispositivo: " + e.getMessage());
        }
    }
}
