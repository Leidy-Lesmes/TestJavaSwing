package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection cn;

    public static Connection getConnection() {
    	String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1434;databaseName=prealert_db;encrypt=false";
        String user = "user_prealert";
        String password = "a1234";

        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error al cargar el driver de SQL Server");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return cn;
    }

    public static void main(String[] args) {
        Connection connectionDB = DatabaseConnection.getConnection();
        if (connectionDB != null) {
            System.out.println("Conectado");
            System.out.println(connectionDB);
        } else {
            System.out.println("Desconectado...");
        }
    }
}
