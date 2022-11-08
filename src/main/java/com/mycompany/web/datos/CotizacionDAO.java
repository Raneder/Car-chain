package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mycompany.web.modelo.Configuracion_De_Antiguedad;
import com.mycompany.web.modelo.Configuracion_De_Edad;
import com.mycompany.web.modelo.Configuracion_De_Localidad;
import com.mycompany.web.modelo.Cotizacion;
import com.mycompany.web.modelo.Vehiculo;

public class CotizacionDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Cotizacion cotizacion) {
        int id = 0;
        String sql = "INSERT INTO cotizacion (vehiculo_id, configlocalidad_id, configedad_id, "
                    + "configantiguedad_id, fechacreacioncotizacion, fechavencimientocotizacion) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cotizacion.getVehiculo().getIdvehiculo());

            if(cotizacion.getConfig_localidad().getIdconfiglocalidad() > 0){
                ps.setInt(2, cotizacion.getConfig_localidad().getIdconfiglocalidad());
            }
            else{
                ps.setNull(2, Types.INTEGER);
            }

            if(cotizacion.getConfig_edad().getIdconfigedad() > 0){
                ps.setInt(3, cotizacion.getConfig_edad().getIdconfigedad());
            }
            else{
                ps.setNull(3, Types.INTEGER);
            }
            
            if(cotizacion.getConfig_antiguedad().getIdconfigantiguedad() > 0){
                ps.setInt(4, cotizacion.getConfig_antiguedad().getIdconfigantiguedad());
            }
            else{
                ps.setNull(4, Types.INTEGER);
            }

            ps.setDate(5, new Date(cotizacion.getFecha_creacion().getTime()));
            ps.setDate(6, new Date(cotizacion.getFecha_vencimiento().getTime()));
            int affectedRows = ps.executeUpdate();
            id = obtenerId(ps, affectedRows);
        }
        catch (SQLException e) {
            System.out.println("agregarCotizacion es el problema");
        }
        return id;
    }

    public Cotizacion getCotizacion(int id){
        Cotizacion cotizacion = new Cotizacion();
        String sql = "SELECT * FROM cotizacion WHERE idcotizacion=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                //VEHICULO
                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdvehiculo(rs.getInt("vehiculo_id"));

                //CONFIGURACION LOCALIDAD
                Configuracion_De_Localidad config_local = new Configuracion_De_Localidad();
                config_local.setIdconfiglocalidad(rs.getInt("configlocalidad_id"));

                //CONFIGURACION EDAD
                Configuracion_De_Edad config_edad = new Configuracion_De_Edad();
                config_edad.setIdconfiguracionedad(rs.getInt("configedad_id"));

                //CONFIGURACION ANTIGUEDAD
                Configuracion_De_Antiguedad config_antiguedad = new Configuracion_De_Antiguedad();
                config_antiguedad.setIdconfigantiguedad(rs.getInt("configantiguedad_id"));

                cotizacion.setIdcotizacion(id);
                cotizacion.setVehiculo(vehiculo);
                cotizacion.setConfig_localidad(config_local);
                cotizacion.setConfig_edad(config_edad);
                cotizacion.setConfig_antiguedad(config_antiguedad);
                cotizacion.setFecha_creacion(rs.getDate("fechacreacioncotizacion"));
                cotizacion.setFecha_vencimiento(rs.getDate("fechavencimientocotizacion"));
            }
        }
        catch ( SQLException e){
            System.out.println("getCotizacion No funciona");
        }
        return cotizacion;
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
