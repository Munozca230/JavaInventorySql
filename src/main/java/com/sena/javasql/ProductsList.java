package com.sena.javasql;

import connections.ConnectionDB;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ProductsList implements Initializable {

    @FXML
    private Button btnCloseDB;

    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<Product, Integer> codColumn;

    @FXML
    private TableColumn<Product, String> nombColumn;

    @FXML
    private TableColumn<Product, Integer> stockColumn;

    @FXML
    private TableColumn<Product, Double> valorColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas de la tabla
        codColumn.setCellFactory(getTableCellFactoryForInteger());
        codColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCodProd()).asObject());

        nombColumn.setCellFactory(getTableCellFactoryForString());
        nombColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombPro()));

        stockColumn.setCellFactory(getTableCellFactoryForInteger());
        stockColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());

        valorColumn.setCellFactory(getTableCellFactoryForDouble());
        valorColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorUnitario()).asObject());

        // Obtener los datos de la base de datos y mostrarlos en la tabla
        Connection con = ConnectionDB.getConexion();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cod_pro, nomb_pro, stock, valor_unitario FROM productos");
            while (rs.next()) {
                Product p = new Product(rs.getInt("cod_pro"), rs.getString("nomb_pro"), rs.getInt("stock"), rs.getDouble("valor_unitario"));
                table.getItems().add(p);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos de la base de datos: " + e.getMessage());
        }
    }

    private Callback<TableColumn<Product, Integer>, TableCell<Product, Integer>> getTableCellFactoryForInteger() {
        return column -> new TableCell<Product, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.toString());
            }
        };
    }

    //Para setear el tipo de dato según la columna
    private Callback<TableColumn<Product, String>, TableCell<Product, String>> getTableCellFactoryForString() {
        return column -> new TableCell<Product, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
            }
        };
    }

    private Callback<TableColumn<Product, Double>, TableCell<Product, Double>> getTableCellFactoryForDouble() {
        return column -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : String.format("%.2f", item));
            }
        };
    }

    @FXML
    public void closeDbChangeView(ActionEvent event) throws IOException {
        try {
            // Cerrar ventana actual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Abrir ventana Connect DB
            FXMLLoader loader = new FXMLLoader(getClass().getResource("connect-db.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

