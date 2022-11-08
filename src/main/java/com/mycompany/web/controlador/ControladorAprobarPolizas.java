package com.mycompany.web.controlador;

import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.web.datos.ClienteDAO;
import com.mycompany.web.datos.CoberturaDAO;
import com.mycompany.web.datos.Cobertura_DetalleDAO;
import com.mycompany.web.datos.CotizacionDAO;
import com.mycompany.web.datos.Linea_CotizacionDAO;
import com.mycompany.web.datos.LocalidadDAO;
import com.mycompany.web.datos.MarcaDAO;
import com.mycompany.web.datos.ModeloDAO;
import com.mycompany.web.datos.Periodo_De_PagoDAO;
import com.mycompany.web.datos.PersonaDAO;
import com.mycompany.web.datos.PolizaDAO;
import com.mycompany.web.datos.ProvinciaDAO;
import com.mycompany.web.datos.RevisionDAO;
import com.mycompany.web.datos.Tipo_De_ContratacionDAO;
import com.mycompany.web.datos.VehiculoDAO;
import com.mycompany.web.datos.VersionDAO;
import com.mycompany.web.modelo.Cliente;
import com.mycompany.web.modelo.Cobertura;
import com.mycompany.web.modelo.Cobertura_Detalle;
import com.mycompany.web.modelo.Cotizacion;
import com.mycompany.web.modelo.Estado_Poliza;
import com.mycompany.web.modelo.Linea_Cotizacion;
import com.mycompany.web.modelo.Localidad;
import com.mycompany.web.modelo.Marca;
import com.mycompany.web.modelo.Modelo;
import com.mycompany.web.modelo.Periodo_De_Pago;
import com.mycompany.web.modelo.Poliza;
import com.mycompany.web.modelo.Provincia;
import com.mycompany.web.modelo.Tipo_De_Contratacion;
import com.mycompany.web.modelo.Usuario;
import com.mycompany.web.modelo.Vehiculo;
import com.mycompany.web.modelo.Version;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorAprobarPolizas", urlPatterns = {"/vendedor", "/poliza_cliente"})
public class ControladorAprobarPolizas extends HttpServlet{
    PolizaDAO polizaDAO = new PolizaDAO();
    Tipo_De_ContratacionDAO tipo_contratacionDAO = new Tipo_De_ContratacionDAO();
    Periodo_De_PagoDAO periodo_pagoDAO = new Periodo_De_PagoDAO();
    Linea_CotizacionDAO lineaDAO = new Linea_CotizacionDAO();
    CotizacionDAO cotizacionDAO = new CotizacionDAO();
    VehiculoDAO vehiculoDAO = new VehiculoDAO();
    VersionDAO versionDAO = new VersionDAO();
    ModeloDAO modeloDAO = new ModeloDAO();
    MarcaDAO marcaDAO = new MarcaDAO();
    CoberturaDAO coberturaDAO = new CoberturaDAO();
    Cobertura_DetalleDAO cobertura_detalleDAO = new Cobertura_DetalleDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    PersonaDAO personaDAO = new PersonaDAO();
    LocalidadDAO localidadDAO = new LocalidadDAO();
    ProvinciaDAO provinciaDAO = new ProvinciaDAO();
    RevisionDAO revisionDAO = new RevisionDAO();

