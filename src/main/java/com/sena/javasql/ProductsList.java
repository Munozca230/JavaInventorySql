package com.sena.javasql;

import connections.ConnectionDB;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ProductsList {
    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, String> codColumn;

    @FXML
    private TableColumn<Product, String> nombColumn;

    @FXML
    private TableColumn<Product, Integer> stockColumn;

    @FXML
    private TableColumn<Product, Double> valorColumn;

    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas de la tabla
        codColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nombColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));

        // Obtener los datos de la base de datos y mostrarlos en la tabla
        Connection con = ConnectionDB.getConexion();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cod_prod, nomb_pro, stock, valor_unitario FROM productos");
            while (rs.next()) {
                Product p = new Product(rs.getInt("cod_prod"), rs.getString("nomb_pro"), rs.getInt("stock"), rs.getDouble("valor_unitario"));
                table.getItems().add(p);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos de la base de datos: " + e.getMessage());
        }
    }
}
