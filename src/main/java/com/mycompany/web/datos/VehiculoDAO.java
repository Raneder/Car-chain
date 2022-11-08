package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.web.modelo.Cliente;
import com.mycompany.web.modelo.Vehiculo;
import com.mycompany.web.modelo.Version;

public class VehiculoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int VehiculoCotizado(String matricula) {
        int id = 0;
        String sql = "SELECT * FROM vehiculo WHERE matricula=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            while (rs.next()) {
                id=rs.getInt("idvehiculo");
            }
        }
        catch (SQLException e) {
            System.out.println("vehiculoExiste es el problema");
        }
        return id;
    }

    public boolean VehiculoEnPoliza(String matricula) {
        String sql = "SELECT p.numeropoliza FROM vehiculo v, cotizacion c, lineacotizacion l, poliza p " +
                    "WHERE v.idvehiculo=c.vehiculo_id AND c.idcotizacion = l.cotizacion_id "+
                    "AND l.idlineacotizacion=p.lineacotizacion_id AND v.matricula=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println("VehiculoEnPoliza es el problema");
        }
        return false;
    }

    public boolean VehiculoCotizablePorMi(String matricula, int id) {
        String sql = "SELECT * FROM vehiculo WHERE matricula=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id_dueño = rs.getInt("cliente_id");
                if(id_dueño == id){     
                    return true;
                }
                else{     
                    return false;   
                }
            }
        }
        catch (SQLException e) {
            System.out.println("vehiculoExiste es el problema");
        }
        return true;
    }

    public int agregar(Vehiculo vehiculo) {
        int id = 0;
        String sql = "INSERT INTO vehiculo (cliente_id, version_id, matricula, "
                    +"añofabricación, numeromotor, chasis, gnc) "
                    +"VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, vehiculo.getCliente().getIdCliente());
            ps.setInt(2, vehiculo.getVersion().getIdversion());
            ps.setString(3, vehiculo.getMatricula());
            ps.setInt(4, vehiculo.getAñofabricación());
            ps.setString(5, vehiculo.getNumeromotor());
            ps.setString(6, vehiculo.getChasis());
            ps.setBoolean(7, vehiculo.isGnc());
            int affectedRows = ps.executeUpdate();
            id = obtenerId(ps, affectedRows);
        }
        catch (SQLException e) {
            System.out.println("agregarVehiculo es el problema");
        }
        return id;
    }

    public void actualizar(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculo SET version_id=?, añofabricación=?, numeromotor=?, chasis=?, gnc=? "+
                    "WHERE matricula=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vehiculo.getVersion().getIdversion());
            ps.setInt(2, vehiculo.getAñofabricación());
            ps.setString(3, vehiculo.getNumeromotor());
            ps.setString(4, vehiculo.getChasis());
            ps.setBoolean(5, vehiculo.isGnc());
            ps.setString(6, vehiculo.getMatricula());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("actualizarVehiculo es el problema");
        }
    }

    public Vehiculo getVehiculo(int id){
        Vehiculo vehiculo = new Vehiculo();
        String sql = "SELECT * FROM vehiculo WHERE idvehiculo=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                //OBTENER EL CLIENTE
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("cliente_id"));

                //OBTENGO LA VERSION
                Version version = new Version();
                version.setIdversion(rs.getInt("version_id"));

                //OBTENER LA VERSION
                vehiculo.setIdvehiculo(id);
                vehiculo.setCliente(cliente);
                vehiculo.setVersion(version);
                vehiculo.setMatricula(rs.getString("matricula"));
                vehiculo.setAñofabricación(rs.getInt("añofabricación"));
                vehiculo.setNumeromotor(rs.getString("numeromotor"));
                vehiculo.setChasis(rs.getString("chasis"));
                vehiculo.setGnc(rs.getBoolean("gnc"));
            }
        }
        catch ( SQLException e){
            System.out.println("getVehiculo No funciona");
        }
        return vehiculo;
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
