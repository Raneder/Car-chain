package com.mycompany.web.controlador;

import java.io.IOException;

import com.mycompany.web.datos.ClienteDAO;
import com.mycompany.web.datos.LocalidadDAO;
import com.mycompany.web.datos.PersonaDAO;
import com.mycompany.web.datos.ProvinciaDAO;
import com.mycompany.web.datos.UsuarioDAO;
import com.mycompany.web.modelo.Cliente;
import com.mycompany.web.modelo.Localidad;
import com.mycompany.web.modelo.Persona;
import com.mycompany.web.modelo.Provincia;
import com.mycompany.web.modelo.Tipo_De_Documento;
import com.mycompany.web.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ControladorIniciarSesion", urlPatterns = {"/login", "/register"})
public class ControladorIniciarSesion extends HttpServlet{
    final String MENSAJE1 = "Campo Vacío *";
    final String MENSAJE2 = "Los datos ingresados son Incorrectos";
    final String MENSAJE3 = "Correo Invalido *";
    final String MENSAJE4 = "Contraseña Corta *";
    final String MENSAJE5 = "No Coinciden *";
    final String MENSAJE6 = "Seleccione Uno *";
    final String MENSAJE7 = "Documento Invalido *";
    final String MENSAJE8 = "Fecha Invalida *";
    final String MENSAJE9 = "Telefono Invalido *";
    final String MENSAJE10 = "Este correo ya está asociado a una cuenta";
    ProvinciaDAO provinciaDAO = new ProvinciaDAO();
    LocalidadDAO localidadDAO = new LocalidadDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    Persona persona = new Persona();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    Usuario usuario = new Usuario();
    Cliente cliente = new Cliente();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Login":
                request.getRequestDispatcher("login.jsp").forward(request,response);
                break;
            case "iniciarSesión":
                //LLAVE DE CONFIRMACION
                boolean llave = true;
                
                //REVISIÓN DE CORREO O LEGAJO
                String cor_leg = request.getParameter("cor_leg");
                if(cor_leg.equals("")){
                    request.setAttribute("mensajeError1", MENSAJE1);
                    llave=false;
                }

                //REVISIÓN DE CONTRASEÑA
                String contraseña = request.getParameter("contraseña");
                if(contraseña.equals("")){
                    request.setAttribute("mensajeError2", MENSAJE1);
                    llave=false;
                }
                
                //REDIRECCIONAR
                if(llave){
                    String legajo = "";
                    int cliente_id = 0;
                    int id_localidad = 0;
                    try {
                        usuario = usuarioDAO.usuarioExiste(cor_leg, contraseña);
                        legajo = usuario.getLegajo();
                        id_localidad = usuario.getLocalidad().getIdlocalidad();
                    }
                    catch(Exception e){     legajo = "";    }

                    try{
                        cliente = clienteDAO.clienteExiste(cor_leg, contraseña);
                        cliente_id = cliente.getIdPersona();
                        id_localidad = cliente.getLocalidad().getIdlocalidad();
                    }
                    catch(Exception e){     cliente_id=0;   }

                    if (cliente_id == 0 && legajo.equals("")){
                        request.setAttribute("mensajeError3", MENSAJE2);
                        cargarElementos(request);
                        request.getRequestDispatcher("login.jsp").forward(request,response);
                    }
                    else{                        
                        //LOCALIDAD
                        Localidad localidad = localidadDAO.getLocalidad(id_localidad);

                        //PROVINCIA
                        Provincia provincia = provinciaDAO.getProvincia(localidad.getProvincia().getIdprovincia());

                        //PROVINCIA SE AÑADE A LOCALIDAD
                        localidad.setProvincia(provincia);

                        if(!legajo.equals("")){
                            //LOCALIDAD SE AÑADE CLIENTE
                            usuario.setLocalidad(localidad);

                            //CREAR SESION PARA USUARIO
                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", usuario);
                            if(usuario.getTipoUsuario().getId() == 2){
                                //SI ES VENDEDOR QUE LO MANDE AL CONTROLADOR
                                response.sendRedirect(request.getContextPath() + "/vendedor");
                            }
                            else{
                                response.sendRedirect(request.getContextPath());
                            }
                        }
                        else{
                            //LOCALIDAD SE AÑADE CLIENTE
                            cliente.setLocalidad(localidad);

                            //CREAR SESION PARA CLIENTE
                            HttpSession session = request.getSession();
                            session.setAttribute("cliente", cliente);
                            response.sendRedirect(request.getContextPath());
                        }
                    }
                }
                else{
                    cargarElementos(request);
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }                
                break;

            case "CerrarSesion":
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                break;

            case "solicitarRegistro":
                cargarListas(request);
                request.getRequestDispatcher("register.jsp").forward(request,response);
                break;

