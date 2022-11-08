package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycompany.web.modelo.Periodo_De_Pago;

public class Periodo_De_PagoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Periodo_De_Pago> listar(){
        ArrayList<Periodo_De_Pago> periodos_pago = new ArrayList<Periodo_De_Pago>();
        String sql = "SELECT * FROM periodopago";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER EL PERIODO DE PAGO
                Periodo_De_Pago periodo_pago = new Periodo_De_Pago();
                periodo_pago.setIdperiodopago(rs.getInt("idperiodopago"));
                periodo_pago.setNombreperiodopago(rs.getString("nombreperiodopago"));
                periodo_pago.setCantidadmesespago(rs.getInt("cantidadmesespago"));
                periodo_pago.setDescuentoperiodopago(rs.getInt("descuentoperiodopago"));
            
                periodos_pago.add(periodo_pago);
            }
        }
        catch ( SQLException e){
            System.out.println("getPeriodoPago No funciona");
        }
        return periodos_pago;
    }

    public Periodo_De_Pago getPeriodoPago(int id){
        Periodo_De_Pago periodo_pago = new Periodo_De_Pago();
        String sql = "SELECT * FROM periodopago WHERE idperiodopago=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER LA VERSION
                periodo_pago.setIdperiodopago(id);
                periodo_pago.setNombreperiodopago(rs.getString("nombreperiodopago"));
                periodo_pago.setCantidadmesespago(rs.getInt("cantidadmesespago"));
                periodo_pago.setDescuentoperiodopago(rs.getInt("descuentoperiodopago"));
            }
        }
        catch ( SQLException e){
            System.out.println("getPeriodoPago No funciona");
        }
        return periodo_pago;
    }
    
}
