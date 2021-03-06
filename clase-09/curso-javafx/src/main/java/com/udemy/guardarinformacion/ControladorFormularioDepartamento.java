package com.udemy.guardarinformacion;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ControladorFormularioDepartamento implements Initializable {

    @FXML
    TextField nombre;

    @FXML
    TextArea descripcion;

    public void guardar() {
        try (Connection conexionDB = DriverManager.getConnection("jdbc:h2:./target/demo", "sa", "")) {
            Statement statement = conexionDB.createStatement();
            String sql = "INSERT INTO departamento(nombre, descripcion) "
                    + "VALUES ('" + nombre.getText() + "', '" + descripcion.getText() + "')";
            statement.executeUpdate(sql);
            System.out.println("Informacion guardada");

            sql ="SELECT COUNT(*) AS cantidad FROM departamento";
            ResultSet resultadoConsulta = statement.executeQuery(sql);
            if(resultadoConsulta.next()) {
                System.out.println("Departamentos guardados: "+resultadoConsulta.getInt("cantidad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        nombre.clear();
        descripcion.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try (Connection conexionDB = DriverManager.getConnection("jdbc:h2:./target/demo", "sa", "")) {
            Statement statement = conexionDB.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS departamento" +
                    "(id INTEGER auto_increment, " +
                    " nombre VARCHAR(255), " +
                    " descripcion VARCHAR(255) )";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
