package com.sena.javasql;
import java.sql.*;

import connections.ConnectionDB;

public class Main {
    public static void main(String[] args) {
        ConnectionDB.getConexion();

        try {
            // Obtener conexión a la base de datos
            Connection conn = ConnectionDB.getConexion();

            // Crear objeto Statement para ejecutar la consulta
            Statement stmt = conn.createStatement();

            // Ejecutar la consulta
            String sql = "SELECT * FROM productos";
            ResultSet rs = stmt.executeQuery(sql);

            // Procesar los resultados de la consulta
            while (rs.next()) {
                // Obtener los valores de cada columna
                int id = rs.getInt("cod_pro");
                String nombre = rs.getString("nomb_pro");
                int stock = rs.getInt("stock");
                String valor = rs.getString("valor_unitario");

                // Hacer algo con los valores obtenidos (por ejemplo, imprimirlos)
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", stock: " + stock + ", valor: "+valor);
            }

            // Cerrar objetos ResultSet, Statement y Connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
