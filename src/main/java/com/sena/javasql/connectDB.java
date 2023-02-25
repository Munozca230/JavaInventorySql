package com.sena.javasql;

import connections.ConnectionDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class connectDB {

    ConnectionDB connectionDB = new ConnectionDB();

    @FXML
    private Button btnConnectDB;

    @FXML
    private Label statusLabel;

    @FXML
    protected void handleBtnConnectDB() {
        Connection conn = null;
        boolean connected = false;

        try {
            conn = connectionDB.getConexion();
            statusLabel.setText("Conexión exitosa.");
            connected = true;
        } catch (Exception e) {
            statusLabel.setText("Error.");
            e.printStackTrace();
        }

        if (connected) {
            try {
                // Cargar la vista FXML de la otra vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("products-list.fxml"));
                AnchorPane root = loader.load();

                // Crear una nueva escena con la otra vista cargada
                Scene scene = new Scene(root);

                // Obtener el stage (ventana) actual y establecer la nueva escena
                Stage stage = (Stage) btnConnectDB.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}