module com.sena.javasql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.sena.javasql to javafx.fxml;
    exports com.sena.javasql;
}