package com.udemy.persistencia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDao {


    public UsuarioDao() {
        crearTablaSiNoExiste();
        insertarUsuarioSiNoHay();
    }

    private void crearTablaSiNoExiste() {
        try (Connection conexionDB = DriverManager.getConnection(DepartamentoDao.URL_CONEXION, DepartamentoDao.USUARIO_BDD, DepartamentoDao.PASSWORD_BDD)) {
            Statement statement = conexionDB.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS usuario" +
                    "(id INTEGER auto_increment, " +
                    " nombre_usuario VARCHAR(255), " +
                    " password VARCHAR(255))";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarUsuarioSiNoHay() {
        boolean existeUsuario = existeUsuario("admin", "admin");
        if (!existeUsuario) {
            try (Connection conexionDB = DriverManager.getConnection(DepartamentoDao.URL_CONEXION, DepartamentoDao.USUARIO_BDD, DepartamentoDao.PASSWORD_BDD)) {
                Statement statement = conexionDB.createStatement();
                String sql = "INSERT INTO usuario (nombre_usuario, password) VALUES ('admin', 'admin')";
                statement.executeUpdate(sql);
            } catch (Exception e) {
                throw new RuntimeException("Ocurrio un error al guardar la informacion: " + e.getMessage());
            }
        }
    }

    public boolean existeUsuario(String username, String password) {
        try (Connection conexionDB = DriverManager.getConnection(DepartamentoDao.URL_CONEXION, DepartamentoDao.USUARIO_BDD, DepartamentoDao.PASSWORD_BDD)) {
            Statement statement = conexionDB.createStatement();
            String sql = "SELECT * FROM usuario WHERE nombre_usuario='" + username
                    + "' AND password='" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al consultar la informacion: " + e.getMessage());
        }
    }

}