    ArrayList<Poliza> polizas = new ArrayList<Poliza>();
    Usuario usuario = new Usuario();
    Poliza poliza = new Poliza();
    Cliente cliente = new Cliente();

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = "";
        try{
            accion = request.getParameter("accion");
            if(accion.equals(null)){}
        }
        catch(Exception e){
            accion = "iniciarAprobacionDePoliza";
        }
        switch (accion) {
            case "iniciarAprobacionDePoliza":
                polizas = new ArrayList<Poliza>();
                polizas = polizaDAO.obtenerPolizasPendientes();;
                request.setAttribute("polizas", polizas);
                request.getRequestDispatcher("index.jsp").forward(request,response);
                break;
            case "solicitarVerPoliza":
                String num_poliza = request.getParameter("poliza_numero");
                
                //OBTENGO POLIZA
                poliza = polizaDAO.getPoliza(Integer.parseInt(num_poliza));
                
                //OBTENGO TIPO DE CONTRATACION
                Tipo_De_Contratacion tipo_contratacion = new Tipo_De_Contratacion();
                if(poliza.getTipo_contratacion().getIdtipocontratacion() > 0){
                    tipo_contratacion = tipo_contratacionDAO.getTipoContratacion(poliza.getTipo_contratacion().getIdtipocontratacion());
                }
                poliza.setTipo_contratacion(tipo_contratacion);

                //OBTENGO PERIODO DE PAGO
                Periodo_De_Pago periodo_pago = new Periodo_De_Pago();
                if(poliza.getPeriodo_pago().getIdperiodopago() > 0){
                    periodo_pago = periodo_pagoDAO.getPeriodoPago(poliza.getPeriodo_pago().getIdperiodopago());
                }
                poliza.setPeriodo_pago(periodo_pago);
                
                //OBTENGO LINEA
                Linea_Cotizacion linea = new Linea_Cotizacion();
                linea = lineaDAO.getLinea(poliza.getLinea_cotizacion().getIdlineacotizacion());

                //OBTENGO COTIZACION
                Cotizacion cotizacion = new Cotizacion();
                cotizacion = cotizacionDAO.getCotizacion(linea.getCotizacion().getIdcotizacion());

                //OBTENGO VEHICULO
                Vehiculo vehiculo = new Vehiculo();
                vehiculo = vehiculoDAO.getVehiculo(cotizacion.getVehiculo().getIdvehiculo());
                
                //OBTENGO CLIENTE
                cliente = clienteDAO.getCliente(vehiculo.getCliente().getIdCliente());

                //OBTENGO LOCALIDAD
                Localidad localidad_1 = localidadDAO.getLocalidad(cliente.getLocalidad().getIdlocalidad());

                //OBTENGO PROVINCIA
                Provincia provincia_1 = provinciaDAO.getProvincia(localidad_1.getProvincia().getIdprovincia());

                //OBTENGO VERSION
                Version version = new Version();
                version = versionDAO.getVersion(vehiculo.getVersion().getIdversion());

                //OBTENGO MODELO
                Modelo modelo = new Modelo();
                modelo = modeloDAO.getModelo(version.getModelo().getIdmodelo());

                //OBTENGO MARCA
                Marca marca = new Marca();
                marca = marcaDAO.getMarca(modelo.getMarca().getIdmarca());

                //OBTENGO COBERTURA
                Cobertura cobertura = new Cobertura();
                cobertura = coberturaDAO.getCobertura(linea.getCobertura().getIdcobertura());
                
                //AGREGO PROVINCIA A LOCALIDAD
                localidad_1.setProvincia(provincia_1);
                //AGREGO LOCALIDAD A CLIENTE
                cliente.setLocalidad(localidad_1);
                //AGREGO MARCA A MODELO
                modelo.setMarca(marca);
                //AGREGO MODELO A VERSION
                version.setModelo(modelo);
                //AGREGO VERSION A VEHICULO
                vehiculo.setVersion(version);
                //AGREGO VEHICULO A COTIZACION
                cotizacion.setVehiculo(vehiculo);
                //AGREGO COTIZACION A LINEA
                linea.setCotizacion(cotizacion);
                //AGREGO COBERTURA A LINEA
                linea.setCobertura(cobertura);
                //AGREGO LINEA A POLIZA
                poliza.setLinea_cotizacion(linea);
                
                //OBTENGO DETALLES DE COBERTURA
                Cobertura_Detalle cobertura_detalle = cobertura_detalleDAO.listar(cobertura);
                request.setAttribute("poliza", poliza);
                request.setAttribute("cliente", cliente);
                //request.setAttribute("usuario", usuario);
                request.setAttribute("cobertura_detalle", cobertura_detalle);
                request.getRequestDispatcher("vendedor_poliza.jsp").forward(request,response);
                break;
            case "aprobarPoliza":
                poliza.setEstadopoliza(Estado_Poliza.APROBADA);
                poliza.setUsuario(usuario);
                polizaDAO.actualizarEstadoPoliza(poliza);
                request.setAttribute("mensaje", "1");
                request.getRequestDispatcher("vendedor_resultado.jsp").forward(request,response);
                break;
            case "enviarARevisarPoliza":
                poliza.setEstadopoliza(Estado_Poliza.EN_REVISIÓN);
                Usuario us = new Usuario();
                us.setLegajo("-1");
                poliza.setUsuario(us);
                polizaDAO.actualizarEstadoPoliza(poliza);
                revisionDAO.agregar(poliza.getNumeropoliza());
                request.setAttribute("mensaje", "2");
                request.getRequestDispatcher("vendedor_resultado.jsp").forward(request,response);
                break;
            case "rechazarPoliza":
                poliza.setEstadopoliza(Estado_Poliza.RECHAZADA);
                poliza.setUsuario(usuario);
                polizaDAO.actualizarEstadoPoliza(poliza);
                request.setAttribute("mensaje", "3");
                request.getRequestDispatcher("vendedor_resultado.jsp").forward(request,response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String leg = "";

        try{
            usuario = (Usuario) request.getSession().getAttribute("usuario");
            leg = usuario.getLegajo();
        }
        catch(Exception e){
            //System.out.println("No hay Usuario");
        }

        if(!leg.equals("")){
            doPost(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath());
        }
    }
}
