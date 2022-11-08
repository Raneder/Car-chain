package com.mycompany.web.datos;

import com.mycompany.web.modelo.Documentacion;

import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentacionDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Documentacion> Listar(int id) {
        ArrayList<Documentacion> list = new ArrayList<>();
        String sql = "SELECT * FROM documentacion WHERE iddocumentacion="+id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Documentacion doc1 = new Documentacion();
                doc1.setIddocumentacion(rs.getInt("iddocumentacion"));
                doc1.setFotofrontal(rs.getBinaryStream("fotofrontal"));
                doc1.setFototrasera(rs.getBinaryStream("fototrasera"));
                doc1.setFotolateraluno(rs.getBinaryStream("fotolateraluno"));
                doc1.setFotolateraldos(rs.getBinaryStream("fotolateraldos"));
                doc1.setFototecho(rs.getBinaryStream("fototecho"));
                doc1.setCedulaverde(rs.getBinaryStream("cedulaverde"));
                list.add(doc1);
            }
        } 
        catch (Exception e) {
            System.out.println("1 NO funciona");
        }
        return list;
    }

    public int agregar(Documentacion documentacion) {
        int id = 0;
        String sql = "INSERT INTO documentacion (fotofrontal, fototrasera, fotolateraluno, "
                    +"fotolateraldos, fototecho, cedulaverde) "
                    +"VALUES(?, ?, ?, ?, ?, ?)";
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setBlob(1, documentacion.getFotofrontal());
            ps.setBlob(2, documentacion.getFototrasera());
            ps.setBlob(3, documentacion.getFotolateraluno());
            ps.setBlob(4, documentacion.getFotolateraldos());
            ps.setBlob(5, documentacion.getFototecho());
            ps.setBlob(6, documentacion.getCedulaverde());            
            int affectedRows = ps.executeUpdate();
            id = obtenerId(ps, affectedRows);
            ps.close();
        } 
        catch(Exception e){
            System.out.println("NO funciona Insertar documentacion");
        }
        return id;
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

    public void mostrarImgGuardada(int id, String col, HttpServletResponse response){
        String sql = "SELECT * FROM documentacion where iddocumentacion="+id;
        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();	
            if(rs.next()){
                Blob blob = rs.getBlob(col);
                byte byteArray[] = blob.getBytes(1, (int) blob.length());
                response.setContentType("image/gif");
                OutputStream os = response.getOutputStream();
                os.write(byteArray);
                os.flush();
                os.close();
            }
            else{
                System.out.println("Imagen no encontrada");
            }
        }
        catch(Exception e){
            System.out.println("2 NO funciona");
        }
    }

}