            case "crearCuentaCliente":
                //LLAVE DE CONFIRMACION
                llave = true;

                //REVISION DE CORREO
                String correo = request.getParameter("correo");
                if(correo.equals("")){
                    request.setAttribute("mensajeError1", MENSAJE1);
                    llave = false;
                }
                else if(!correo.contains("@")){
                    request.setAttribute("mensajeError1", MENSAJE3);
                    llave = false;
                }
                else if(personaDAO.buscarCorreo(correo)){
                    request.setAttribute("mensajeError14", MENSAJE10);
                    request.setAttribute("mensajeError1", MENSAJE3);
                    llave = false;
                }
                
                //REVISION DE CONTRASEÑA
                contraseña = request.getParameter("contraseña");
                if(contraseña.equals("")){
                    request.setAttribute("mensajeError2", MENSAJE1);
                    llave = false;
                }
                else if(contraseña.length() < 6){
                    request.setAttribute("mensajeError2", MENSAJE4);
                    llave = false;
                }

                //REVISION DE CONFIRMAR CONTRASEÑA
                String confirmar_contraseña = request.getParameter("confirmar_contraseña");
                if(confirmar_contraseña.equals("")){
                    request.setAttribute("mensajeError3", MENSAJE1);
                    llave = false;
                }

                //REVISAR SI LAS CONTRASEÑAS SON IGUALES
                if(!contraseña.equals("") && !confirmar_contraseña.equals("") 
                    && !contraseña.equals(confirmar_contraseña)){
                    request.setAttribute("mensajeError2", MENSAJE5);
                    llave = false;
                }

                //REVISION DE NOMBRES
                String nombres = request.getParameter("nombres");
                if(nombres.equals("")){
                    request.setAttribute("mensajeError4", MENSAJE1);
                    llave = false;
                }

                //REVISION DE APELLIDOS
                String apellidos =request.getParameter("apellidos");
                if(apellidos.equals("")){
                    request.setAttribute("mensajeError5", MENSAJE1);
                    llave = false;
                }

                //REVISION DE TIPO DE DOCUMENTO
                String tipo_documento = request.getParameter("tipo_documento");
                if(tipo_documento.equals("-1")){
                    request.setAttribute("mensajeError6", MENSAJE6);
                    llave = false;
                }

                //REVISION DE DOCUMENTO
                String documento = request.getParameter("documento");
                if(documento.equals("")){
                    request.setAttribute("mensajeError7", MENSAJE1);
                    llave = false;
                }
                else{
                    char[] caracteres = documento.toCharArray();
                    boolean letra_detectada = false;
                    for(char c: caracteres) {
                        if(c < 48 || c > 57){
                            letra_detectada = true;
                            request.setAttribute("mensajeError7", MENSAJE7);
                            llave = false;
                            break;
                        }
                    }
                    if(!letra_detectada && documento.length() < 5){
                        request.setAttribute("mensajeError7", MENSAJE7);
                        llave = false;
                    }
                }

                //REVISION FECHA NACIMIENTO
                String date = request.getParameter("date");
                boolean llave_fecha = persona.validarFecha(date);
                if(!llave_fecha){
                    request.setAttribute("mensajeError8", MENSAJE8);
                    llave = false;
                }

                //REVISION DE TELEFONO
                String telefono = request.getParameter("telefono");
                if(telefono.equals("")){
                    request.setAttribute("mensajeError9", MENSAJE1);
                    llave = false;
                }
                else{
                    char[] caracteres = telefono.toCharArray();
                    boolean letra_detectada = false;
                    for(char c: caracteres) {
                        if(c < 48 || c > 57){
                            letra_detectada = true;
                            request.setAttribute("mensajeError9", MENSAJE9);
                            llave = false;
                            break;
                        }
                    }
                    if(!letra_detectada && telefono.length() < 9){
                        request.setAttribute("mensajeError9", MENSAJE9);
                        llave = false;
                    }
                }

                //REVISION DE TIPO SEXO
                String sexo = request.getParameter("sexo");
                if(sexo.equals("-1")){
                    request.setAttribute("mensajeError10", MENSAJE6);
                    llave = false;
                }

                //REVISION DE PROVINCIA
                String id_provincia = request.getParameter("provincia");
                if(id_provincia.equals("-1")){
                    request.setAttribute("mensajeError11", MENSAJE6);
                    llave = false;
                }

                //REVISION DE LOCALIDAD
                String id_localidad = request.getParameter("localidad");
                if(id_localidad.equals("-1")){
                    request.setAttribute("mensajeError12", MENSAJE6);
                    llave = false;
                }

