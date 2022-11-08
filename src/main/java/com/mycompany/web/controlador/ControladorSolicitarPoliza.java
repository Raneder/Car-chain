package com.mycompany.web.controlador;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;

import com.mycompany.web.datos.ClienteDAO;
import com.mycompany.web.datos.CoberturaDAO;
import com.mycompany.web.datos.Cobertura_DetalleDAO;
import com.mycompany.web.datos.Configuracion_De_AntiguedadDAO;
import com.mycompany.web.datos.Configuracion_De_EdadDAO;
import com.mycompany.web.datos.Configuracion_De_LocalidadDAO;
import com.mycompany.web.datos.CotizacionDAO;
import com.mycompany.web.datos.DetalleDAO;
import com.mycompany.web.datos.DocumentacionDAO;
import com.mycompany.web.datos.Linea_CotizacionDAO;
import com.mycompany.web.datos.LocalidadDAO;
import com.mycompany.web.datos.MarcaDAO;
import com.mycompany.web.datos.ModeloDAO;
import com.mycompany.web.datos.PersonaDAO;
import com.mycompany.web.datos.PolizaDAO;
import com.mycompany.web.datos.ProvinciaDAO;
import com.mycompany.web.datos.VehiculoDAO;
import com.mycompany.web.datos.VersionDAO;
import com.mycompany.web.modelo.Cliente;
import com.mycompany.web.modelo.Cobertura;
import com.mycompany.web.modelo.Cobertura_Detalle;
import com.mycompany.web.modelo.Configuracion_De_Antiguedad;
import com.mycompany.web.modelo.Configuracion_De_Edad;
import com.mycompany.web.modelo.Configuracion_De_Localidad;
import com.mycompany.web.modelo.Cotizacion;
import com.mycompany.web.modelo.Detalle;
import com.mycompany.web.modelo.Documentacion;
import com.mycompany.web.modelo.Linea_Cotizacion;
import com.mycompany.web.modelo.Localidad;
import com.mycompany.web.modelo.Marca;
import com.mycompany.web.modelo.Modelo;
import com.mycompany.web.modelo.Poliza;
import com.mycompany.web.modelo.Provincia;
import com.mycompany.web.modelo.Tipo_De_Documento;
import com.mycompany.web.modelo.Vehiculo;
import com.mycompany.web.modelo.Version;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ControladorSolicitarPoliza", urlPatterns = {"/solicitar_poliza"})
@MultipartConfig(maxFileSize = 16177218)
public class ControladorSolicitarPoliza extends HttpServlet{
    final String MENSAJE1 = "Campo Vacío *";
    final String MENSAJE2 = "Matricula Invalida *";
    final String MENSAJE3 = "N° de Motor Invalido *";
    final String MENSAJE4 = "Seleccione Uno *";
    final String MENSAJE5 = "Documento Invalido *";
    final String MENSAJE6 = "Fecha Invalida *";
    final String MENSAJE7 = "Telefono Invalido *";
    final String MENSAJE8 = "Correo Invalido *";
    final String MENSAJE9 = "Esta cuenta ya está asociada a otro usuario. \n Por favor, inicie sesión y vuelva a intentarlo";
    final String MENSAJE10 = "Contraseña Corta *";
    final String MENSAJE11 = "No Coinciden *";
    final String MENSAJE12 = "Archivo No Subido";
    final String MENSAJE13 = "Matricula Invalida*";
    final String MENSAJE14 = "Este vehiculo ya está asociado a otro usuario. Por favor, inicie sesión y vuelva a intentarlo";
    final String MENSAJE15 = "Este vehiculo ya está asociado a otro usuario.";
    final String MENSAJE16 = "Este vehiculo ya está asociado a una poliza.";
    ArrayList<Linea_Cotizacion> lineascotizaciones;
    DocumentacionDAO documentaciondao = new DocumentacionDAO();
    Cobertura_DetalleDAO cobertura_detalleDAO = new Cobertura_DetalleDAO();
    ProvinciaDAO provinciaDAO = new ProvinciaDAO();
    LocalidadDAO localidadDAO = new LocalidadDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    VehiculoDAO vehiculoDAO = new VehiculoDAO();
    MarcaDAO marcaDAO = new MarcaDAO();
    ModeloDAO modeloDAO = new ModeloDAO();
    VersionDAO versionDAO = new VersionDAO();
    CotizacionDAO cotizacionDAO =  new CotizacionDAO();
    Linea_CotizacionDAO linea_CotizacionDAO = new Linea_CotizacionDAO();
    
