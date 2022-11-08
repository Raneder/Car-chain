package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    Connection connection;

    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/polizasdb?user=root&password=");
        } 
        catch (ClassNotFoundException ex) {

        }
        catch (SQLException e) {
        }
        return connection;
    }

    public void desconectar() {
        try {
            connection.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}