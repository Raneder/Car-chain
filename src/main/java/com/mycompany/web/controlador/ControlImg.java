package com.mycompany.web.controlador;

import java.io.IOException;

import com.mycompany.web.datos.DocumentacionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Controlimg")
public class ControlImg extends HttpServlet{
    DocumentacionDAO docDAO = new DocumentacionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        String p = req.getParameter("p");
        switch(p){
            case "frente":
                docDAO.mostrarImgGuardada(id, "fotofrontal", resp);
                break;
            case "trasera":
                docDAO.mostrarImgGuardada(id, "fototrasera", resp);
                break;
            case "lateral1":
                docDAO.mostrarImgGuardada(id, "fotolateraluno", resp);
                break;
            case "lateral2":
                docDAO.mostrarImgGuardada(id, "fotolateraldos", resp);
                break;
            case "techo":
                docDAO.mostrarImgGuardada(id, "fototecho", resp);
                break;
            case "cedula":
                docDAO.mostrarImgGuardada(id, "cedulaverde", resp);
                break;
        }
    }
    
}