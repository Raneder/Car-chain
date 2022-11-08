package com.mycompany.web.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.web.modelo.Localidad;
import com.mycompany.web.modelo.Tipo_De_Documento;
import com.mycompany.web.modelo.Tipo_De_Usuario;
import com.mycompany.web.modelo.Usuario;

public class UsuarioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Usuario usuarioExiste(String legajo, String contraseña) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuario u, persona p WHERE u.legajo=? and p.contraseña=?";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, legajo);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            while (rs.next()) {
                //LOCALIDAD
                Localidad localidad = new Localidad();
                localidad.setIdlocalidad(rs.getInt("localidad_id"));

                //USUARIO
                usuario.setLegajo(rs.getString("legajo"));
                usuario.setEstado(rs.getBoolean("estado"));
                Tipo_De_Usuario tipo_usuario = Tipo_De_Usuario.obtenerTipo(rs.getString("tipousuario"));
                usuario.setTipoUsuario(tipo_usuario);

                //DATOS DE PERSONA EN USUARIO
                usuario.setIdPersona(rs.getInt("idpersona"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setapellidos(rs.getString("apellidos"));
                usuario.setFechanacimiento(rs.getDate("fechanacimiento"));
                Tipo_De_Documento tipo_documento = Tipo_De_Documento.obtenerTipoPorId(rs.getString("tipodocumento"));
                usuario.setTipoDocumento(tipo_documento);
                usuario.setDocumento(rs.getString("documento"));
                usuario.setDomicilio(rs.getString("domicilio"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setSexo(rs.getBoolean("sexo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setLocalidad(localidad);
            }
        } 
        catch (SQLException e) {
            System.out.println("usuarioExiste es el problema");
        }
        return usuario;
    }
}