    Documentacion documentacion = new Documentacion();
    Vehiculo vehiculo = new Vehiculo();
    Cotizacion cotizacion = new Cotizacion();
    Version version = new Version();
    Cliente cliente = new Cliente();
    String id_cobertura = "";

    boolean llave = true;
    int linea_cotizacion_id;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "solicitarCotizacion":
                cargarListasVehiculo(request);
                request.getRequestDispatcher("cargar_vehiculo.jsp").forward(request,response);
                break;
            case "cargarVehiculo":
                //OBTENER CLIENTE
                Cliente cliente1 = null;
                boolean llave_usuario = false;
                try{
                    cliente1 = (Cliente) request.getSession().getAttribute("cliente");
                    if(cliente1.getIdCliente() > 0){
                        llave_usuario = true;
                    }
                }
                catch(Exception e){ }

                //LLAVE DE CONFIRMACION
                llave = true;

                //REVISION DE MATRICULA
                String matricula = request.getParameter("matricula");
                if(matricula.equals("")){
                    request.setAttribute("mensajeError1", MENSAJE1);
                    llave = false;
                }
                else if(matricula.length() < 6){
                    request.setAttribute("mensajeError1", MENSAJE2);
                    llave = false;
                }
                else if(!llave_usuario && vehiculoDAO.VehiculoCotizado(matricula) > 0){
                    request.setAttribute("mensajeError1", MENSAJE13);
                    request.setAttribute("mensajeError8", MENSAJE14);
                    llave = false;
                }
                else if(llave_usuario){
                    if(!vehiculoDAO.VehiculoCotizablePorMi(matricula, cliente1.getIdCliente())){
                        request.setAttribute("mensajeError1", MENSAJE13);
                        request.setAttribute("mensajeError8", MENSAJE15);
                        llave = false;
                    }
                    else if(vehiculoDAO.VehiculoEnPoliza(matricula)){
                        request.setAttribute("mensajeError1", MENSAJE13);
                        request.setAttribute("mensajeError8", MENSAJE16);
                        llave = false;
                    }
                }

                //REVISION DE CHASIS
                String chasis = request.getParameter("chasis");
                if(chasis.equals("")){
                    request.setAttribute("mensajeError2", MENSAJE1);
                    llave = false;
                }
                
                //REVISION DE NUMERO DE MOTOR
                String numero_motor = request.getParameter("numero_motor");
                if(numero_motor.equals("")){
                    request.setAttribute("mensajeError3", MENSAJE1);
                    llave = false;
                }
                else{
                    char[] caracteres = numero_motor.toCharArray();
                    for(char c: caracteres) {
                        if(c < 48 || c > 57){
                            request.setAttribute("mensajeError3", MENSAJE3);
                            llave = false;
                            break;
                        }
                    }
                }

                //REVISIÓN DE GNC
                String gnc = request.getParameter("gnc");

                //REVISION DE MARCA
                String id_marca = request.getParameter("marca");
                if(id_marca.equals("-1")){
                    request.setAttribute("mensajeError4", MENSAJE4);
                    llave = false;
                }
                
                //REVISION DE MODELO
                String id_modelo = request.getParameter("modelo");
                if(id_modelo.equals("-1")){
                    request.setAttribute("mensajeError5", MENSAJE4);
                    llave = false;
                }

                //REVISION DE VERSION
                String idversion = request.getParameter("version");
                if(idversion.equals("-1")){
                    request.setAttribute("mensajeError6", MENSAJE4);
                    llave = false;
                }

