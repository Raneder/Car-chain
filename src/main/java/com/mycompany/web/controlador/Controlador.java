package com.mycompany.web.controlador;

import java.io.IOException;

import com.mycompany.web.modelo.Cliente;
import com.mycompany.web.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Controlador", urlPatterns = {"/inicio", "/no_funciona", "/perfil_usuario"})
public class Controlador extends HttpServlet{
    Cliente cliente = new Cliente();
    Usuario usuario = new Usuario();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        String leg = "";

        try{
            cliente = (Cliente) request.getSession().getAttribute("cliente");
            id = cliente.getIdCliente();
        }
        catch(Exception e){
            //System.out.println("No hay Cliente");
        }
        
        try{
            usuario = (Usuario) request.getSession().getAttribute("usuario");
            leg = usuario.getLegajo();
        }
        catch(Exception e){
            //System.out.println("No hay Usuario");
        }

        String url = request.getRequestURL().toString();
        String url1 = "/no_funciona";
        String url2 = "/perfil_usuario";
        if(url.contains(url1)){
            request.getRequestDispatcher("en_construccion.jsp").forward(request,response);
        }
        else if(url.contains(url2) && id > 0){
            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("perfil_usuario.jsp").forward(request,response);
        }
        else if(!leg.equals("")){
            System.out.println(usuario.getTipoUsuario().getId());
            if(usuario.getTipoUsuario().getId() == 2){
                response.sendRedirect(request.getContextPath() +"/vendedor");
            }
            else{
                response.sendRedirect(request.getContextPath());
            }
        }
        else{
            response.sendRedirect(request.getContextPath());
        }
    }
}