                //REVISION DE DOMICILIO
                String domicilio = request.getParameter("domicilio");
                if(domicilio.equals("")){
                    request.setAttribute("mensajeError13", MENSAJE1);
                    llave = false;
                }

                //REDIRECCIONAR
                if(llave){
                    //PROVINCIA
                    Provincia provincia_1 = provinciaDAO.getProvincia(Integer.parseInt(id_provincia));

                    //LOCALIDAD
                    Localidad localidad_1 = localidadDAO.getLocalidad(Integer.parseInt(id_localidad));
                    localidad_1.setProvincia(provincia_1);

                    //CLIENTE
                    cliente.setCorreo(correo);
                    cliente.setContraseña(contraseña);
                    cliente.setNombres(nombres);
                    cliente.setapellidos(apellidos);
                    cliente.setTipoDocumento(Tipo_De_Documento.obtenerTipoPorId(tipo_documento));
                    cliente.setDocumento(documento);
                    cliente.setFechanacimiento(date);
                    cliente.setTelefono(telefono);
                    cliente.setSexo(sexo);
                    cliente.setLocalidad(localidad_1);
                    cliente.setDomicilio(domicilio);

                    //CREAR PERSONA Y OBTENER ID
                    int id_persona = 0;
                    id_persona = personaDAO.agregar(cliente.obtenerPersona());

                    //CLEAR CLIENTE Y OBTENER ID
                    if (id_persona > 0){
                        cliente.setIdPersona(id_persona);
                        cliente.setIdCliente(clienteDAO.agregar(cliente));
                    }

                    if(cliente.getIdCliente() > 0) {
                        HttpSession session1 = request.getSession();
                        session1.setAttribute("cliente", cliente);
                        request.getRequestDispatcher("index.jsp").forward(request,response);
                    }
                    else {
                        request.setAttribute("mensajeError14", MENSAJE10);
                        request.setAttribute("mensajeError1", MENSAJE3);
                        cargarListas(request);
                        cargarElementosRegistro(request);
                        request.getRequestDispatcher("register.jsp").forward(request,response);
                    }
                }
                else{
                    cargarListas(request);
                    cargarElementosRegistro(request);
                    request.getRequestDispatcher("register.jsp").forward(request,response);
                }
                break;

            case "Cancelar":
                HttpSession session_1 = request.getSession();
                session_1.invalidate();
                response.sendRedirect(request.getContextPath());
            break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String leg = "";
        int id = 0;

        try{
            usuario = (Usuario) request.getSession().getAttribute("usuario");
            leg = usuario.getLegajo();
        }
        catch(Exception e){
            //System.out.println("No hay Usuario");
        }

        try{
            cliente = (Cliente) request.getSession().getAttribute("cliente");
            id = cliente.getIdCliente();
        }
        catch(Exception e){
            //System.out.println("No hay Cliente");
        }

        if(leg.equals("") && id==0){
            String url = request.getRequestURL().toString();
            String url1 = "/login";
            String url2 = "/register";
            if(url.contains(url1)){
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
            if(url.contains(url2)){
                cargarListas(request);
                cargarElementosRegistro(request);
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }
        }
        else{
            if(usuario.getTipoUsuario().getId() == 2){
                response.sendRedirect(request.getContextPath() +"/vendedor");
            }
            else{
                response.sendRedirect(request.getContextPath());
            }
        }
    }

    public void cargarElementos(HttpServletRequest request){
        request.setAttribute("cor_leg", request.getParameter("cor_leg"));
        request.setAttribute("contraseña", request.getParameter("contraseña"));
    }
    
    public void cargarListas(HttpServletRequest request){
        request.setAttribute("tiposdedoc", Tipo_De_Documento.values());
        request.setAttribute("provincias", provinciaDAO.listar());
        request.setAttribute("localidades", localidadDAO.listar());
    }
    
    public void cargarElementosRegistro(HttpServletRequest request){
        request.setAttribute("correo", request.getParameter("correo"));
        request.setAttribute("contraseña", request.getParameter("contraseña"));
        request.setAttribute("confirmar_contraseña", request.getParameter("confirmar_contraseña"));
        request.setAttribute("nombres", request.getParameter("nombres"));
        request.setAttribute("apellidos", request.getParameter("apellidos"));
        request.setAttribute("documento", request.getParameter("documento"));
        request.setAttribute("date", request.getParameter("date"));
        request.setAttribute("telefono", request.getParameter("telefono"));
        request.setAttribute("sex", request.getParameter("sexo"));
        request.setAttribute("tipo_documento", request.getParameter("tipo_documento"));
        request.setAttribute("prov_id", request.getParameter("provincia"));
        request.setAttribute("loc_id", request.getParameter("localidad"));
        request.setAttribute("domicilio", request.getParameter("domicilio"));
    }

}
