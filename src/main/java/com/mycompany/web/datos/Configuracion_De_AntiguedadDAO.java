package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.web.modelo.Configuracion_De_Antiguedad;

public class Configuracion_De_AntiguedadDAO  {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Configuracion_De_Antiguedad getConfigAntiguedad(int año){
        Configuracion_De_Antiguedad configAntiguedad = new Configuracion_De_Antiguedad();
        String sql = "SELECT * FROM configuracionantiguedad WHERE minimaca<=? AND maximaca>=? AND activoca=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, año);
            ps.setInt(2, año);
            ps.setBoolean(3, true);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER LA CONFIGURACION DE ANTIGUEDAD
                configAntiguedad.setIdconfigantiguedad(rs.getInt("idconfigantiguedad"));
                configAntiguedad.setNombreca(rs.getString("nombreca"));
                configAntiguedad.setMinimaca(rs.getInt("minimaca"));
                configAntiguedad.setMaximaca(rs.getInt("maximaca"));
                configAntiguedad.setDescuentoca(rs.getInt("descuentoca"));
                configAntiguedad.setGananciaca(rs.getInt("gananciaca"));
                configAntiguedad.setRecargoca(rs.getDouble("recargoca"));
                configAntiguedad.setActivoca(true);
            }
        }
        catch ( SQLException e){
            System.out.println("No hay Configuracion Antiguedad");
            return null;
        }
        return configAntiguedad;
    }
}