                //REVISION DE AÑO
                String año = request.getParameter("año");
                if(año.equals("-1")){
                    request.setAttribute("mensajeError7", MENSAJE4);
                    llave = false;
                }

                if(llave){
                    //CARGAR MARCA
                    Marca marca_1 = marcaDAO.getMarca(Integer.parseInt(id_marca));
                    
                    //CARGAR MODELO
                    Modelo modelo_1 = modeloDAO.getModelo(Integer.parseInt(id_modelo));
                    modelo_1.setMarca(marca_1);

                    //CARGAR VERSION
                    version = versionDAO.getVersion(Integer.parseInt(idversion));
                    version.setModelo(modelo_1);
                    
                    //CARGAR VEHICULO
                    vehiculo.setMatricula(matricula);
                    vehiculo.setAñofabricación(Integer.parseInt(año));
                    vehiculo.setNumeromotor(numero_motor);
                    vehiculo.setChasis(chasis);
                    try{    if (gnc.equals("on")){  vehiculo.setGnc(true);  }  }
                    catch(Exception e){                       vehiculo.setGnc(false);   }
                    vehiculo.setVersion(version);
                    
                    //INTENTA CARGAR AL CLIENTE
                    if(llave_usuario){
                        vehiculo.setCliente(cliente1);
                        cargarListasCotizacion(request);
                        request.getRequestDispatcher("cotizaciones_vehiculo.jsp").forward(request,response);   
                    }
                    else{
                        cargarListasCliente(request);
                        cargarElementosCliente(request);
                        request.getRequestDispatcher("cargar_cliente.jsp").forward(request,response);
                    }
                }
                else{
                    cargarListasVehiculo(request);
                    cargarElementosVehiculo(request);
                    request.getRequestDispatcher("cargar_vehiculo.jsp").forward(request,response);
                }
                break;
            case "cargarDatosPersonales":
                //LLAVE DE CONFIRMACION
                llave = true;

                //REVISION DE NOMBRES
                String nombres = request.getParameter("nombres");
                if(nombres.equals("")){
                    request.setAttribute("mensajeError1", MENSAJE1);
                    llave = false;
                }

                //REVISION DE APELLIDOS
                String apellidos =request.getParameter("apellidos");
                if(apellidos.equals("")){
                    request.setAttribute("mensajeError2", MENSAJE1);
                    llave = false;
                }

                //REVISION DE TIPO DE DOCUMENTO
                String tipo_documento = request.getParameter("tipo_documento");
                if(tipo_documento.equals("-1")){
                    request.setAttribute("mensajeError3", MENSAJE4);
                    llave = false;
                }

                //REVISION DE DOCUMENTO
                String documento = request.getParameter("documento");
                if(documento.equals("")){
                    request.setAttribute("mensajeError4", MENSAJE1);
                    llave = false;
                }
                else{
                    char[] caracteres = documento.toCharArray();
                    boolean letra_detectada = false;
                    for(char c: caracteres) {
                        if(c < 48 || c > 57){
                            letra_detectada = true;
                            request.setAttribute("mensajeError4", MENSAJE5);
                            llave = false;
                            break;
                        }
                    }
                    if(!letra_detectada && documento.length() < 5){
                        request.setAttribute("mensajeError4", MENSAJE5);
                        llave = false;
                    }
                }

                //REVISION FECHA NACIMIENTO
                String date = request.getParameter("date");
                boolean llave_fecha = cliente.validarFecha(date);
                if(!llave_fecha){
                    request.setAttribute("mensajeError5", MENSAJE6);
                    llave = false;
                }

                //REVISION DE TELEFONO
                String telefono = request.getParameter("telefono");
                if(telefono.equals("")){
                    request.setAttribute("mensajeError6", MENSAJE1);
                    llave = false;
                }
                else{
                    char[] caracteres = telefono.toCharArray();
                    boolean letra_detectada = false;
                    for(char c: caracteres) {
                        if(c < 48 || c > 57){
                            letra_detectada = true;
                            request.setAttribute("mensajeError6", MENSAJE7);
                            llave = false;
                            break;
                        }
                    }
                    if(!letra_detectada && telefono.length() < 9){
                        request.setAttribute("mensajeError6", MENSAJE7);
                        llave = false;
                    }
                }

