package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RevisionDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public void agregar(int id) {
        String sql = "INSERT INTO revision (poliza_num) "
                    +"VALUES (?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("agregarRevision es el problema");
        }
    }
}
