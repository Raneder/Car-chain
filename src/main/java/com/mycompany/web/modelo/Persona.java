package com.mycompany.web.modelo;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Persona {
    private int idPersona;
    private Localidad localidad;
    private String nombres;
    private String apellidos;
    private Date fechanacimiento;
    private Tipo_De_Documento tipoDocumento;
    private String documento;
    private String domicilio;
    private String correo;
    private String telefono;
    private boolean sexo;
    private String contraseña;

    //CONSTRUCTORES
    public Persona(){

    }
    public Persona(int idPersona, Localidad localidad, String nombres, String apellidos, Date fechanacimiento,
            Tipo_De_Documento tipoDocumento, String documento, String domicilio, String correo, String telefono,
            boolean sexo, String contraseña) {
        this.idPersona = idPersona;
        this.localidad = localidad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
        this.sexo = sexo;
        this.contraseña = contraseña;
    }

    //GETTERS
    public int getIdPersona() {
        return idPersona;
    }
    public Localidad getLocalidad() {
        return localidad;
    }
    public String getNombres() {
        return nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public Date getFechanacimiento() {
        return fechanacimiento;
    }
    public Tipo_De_Documento getTipoDocumento() {
        return tipoDocumento;
    }
    public String getDocumento() {
        return documento;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public String getCorreo() {
        return correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public boolean isSexo() {
        return sexo;
    }
    public String getContraseña() {
        return contraseña;
    }
    
    //SETTERS
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public void setapellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    public void setTipoDocumento(Tipo_De_Documento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    //METODOS
    public String mostrarPersona() {
        return "----Persona---- \nidPersona=" + idPersona + ", \nlocalidad=" + localidad + ", \nnombres=" + nombres + ", \napellidos="
                + apellidos + ", \nfechanacimiento=" + fechanacimiento + ", \ntipoDocumento=" + tipoDocumento
                + ", \ndocumento=" + documento + ", \ndomicilio=" + domicilio + ", \nemail=" + correo + ", \ntelefono="
                + telefono + ", \nsexo=" + sexo + ", \ncontraseña=" + contraseña;
    }
    public boolean validarFecha(String fecha) {
        //CREAR LLAVE
        boolean llave = true;

        //OBTENER FECHA MAXIMA
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.YEAR, -18);
        Date dateMax = calendar1.getTime();
        
        //OBTENER FECHA MINIMA
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1900, Calendar.JANUARY, 01);
        Date dateMin = calendar2.getTime();
        
        //OBTENER FECHA ACTUAL Y COMPARAR
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            ParsePosition position = new ParsePosition(0);
            Date dateActual = sdf.parse(fecha, position);
            if(dateActual.after(dateMax) || dateActual.before(dateMin)){
                llave = false;
            }
        }
        catch(Exception e){
            llave = false;
        }
        return llave;
    }
    public int calcularEdad() {
        Calendar fecnac = Calendar.getInstance();
		fecnac.setTime(this.fechanacimiento);

        Calendar hoy = Calendar.getInstance();
        int diffYear = hoy.get(Calendar.YEAR) - fecnac.get(Calendar.YEAR);
        int diffMonth = hoy.get(Calendar.MONTH) - fecnac.get(Calendar.MONTH);
        int diffDay = hoy.get(Calendar.DAY_OF_MONTH) - fecnac.get(Calendar.DAY_OF_MONTH);
        
        if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear - 1;
        }
        return diffYear;
    }
    public String obtenerSexo(){
        if (this.sexo){
            return "Hombre";
        }else{
            return "Mujer";
        }
    }
    public String obtenerFNacimiento(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.fechanacimiento);
        //OBTENER DIA
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String dia_txt = dia + "";
        if(dia < 10){   dia_txt = "0" + dia_txt;    }
        
        //OBTENER MES
        int mes = calendar.get(Calendar.MONTH) + 1;
        String mes_txt = mes + "";
        if(mes < 10){   mes_txt = "0" + mes_txt;    }

        //OBTENER AÑO
        int año = calendar.get(Calendar.YEAR);

        return dia_txt + "/" + mes_txt + "/" + año;
    }
    public String obtenerCorreo(){
        String primero = this.correo.substring(0,3);
        String ultimo = this.correo.substring(this.correo.indexOf("@"));
        return primero + "***" + ultimo;
    }
    
    //SETTERS SOBRECARGADOS
    public void setFechanacimiento(String fecha) {
        //FORMATO DE FECHA ACTUAL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            ParsePosition position = new ParsePosition(0);
            this.fechanacimiento = sdf.parse(fecha, position); 
        }
        catch(Exception e){              
            System.out.println("Algo anda mal con la fecha");
        }
    }
    public void setSexo(String sexo) {
        if(sexo.equals("0")){
            this.sexo = false;
        }
        else {
            this.sexo = true;
        }
    }
    public String obtenerTitular(){
        return this.apellidos + ", " + this.nombres;
    }
    public String obtenerNombre(){
        return this.apellidos + " " + this.nombres;
    }
}