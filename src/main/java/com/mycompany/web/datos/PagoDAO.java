package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.mycompany.web.modelo.Pago;

public class PagoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public void agregar(Pago pago) {
        String sql = "INSERT INTO pago (poliza_num, totalpago, fechapago, horapago) "
                    +"VALUES (?, ?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pago.getPoliza_num());
            ps.setDouble(2, pago.getTotalpago());
            ps.setDate(3, new Date(pago.getFechapago().getTime()));
            ps.setTime(4, Time.valueOf(pago.getHorapago()));
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("agregarPago es el problema");
        }
    }
    
}
