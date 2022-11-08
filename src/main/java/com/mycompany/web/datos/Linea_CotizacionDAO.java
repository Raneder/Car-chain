package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.web.modelo.Cobertura;
import com.mycompany.web.modelo.Cotizacion;
import com.mycompany.web.modelo.Linea_Cotizacion;

public class Linea_CotizacionDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Linea_Cotizacion linea_cotizacion) {
        int id = 0;
        String sql = "INSERT INTO lineacotizacion (monto, cotizacion_id, cobertura_id) "
                    +"VALUES (?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, linea_cotizacion.getMonto());
            ps.setInt(2, linea_cotizacion.getCotizacion().getIdcotizacion());
            ps.setInt(3, linea_cotizacion.getCobertura().getIdcobertura());
            int affectedRows = ps.executeUpdate();
            id = obtenerId(ps, affectedRows);
        }
        catch (SQLException e) {
            System.out.println("agregarLineaCotizacion es el problema");
        }
        return id;
    }

    public Linea_Cotizacion getLinea(int id){
        Linea_Cotizacion linea = new Linea_Cotizacion();
        String sql = "SELECT * FROM lineacotizacion WHERE idlineacotizacion=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                Cotizacion cotizacion = new Cotizacion();
                cotizacion.setIdcotizacion(rs.getInt("cotizacion_id"));

                Cobertura cobertura = new Cobertura();
                cobertura.setIdcobertura(rs.getInt("cobertura_id"));
                
                linea.setIdlineacotizacion(id);
                linea.setMonto(rs.getDouble("monto"));
                linea.setCotizacion(cotizacion);
                linea.setCobertura(cobertura);
            }
        }
        catch ( SQLException e){
            System.out.println("getLinea No funciona");
        }
        return linea;
    }

    private int obtenerId(PreparedStatement ps, int affectedRows) throws SQLException {
        int id = 0;
        if (affectedRows == 0) {
            throw new SQLException("La creación de Usuario ha fallado, no hay filas afectadas.");
        }
        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = Integer.parseInt(generatedKeys.getLong(1) + "");
            }
            else {
                throw new SQLException("La creación de Usuario ha fallado, no se obtuvo el ID.");
            }
        }
        return id;
    }
}