                //REVISION DE TIPO SEXO
                String sexo = request.getParameter("sexo");
                if(sexo.equals("-1")){
                    request.setAttribute("mensajeError7", MENSAJE4);
                    llave = false;
                }

                //REVISION DE PROVINCIA
                String id_provincia = request.getParameter("provincia");
                if(id_provincia.equals("-1")){
                    request.setAttribute("mensajeError8", MENSAJE4);
                    llave = false;
                }

                //REVISION DE LOCALIDAD
                String id_localidad = request.getParameter("localidad");
                if(id_localidad.equals("-1")){
                    request.setAttribute("mensajeError9", MENSAJE4);
                    llave = false;
                }

                //REVISION DE DOMICILIO
                String domicilio = request.getParameter("domicilio");
                if(domicilio.equals("")){
                    request.setAttribute("mensajeError10", MENSAJE1);
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
                    cliente.setNombres(nombres);
                    cliente.setapellidos(apellidos);
                    cliente.setTipoDocumento(Tipo_De_Documento.obtenerTipoPorId(tipo_documento));
                    cliente.setDocumento(documento);
                    cliente.setFechanacimiento(date);
                    cliente.setTelefono(telefono);
                    cliente.setSexo(sexo);
                    cliente.setLocalidad(localidad_1);
                    cliente.setDomicilio(domicilio);
                    
                    //VEHICULO
                    vehiculo.setCliente(cliente);
                    cargarListasCotizacion(request);
                    request.getRequestDispatcher("cotizaciones_vehiculo.jsp").forward(request,response);
                }
                else{
                    cargarListasCliente(request);
                    cargarElementosCliente(request);
                    request.getRequestDispatcher("cargar_cliente.jsp").forward(request,response);
                }
                break;
            case "solicitarContratacionDeCobertura":
                int n = lineascotizaciones.size();
                for(int i=1; i<=n; i++){
                    id_cobertura = request.getParameter(i+"");
                    try{
                        if(id_cobertura.equals(i+"")){
                            i = n + 1;
                        }
                    }
                    catch(Exception e){
                        id_cobertura = "-1";
                    }
                }

