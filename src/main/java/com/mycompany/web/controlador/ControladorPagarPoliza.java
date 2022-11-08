package com.mycompany.web.controlador;

import java.io.IOException;
import java.util.ArrayList;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.mycompany.web.blockchain.Conexion;
import com.mycompany.web.datos.CoberturaDAO;
import com.mycompany.web.datos.Cobertura_DetalleDAO;
import com.mycompany.web.datos.CotizacionDAO;
import com.mycompany.web.datos.Linea_CotizacionDAO;
import com.mycompany.web.datos.MarcaDAO;
import com.mycompany.web.datos.ModeloDAO;
import com.mycompany.web.datos.PagoDAO;
import com.mycompany.web.datos.Periodo_De_PagoDAO;
import com.mycompany.web.datos.PolizaDAO;
import com.mycompany.web.datos.Tipo_De_ContratacionDAO;
import com.mycompany.web.datos.VehiculoDAO;
import com.mycompany.web.datos.VersionDAO;
import com.mycompany.web.modelo.Cliente;
import com.mycompany.web.modelo.Cobertura;
import com.mycompany.web.modelo.Cobertura_Detalle;
import com.mycompany.web.modelo.Cotizacion;
import com.mycompany.web.modelo.Estado_Poliza;
import com.mycompany.web.modelo.Linea_Cotizacion;
import com.mycompany.web.modelo.Marca;
import com.mycompany.web.modelo.MercadoPago;
import com.mycompany.web.modelo.Modelo;
import com.mycompany.web.modelo.Pago;
import com.mycompany.web.modelo.Periodo_De_Pago;
import com.mycompany.web.modelo.Poliza;
import com.mycompany.web.modelo.Tipo_De_Contratacion;
import com.mycompany.web.modelo.Vehiculo;
import com.mycompany.web.modelo.Version;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorPagarPoliza", urlPatterns = {"/polizas", "/webhooks"})
public class ControladorPagarPoliza extends HttpServlet{
    ArrayList<Poliza> polizas = new ArrayList<Poliza>();
    PolizaDAO polizaDAO = new PolizaDAO();
    Linea_CotizacionDAO lineaDAO = new Linea_CotizacionDAO();
    CotizacionDAO cotizacionDAO = new CotizacionDAO();
    VehiculoDAO vehiculoDAO = new VehiculoDAO();
    VersionDAO versionDAO = new VersionDAO();
    ModeloDAO modeloDAO = new ModeloDAO();
    MarcaDAO marcaDAO = new MarcaDAO();
    Tipo_De_ContratacionDAO tipo_contratacionDAO = new Tipo_De_ContratacionDAO();
    Periodo_De_PagoDAO periodo_pagoDAO = new Periodo_De_PagoDAO();
    CoberturaDAO coberturaDAO = new CoberturaDAO();
    Cobertura_DetalleDAO cobertura_detalleDAO = new Cobertura_DetalleDAO();
    PagoDAO pagoDAO = new PagoDAO();
    
    Tipo_De_Contratacion tipo_contratacion = new Tipo_De_Contratacion();
    Periodo_De_Pago periodo_pago = new Periodo_De_Pago();
    Cliente cliente = new Cliente();
    Poliza poliza = new Poliza();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "iniciarPagoDePoliza":
                cliente = (Cliente) request.getSession().getAttribute("cliente");
                polizas = new ArrayList<Poliza>();
                polizas = polizaDAO.obtenerPolizasCliente(cliente.getIdCliente());
                request.setAttribute("polizas", polizas);
                request.getRequestDispatcher("polizas_cliente.jsp").forward(request,response);
                break;
            case "visualizarPolizaAPagar":
                String num_poliza = request.getParameter("poliza_numero");

                //OBTENGO POLIZA
                poliza = polizaDAO.getPoliza(Integer.parseInt(num_poliza));
                cliente = (Cliente) request.getSession().getAttribute("cliente");
                
                //OBTENGO TIPO DE CONTRATACION
                tipo_contratacion = new Tipo_De_Contratacion();
                if(poliza.getTipo_contratacion().getIdtipocontratacion() > 0){
                    tipo_contratacion = tipo_contratacionDAO.getTipoContratacion(poliza.getTipo_contratacion().getIdtipocontratacion());
                }
                poliza.setTipo_contratacion(tipo_contratacion);

                //OBTENGO PERIODO DE PAGO
                periodo_pago = new Periodo_De_Pago();
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
                request.setAttribute("cobertura_detalle", cobertura_detalle);
                request.getRequestDispatcher("poliza_cliente.jsp").forward(request,response);
                break;
            case "solicitarRealizarPrimerPago":
                cargarListasPago(request);
                request.getRequestDispatcher("pagar_poliza.jsp").forward(request,response);
                break;
            case "solicitarRealizarPago":

                break;
            case "pagarPolizaPorPrimeraVez":
                //OBTENER TIPO DE CONTRATACION
                String tipo_contratacion_id = request.getParameter("tipo_contratacion");
                tipo_contratacion = tipo_contratacionDAO.getTipoContratacion(Integer.parseInt(tipo_contratacion_id));

