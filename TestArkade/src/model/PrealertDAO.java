package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrealertDAO {
    
	private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1434;databaseName=prealert_db;encrypt=false";
    private static final String USER = "user_prealert";
    private static final String PASSWORD = "a1234";

    public void insertPrealert(Prealert prealert) {
        String sql = "INSERT INTO Prealert (name, status, guide, creationDate, collected, origin) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, prealert.getName());
            pstmt.setString(2, prealert.getStatus());
            pstmt.setString(3, prealert.getGuide());
            if (prealert.getCreationDate() != null) {
                pstmt.setDate(4, new java.sql.Date(prealert.getCreationDate().getTime()));
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }
            pstmt.setString(5, prealert.getCollected());
            pstmt.setString(6, prealert.getOrigin());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Prealerta insertada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al insertar la prealerta: " + e.getMessage());
        }
    }

    public Prealert getPrealertById(int id) {
        String sql = "SELECT * FROM Prealert WHERE id = ?";
        Prealert prealert = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int prealertId = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                String guide = rs.getString("guide");
                Date creationDate = rs.getDate("creationDate");
                String collected = rs.getString("collected");
                String origin = rs.getString("origin");

                prealert = new Prealert(prealertId, name, status, guide, creationDate, collected, origin);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al recuperar la prealerta: " + e.getMessage());
        }

        return prealert;
    }

    public void updatePrealert(Prealert prealert) {
        String sql = "UPDATE Prealert SET name = ?, status = ?, guide = ?, creationDate = ?, collected = ?, origin = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, prealert.getName());
            pstmt.setString(2, prealert.getStatus());
            pstmt.setString(3, prealert.getGuide());
            pstmt.setDate(4, new java.sql.Date(prealert.getCreationDate().getTime()));
            pstmt.setString(5, prealert.getCollected());
            pstmt.setString(6, prealert.getOrigin());
            pstmt.setInt(7, prealert.getId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Prealerta actualizada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al actualizar la prealerta: " + e.getMessage());
        }
    }


    public void deletePrealert(int id) {
        String sql = "DELETE FROM Prealert WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Prealerta eliminada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar la prealerta: " + e.getMessage());
        }
    }

    public List<Prealert> getAllPrealerts() {
        List<Prealert> prealerts = new ArrayList<>();
        String sql = "SELECT * FROM Prealert";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                String guide = rs.getString("guide");
                Date creationDate = rs.getDate("creationDate");
                String collected = rs.getString("collected");
                String origin = rs.getString("origin");

                Prealert prealert = new Prealert(id, name, status, guide, creationDate, collected, origin);
                prealerts.add(prealert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al listar las prealertas: " + e.getMessage());
        }

        return prealerts;
    }

    
    public List<Prealert> searchPrealerts(String name, String guide, String status, Date startDate, Date endDate) {
        List<Prealert> prealerts = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Prealert WHERE 1=1");

        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ?");
        }
        if (guide != null && !guide.isEmpty()) {
            sql.append(" AND guide = ?");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = ?");
        }
        if (startDate != null && endDate != null) {
            sql.append(" AND creationDate BETWEEN ? AND ?");
        } else if (startDate != null) {
            sql.append(" AND creationDate >= ?");
        } else if (endDate != null) {
            sql.append(" AND creationDate <= ?");
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (name != null && !name.isEmpty()) {
                pstmt.setString(index++, "%" + name + "%");
            }
            if (guide != null && !guide.isEmpty()) {
                pstmt.setString(index++, guide);
            }
            if (status != null && !status.isEmpty()) {
                pstmt.setString(index++, status);
            }
            if (startDate != null && endDate != null) {
                pstmt.setDate(index++, new java.sql.Date(startDate.getTime()));
                pstmt.setDate(index++, new java.sql.Date(endDate.getTime()));
            } else if (startDate != null) {
                pstmt.setDate(index++, new java.sql.Date(startDate.getTime()));
            } else if (endDate != null) {
                pstmt.setDate(index++, new java.sql.Date(endDate.getTime()));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String prealertName = rs.getString("name");
                    String prealertStatus = rs.getString("status");
                    String prealertGuide = rs.getString("guide");
                    Date prealertCreationDate = rs.getDate("creationDate");
                    String prealertCollected = rs.getString("collected");
                    String prealertOrigin = rs.getString("origin");

                    Prealert prealert = new Prealert(id, prealertName, prealertStatus, prealertGuide, prealertCreationDate, prealertCollected, prealertOrigin);
                    prealerts.add(prealert);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al buscar las prealertas: " + e.getMessage());
        }

        return prealerts;
    }
}