                //INTENTA CARGAR AL CLIENTE
                try{
                    Cliente cliente2 = (Cliente) request.getSession().getAttribute("cliente");
                    if(cliente2.getIdCliente() > 0){
                        cargarCotizacionEnSistema(true, "", "");
                        if(id_cobertura.equals("-1")){
                            request.setAttribute("mensaje", "2");
                            request.getRequestDispatcher("cotizaciones_poliza.jsp").forward(request,response);
                        }else{
                            request.getRequestDispatcher("cargar_documentacion.jsp").forward(request,response);
                        }
                    }
                }
                catch(Exception e){
                    request.getRequestDispatcher("completar_registro.jsp").forward(request,response);
                }
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
                    request.setAttribute("mensajeError1", MENSAJE8);
                    llave = false;
                }
                else if (personaDAO.buscarCorreo(correo)){
                    request.setAttribute("mensajeError4", MENSAJE9);
                    request.setAttribute("mensajeError1", MENSAJE8);
                    llave = false;
                }
                
                //REVISION DE CONTRASEÑA
                String contraseña = request.getParameter("contraseña");
                if(contraseña.equals("")){
                    request.setAttribute("mensajeError2", MENSAJE1);
                    llave = false;
                }
                else if(contraseña.length() < 6){
                    request.setAttribute("mensajeError2", MENSAJE10);
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
                    request.setAttribute("mensajeError2", MENSAJE11);
                    llave = false;
                }
                if(llave){
                    cargarCotizacionEnSistema(false, correo, contraseña);
                    HttpSession session = request.getSession();
                    session.setAttribute("cliente", cliente);

                    if(id_cobertura.equals("-1")){
                        request.setAttribute("mensaje", "2");
                        request.getRequestDispatcher("cotizaciones_poliza.jsp").forward(request,response);
                    }else{
                        request.getRequestDispatcher("cargar_documentacion.jsp").forward(request,response);
                    }
                }
                else{
                    cargarElementosRegistro(request);
                    request.getRequestDispatcher("completar_registro.jsp").forward(request,response);
                }
                break;
            case "cargarDocumentacion":
                documentacion.setFotofrontal(request.getPart("foto_frontal").getInputStream());
                documentacion.setFototrasera(request.getPart("foto_trasera").getInputStream());
                documentacion.setFotolateraluno(request.getPart("foto_lateral_1").getInputStream());
                documentacion.setFotolateraldos(request.getPart("foto_lateral_2").getInputStream());
                documentacion.setFototecho(request.getPart("foto_techo").getInputStream());
                documentacion.setCedulaverde(request.getPart("cedula_verde").getInputStream());

                for(Linea_Cotizacion linea: lineascotizaciones){
                    if(id_cobertura.equals(linea.getCobertura().getIdcobertura()+"")){
                        Cobertura_Detalle cobertura_detalle = cobertura_detalleDAO.listar(linea.getCobertura());
                        double monto_aseg = 0;
                        if(linea.getCotizacion().getVehiculo().isGnc()){
                            monto_aseg = linea.getCotizacion().getVehiculo().getVersion().getPreciomercadognc();
                        }
                        else{
                            monto_aseg = linea.getCotizacion().getVehiculo().getVersion().getPreciomercado();
                        }
                        
                        request.setAttribute("linea", linea);
                        request.setAttribute("documentacion", documentacion);
                        request.setAttribute("cobertura_detalle", cobertura_detalle);
                        request.setAttribute("monto_aseg", monto_aseg);
                    }
                }
                request.getRequestDispatcher("contratar_poliza.jsp").forward(request,response);
                break;
            case "confirmarContrataciónPóliza":
                int documentacion_id = documentaciondao.agregar(documentacion);
                documentacion.setIddocumentacion(documentacion_id);
                Poliza poliza = null; 
                for(Linea_Cotizacion linea: lineascotizaciones){
                    if(id_cobertura.equals(linea.getCobertura().getIdcobertura()+"")){
                        linea.setIdlineacotizacion(linea_cotizacion_id);
                        poliza = new Poliza(documentacion, linea);
                    }
                }                
                PolizaDAO polizaDAO = new PolizaDAO();
                polizaDAO.agregar(poliza);

                request.setAttribute("mensaje", "1");
                request.getRequestDispatcher("cotizaciones_poliza.jsp").forward(request,response);
                break;
            case "Cancelar":
                response.sendRedirect(request.getContextPath());
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cargarListasVehiculo(request);
        request.getRequestDispatcher("cargar_vehiculo.jsp").forward(request,response);
    }
    
    //METODOS DE INFORMACION DE VEHICULO
    public void cargarListasVehiculo(HttpServletRequest request){
        //AÑOS
        ArrayList<String> años = new ArrayList<String>();
        Calendar hoy = Calendar.getInstance();
        int año = hoy.get(Calendar.YEAR);
        for(int i=año; i>=1990; i--){
            años.add(i+"");
        }
        request.setAttribute("años", años);
        request.setAttribute("marcas", marcaDAO.listar());
        request.setAttribute("modelos", modeloDAO.listar());
        request.setAttribute("versiones", versionDAO.listar());
    }
    public void cargarElementosVehiculo(HttpServletRequest request){
        request.setAttribute("matricula", request.getParameter("matricula"));
        request.setAttribute("chasis", request.getParameter("chasis"));
        request.setAttribute("numero_motor", request.getParameter("numero_motor"));
        String gnc = request.getParameter("gnc");
        try{    if (gnc.equals("on")){   request.setAttribute("valor_gnc", true); }  }
        catch(Exception e){                       request.setAttribute("valor_gnc", false); }
        request.setAttribute("marca_id", request.getParameter("marca"));
        request.setAttribute("modelo_id", request.getParameter("modelo"));
        request.setAttribute("version_id", request.getParameter("version"));
        request.setAttribute("año_valor", request.getParameter("año"));
    }

    //METODOS DE INFORMACION DE CLIENTE
    public void cargarListasCliente(HttpServletRequest request){
        request.setAttribute("tiposdedoc", Tipo_De_Documento.values());
        request.setAttribute("provincias", provinciaDAO.listar());
        request.setAttribute("localidades", localidadDAO.listar());
    }
    public void cargarElementosCliente(HttpServletRequest request){
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

    //METODOS DE VISTA COTIZACION
    public void cargarListasCotizacion(HttpServletRequest request){
        //OBTENER LA EDAD
        Configuracion_De_EdadDAO config_edadDAO = new Configuracion_De_EdadDAO();
        Configuracion_De_Edad config_edad = config_edadDAO.getConfigEdad(vehiculo.getCliente().calcularEdad());

        //OBTENER LA LOCALIDAD
        Configuracion_De_LocalidadDAO config_localidadDAO = new Configuracion_De_LocalidadDAO();
        Configuracion_De_Localidad config_localidad = config_localidadDAO.getConfigLocalidad(vehiculo.getCliente().getLocalidad());
        
        //OBTENER LA ANTIGUEDAD
        Configuracion_De_AntiguedadDAO config_antiguedadDAO = new Configuracion_De_AntiguedadDAO();
        Configuracion_De_Antiguedad config_antiguedad = config_antiguedadDAO.getConfigAntiguedad(vehiculo.calcularAntiguedad());

        //CREAR LA COTIZACION
        cotizacion = new Cotizacion(0, vehiculo, config_localidad, config_edad, config_antiguedad);

        //OBTENER TODAS LAS COBERTURAS ACTIVAS
        CoberturaDAO coberturadao = new CoberturaDAO();
        ArrayList<Cobertura> coberturas = coberturadao.listarActivos();

        //OBTENER TODOS LOS DETALLES ACTIVOS
        DetalleDAO detalleDAO = new DetalleDAO();
        ArrayList<Detalle> detalles = detalleDAO.listarActivos();
        
        //GENERAR TODAS LAS LINEAS DE COTIZACION
        lineascotizaciones = new ArrayList<Linea_Cotizacion>();
        for(Cobertura cobertura : coberturas) {
            Cobertura_Detalle cobertura_detalle = cobertura_detalleDAO.listar(cobertura);
            Linea_Cotizacion linea_cotizacion = new Linea_Cotizacion(cotizacion, cobertura);
            linea_cotizacion.calcularMonto(cobertura_detalle);
            lineascotizaciones.add(linea_cotizacion);
        }

        //OBTENER TODOS LOS DETALLES ASOCIADOS A CADA COBERTURA
        ArrayList<String[]> lineasTotales = new ArrayList<String[]>();
        for(Detalle detalle : detalles) {
            String linea[] = new String[coberturas.size() + 1];
            linea [0] = detalle.getNombredetalle();
            int num = 1;
            for(Cobertura cobertura : coberturas) {
                boolean llave = cobertura_detalleDAO.cobertura_detalle_existe(cobertura, detalle);
                if(llave){
                    linea[num] = "1";
                }
                else{
                    linea[num] = "0";
                }
                num = num + 1;
            }
            lineasTotales.add(linea);
        }

        //ENVIAR PARAMETROS
        request.setAttribute("lineascotizaciones", lineascotizaciones);
        request.setAttribute("lineasTotales", lineasTotales);
    }

    //METODOS DE COMPLETAR REGISTRO
    public void cargarElementosRegistro(HttpServletRequest request){
        request.setAttribute("correo", request.getParameter("correo"));
        request.setAttribute("contraseña", request.getParameter("contraseña"));
        request.setAttribute("confirmar_contraseña", request.getParameter("confirmar_contraseña"));
    }

    private void cargarCotizacionEnSistema(boolean logueado, String correo, String contraseña) {
        int vehiculo_id = 0;
        if(logueado){
            //SE ACTUALIZA O AGREGA VEHICULO EN BASE DE DATOS
            if(vehiculoDAO.VehiculoCotizado(vehiculo.getMatricula()) > 0){
                vehiculo_id = vehiculoDAO.VehiculoCotizado(vehiculo.getMatricula());
                vehiculoDAO.actualizar(vehiculo);
            }else{
                vehiculo_id = vehiculoDAO.agregar(vehiculo);
            }
        }
        else{
            //SE EXTRAE CLIENTE Y SE AÑADEN DATOS A CLIENTE
            Cliente cliente = vehiculo.getCliente();
            cliente.setCorreo(correo);
            cliente.setContraseña(contraseña);

            //SE CREA A LA PERSONA
            int persona_id = personaDAO.agregar(cliente.obtenerPersona());
                    
            //SE AÑADE CLIENTE A VEHICULO
            cliente.setIdPersona(persona_id);

            //SE CREA AL CLIENTE
            int cliente_id = clienteDAO.agregar(cliente);
            cliente.setIdCliente(cliente_id);
            vehiculo.setCliente(cliente);
                    
            //SE CREA VEHICULO
            vehiculo_id = vehiculoDAO.agregar(vehiculo);
        }

        //AQUI INSERTAR COTIZACION Y LINEA COTIZACION
        Vehiculo vehiculo_c = null;
        Cotizacion cotizacion_c = null;
        if(!id_cobertura.equals("-1")){
            for(Linea_Cotizacion linea: lineascotizaciones){
                if(id_cobertura.equals(linea.getCobertura().getIdcobertura()+"")){
                
                    //AÑADO ID DE VEHICULO A LINEA COTIZACION
                    vehiculo_c = linea.getCotizacion().getVehiculo();
                    vehiculo_c.setIdvehiculo(vehiculo_id);

                    //AGREGO COTIZACION EN BASE DE DATOS
                    cotizacion_c = linea.getCotizacion();
                    cotizacion_c.setVehiculo(vehiculo_c);
                    
                    /*
                    System.out.println("primer  camino: "+vehiculo_id);
                    System.out.println(cotizacion.getVehiculo().getIdvehiculo());
                    System.out.println(cotizacion.getConfig_localidad().getIdconfiglocalidad());
                    System.out.println(cotizacion.getConfig_edad().getIdconfigedad());
                    System.out.println(cotizacion.getConfig_antiguedad().getIdconfigantiguedad());
                    System.out.println(cotizacion.getFecha_creacion());
                    System.out.println(cotizacion.getFecha_vencimiento());*/

                    int cotizacion_id = cotizacionDAO.agregar(cotizacion_c);
                    cotizacion_c.setIdcotizacion(cotizacion_id);
                }
            }
        }
        else{
            //AÑADO ID DE VEHICULO A LINEA COTIZACION
            vehiculo_c = lineascotizaciones.get(0).getCotizacion().getVehiculo();
            
            //System.out.println("segundo camino: "+vehiculo_id);

            //AGREGO COTIZACION EN BASE DE DATOS
            vehiculo_c.setIdvehiculo(vehiculo_id);
            cotizacion_c = lineascotizaciones.get(0).getCotizacion();
            cotizacion_c.setVehiculo(vehiculo_c);
            int cotizacion_id = cotizacionDAO.agregar(cotizacion_c);
            cotizacion_c.setIdcotizacion(cotizacion_id);
        }

        for(Linea_Cotizacion linea: lineascotizaciones){
            linea.setCotizacion(cotizacion_c);
            if(id_cobertura.equals(linea.getCobertura().getIdcobertura()+"")){
                //AGREGO LINEA DE COTIZACION A BASE DE DATOS
                linea_cotizacion_id = linea_CotizacionDAO.agregar(linea);
            }
            else{
                linea_CotizacionDAO.agregar(linea);
            }
        }
    }
}