                //OBTENER PERIODO DE PAGO
                String periodo_pago_id = request.getParameter("periodo_pago");
                periodo_pago = periodo_pagoDAO.getPeriodoPago(Integer.parseInt(periodo_pago_id));

                //OBTENER PRECIO FINAL
                double monto = poliza.getLinea_cotizacion().getMonto();
                double uno = 1;
                double cien = 100;
                double descuento = (double) periodo_pago.getDescuentoperiodopago();
                double monto_final = (monto * (uno - descuento/cien));

                double pagos = (double) periodo_pago.getCantidadmesespago();
                double precio_poliza = monto_final*pagos;          

                //CARGAR DATOS EN POLIZA
                poliza.setTipo_contratacion(tipo_contratacion);
                poliza.setPeriodo_pago(periodo_pago);
                poliza.setPreciopolizaactual(precio_poliza);
                poliza.calcularProximoVencimiento(periodo_pago.getCantidadmesespago());
                poliza.cargarFechaContratacion();
                poliza.cargarHoraContratacion();
                poliza.setEstadopoliza(Estado_Poliza.VIGENTE);

                MercadoPago mercado = new MercadoPago();
                try {
                    String titulo = "Poliza N° "+ poliza.getNumeropoliza();

                    String descripcion = "Contratacion: " + tipo_contratacion.getNombrecontratacion();

                    int cantidad = periodo_pago.getCantidadmesespago();

                    Preference preference = mercado.crearPreferencia(titulo, descripcion, cantidad, monto_final);
                    request.setAttribute("pref_id", preference.getId());
                }
                catch (MPException | MPApiException e) {
                    System.out.println("Algo anda mal con la preferencia");
                }
                request.getRequestDispatcher("checkoutpro.jsp").forward(request,response);
                break;
            case "guardarPoliza":

                //ACTUALIZAR POLIZA
                polizaDAO.actualizarPoliza(poliza);

                //AGREGAR PAGO
                Pago pago = new Pago(poliza.getNumeropoliza(), poliza.getPreciopolizaactual());
                pagoDAO.agregar(pago);
                
                String status = request.getParameter("status");
                
                if(status.equals("approved")){
                    Conexion conexion = new Conexion();
                    String nombre_completo = cliente.getApellidos() + ", " + cliente.getNombres();
                    String doc = cliente.getDocumento();
                    String matr = poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getMatricula();
                    String num_pol = poliza.getNumeropoliza() + "";
                    String estado = poliza.getEstadopoliza().getAbreviatura();
                    String fecha = poliza.obtenerFechaVenc();

		            String contrato = conexion.conectar(nombre_completo, doc, matr, num_pol, estado, fecha);
		            System.out.println("Direccion de Contrato: " + contrato);

                    request.setAttribute("nombre_completo", nombre_completo);
                    request.setAttribute("doc", doc);
                    request.setAttribute("num_pol", num_pol);
                    request.setAttribute("estado", estado);
                    request.setAttribute("fecha", fecha);
                    request.setAttribute("matr", matr);
                    request.setAttribute("contrato", contrato);
                    request.getRequestDispatcher("poliza_blockchain.jsp").forward(request,response);
                }
                else{
                    request.getRequestDispatcher("fallo.jsp").forward(request,response);
                }
                break;
            case "Cancelar":
                response.sendRedirect(request.getContextPath());
            break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;

        try{
            cliente = (Cliente) request.getSession().getAttribute("cliente");
            id = cliente.getIdCliente();
        }
        catch(Exception e){
            //System.out.println("No hay Cliente");
        }
        
        String url = request.getRequestURL().toString();
        String url1 = "/webhooks";
        String url2 = "/polizas";
        if(url.contains(url1) && id > 0){
            request.getRequestDispatcher("load_page.jsp").forward(request,response);
        }
        else if(url.contains(url2) && id > 0){
            request.setAttribute("polizas", polizaDAO.obtenerPolizasCliente(cliente.getIdCliente()));
            request.getRequestDispatcher("polizas_cliente.jsp").forward(request,response);
        }
        else{
            response.sendRedirect(request.getContextPath());
        }
    }

    public void cargarListasPago(HttpServletRequest request){
        //OBTENGO TIPO DE CONTRATACION
        ArrayList<Tipo_De_Contratacion> tipos_contratacion = new ArrayList<Tipo_De_Contratacion>();
        tipos_contratacion = tipo_contratacionDAO.listar();

        //OBTENGO PERIODO DE PAGO
        ArrayList<Periodo_De_Pago> periodos_pago = new ArrayList<Periodo_De_Pago>();
        periodos_pago = periodo_pagoDAO.listar();

        request.setAttribute("poliza", poliza);
        request.setAttribute("tipos_contratacion", tipos_contratacion);
        request.setAttribute("periodos_pago", periodos_pago);
    }

    public void cargarElementosVehiculo(HttpServletRequest request){
        request.setAttribute("tipo_id", request.getParameter("tipo_id"));
        request.setAttribute("periodo_id", request.getParameter("periodo_id"));
    }
}
