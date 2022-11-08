package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;

import com.mycompany.web.modelo.Cliente;
import com.mycompany.web.modelo.Cobertura;
import com.mycompany.web.modelo.Cotizacion;
import com.mycompany.web.modelo.Documentacion;
import com.mycompany.web.modelo.Estado_Poliza;
import com.mycompany.web.modelo.Linea_Cotizacion;
import com.mycompany.web.modelo.Periodo_De_Pago;
import com.mycompany.web.modelo.Poliza;
import com.mycompany.web.modelo.Tipo_De_Contratacion;
import com.mycompany.web.modelo.Usuario;
import com.mycompany.web.modelo.Vehiculo;

public class PolizaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public void agregar(Poliza poliza) {
        String sql = "INSERT INTO poliza (documentacion_id, lineacotizacion_id, preciopolizaactual, "
                    +"montoasegurado, auto_renov_poliza, estadopoliza) "
                    +"VALUES (?,?,?,?,?,?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, poliza.getDocumentacion().getIddocumentacion());
            ps.setInt(2, poliza.getLinea_cotizacion().getIdlineacotizacion());
            ps.setDouble(3, poliza.getPreciopolizaactual());
            ps.setDouble(4, poliza.getMontoasegurado());
            ps.setBoolean(5, poliza.isAuto_renov_poliza());
            ps.setString(6, poliza.getEstadopoliza().name());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("agregarPoliza es el problema");
        }
    }

    public ArrayList<Poliza> obtenerPolizasCliente(int id) {
        ArrayList<Poliza> polizas = new ArrayList<Poliza>();
        String sql = "SELECT * FROM poliza p, lineacotizacion l, cotizacion c, vehiculo v, Cobertura cob "
                    +"WHERE p.lineacotizacion_id=l.idlineacotizacion "
                    +"AND l.cotizacion_id=c.idcotizacion "
                    +"AND l.cobertura_id=cob.idcobertura "
                    +"AND c.vehiculo_id=v.idvehiculo "
                    +"AND v.cliente_id=?";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                Cobertura cobertura = new Cobertura();
                cobertura.setIdcobertura(rs.getInt("cob.idcobertura"));
                cobertura.setNombrecobertura(rs.getString("cob.nombrecobertura"));

                Linea_Cotizacion linea = new Linea_Cotizacion();
                linea.setCobertura(cobertura);

                Poliza poliza = new Poliza();
                poliza.setNumeropoliza(rs.getInt("p.numeropoliza"));
                
                try{
                    poliza.setFec_cont_poliza(rs.getDate("p.fec_cont_poliza"));
                }
                catch(Exception e){
                    poliza.setHora_cont_poliza(null);
                }
                try{
                    poliza.setHora_cont_poliza(rs.getTime("p.hora_cont_poliza").toLocalTime());
                }
                catch(Exception e){
                    poliza.setHora_cont_poliza(null);
                }
                poliza.setEstadopoliza(Estado_Poliza.obtenerEstadoPoliza(rs.getString("p.estadopoliza")));
                poliza.setLinea_cotizacion(linea);

                polizas.add(poliza);
            }
        } 
        catch (SQLException e) {
            System.out.println("obtenerPolizasCliente es el problema");
        }
        return polizas;
    }

    public Poliza getPoliza(int id){
        Poliza poliza = new Poliza();
        String sql = "SELECT * FROM poliza WHERE numeropoliza=?";
        try{
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setLegajo(rs.getString("usuario_legajo"));

                Documentacion documentacion = new Documentacion();
                documentacion.setIddocumentacion(rs.getInt("documentacion_id"));

                Linea_Cotizacion linea = new Linea_Cotizacion();
                linea.setIdlineacotizacion(rs.getInt("lineacotizacion_id"));

                Periodo_De_Pago periodo_pago = new Periodo_De_Pago();
                periodo_pago.setIdperiodopago(rs.getInt("periodopago_id"));

                Tipo_De_Contratacion tipo_contratacion = new Tipo_De_Contratacion();
                tipo_contratacion.setIdtipocontratacion(rs.getInt("tipocontratacion_id"));

                //AGREGAR ID
                poliza.setNumeropoliza(id);
                
                //AGREGAR USUARIO
                poliza.setUsuario(usuario);

                //AGREGAR DOCUMENTACION
                poliza.setDocumentacion(documentacion);

                //AGREGAR LINEA
                poliza.setLinea_cotizacion(linea);

                //AGREGAR PERIODO DE PAGO
                poliza.setPeriodo_pago(periodo_pago);

                //AGREGAR TIPO DE CONTRATACION
                poliza.setTipo_contratacion(tipo_contratacion);
                poliza.setPreciopolizaactual(rs.getDouble("preciopolizaactual"));
                poliza.setMontoasegurado(rs.getDouble("montoasegurado"));
                try{
                    poliza.setHora_cont_poliza(rs.getTime("hora_cont_poliza").toLocalTime());
                    poliza.setFec_cont_poliza(rs.getDate("fec_cont_poliza"));
                }
                catch(Exception e){
                    poliza.setFec_cont_poliza(null);
                    poliza.setHora_cont_poliza(null);
                }
                poliza.setFec_venc_poliza(rs.getDate("fec_venc_poliza"));
                poliza.setFec_canc_poliza(rs.getDate("fec_canc_poliza"));
                poliza.setAuto_renov_poliza(rs.getBoolean("auto_renov_poliza"));
                poliza.setEstadopoliza(Estado_Poliza.obtenerEstadoPoliza(rs.getString("estadopoliza")));
            }
        }
        catch ( SQLException e){
            System.out.println("getPoliza No funciona");
        }
        return poliza;
    }

    public void actualizarPoliza(Poliza poliza){
        String sql = "UPDATE poliza SET tipocontratacion_id=?, periodopago_id=?, preciopolizaactual=?, "
                    +"fec_cont_poliza=?, hora_cont_poliza=?, fec_venc_poliza=?, estadopoliza=? "
                    +"WHERE numeropoliza=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, poliza.getTipo_contratacion().getIdtipocontratacion());
            ps.setInt(2, poliza.getPeriodo_pago().getIdperiodopago());
            ps.setDouble(3, poliza.getPreciopolizaactual());
            ps.setDate(4, new Date(poliza.getFec_cont_poliza().getTime()));
            ps.setTime(5, Time.valueOf(poliza.getHora_cont_poliza()));
            ps.setDate(6, new Date(poliza.getFec_venc_poliza().getTime()));
            ps.setString(7, poliza.getEstadopoliza().name());
            ps.setInt(8, poliza.getNumeropoliza());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("actualizarPoliza es el problema");
        }
    }

    public ArrayList<Poliza> obtenerPolizasPendientes() {
        ArrayList<Poliza> polizas = new ArrayList<Poliza>();
        String sql = "SELECT * FROM poliza p, lineacotizacion l, cotizacion c, "
                    +"vehiculo v, Cobertura cob, cliente cl, persona per "
                    +"WHERE p.lineacotizacion_id=l.idlineacotizacion "
                    +"AND l.cotizacion_id=c.idcotizacion "
                    +"AND l.cobertura_id=cob.idcobertura "
                    +"AND c.vehiculo_id=v.idvehiculo "
                    +"AND v.cliente_id=cl.idcliente "
                    +"AND cl.persona_id=per.idpersona "
                    +"AND p.estadopoliza='PENDIENTE'";
        try {
            con=cn.conectar();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNombres(rs.getString("per.nombres"));
                cliente.setapellidos(rs.getString("per.apellidos"));

                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setMatricula(rs.getString("v.matricula"));
                vehiculo.setCliente(cliente);

                Cotizacion cotizacion = new Cotizacion();
                cotizacion.setIdcotizacion(rs.getInt("c.idcotizacion"));
                cotizacion.setFecha_creacion(rs.getDate("c.fechacreacioncotizacion"));
                cotizacion.setVehiculo(vehiculo);

                Cobertura cobertura = new Cobertura();
                cobertura.setIdcobertura(rs.getInt("cob.idcobertura"));
                cobertura.setNombrecobertura(rs.getString("cob.nombrecobertura"));

                Linea_Cotizacion linea = new Linea_Cotizacion();
                linea.setCobertura(cobertura);
                linea.setCotizacion(cotizacion);

                Poliza poliza = new Poliza();
                poliza.setNumeropoliza(rs.getInt("p.numeropoliza"));
                
                try{
                    poliza.setFec_cont_poliza(rs.getDate("p.fec_cont_poliza"));
                }
                catch(Exception e){
                    poliza.setHora_cont_poliza(null);
                }
                try{
                    poliza.setHora_cont_poliza(rs.getTime("p.hora_cont_poliza").toLocalTime());
                }
                catch(Exception e){
                    poliza.setHora_cont_poliza(null);
                }
                poliza.setEstadopoliza(Estado_Poliza.obtenerEstadoPoliza(rs.getString("p.estadopoliza")));
                poliza.setLinea_cotizacion(linea);

                polizas.add(poliza);
            }
        } 
        catch (SQLException e) {
            System.out.println("obtenerPolizasPendientes es el problema");
        }
        return polizas;
    }

    public void actualizarEstadoPoliza(Poliza poliza){
        String sql = "UPDATE poliza SET usuario_legajo=?, estadopoliza=? "
                    +"WHERE numeropoliza=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            if(poliza.getUsuario().getLegajo().equals("-1")){
                ps.setNull(1, Types.VARCHAR);
            }
            else{
                ps.setString(1, poliza.getUsuario().getLegajo());
            }
            ps.setString(2, poliza.getEstadopoliza().name());
            ps.setInt(3, poliza.getNumeropoliza());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            System.out.println("actualizarEstadoPoliza es el problema");
        }
    }

}