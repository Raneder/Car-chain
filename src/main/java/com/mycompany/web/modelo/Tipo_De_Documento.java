package com.mycompany.web.modelo;

public enum Tipo_De_Documento {

    CUIT(1, "CUIT"), CEDULA(2,"Cedula"), DNI(3,"DNI"), 
    LIBRETA_ENROLE(4,"Libreta de Enrole"), LIBRETA_CIVICA(5,"Libreta Civica"), 
    PASAPORTE(6,"Pasaporte");
    
    private int id;
    private String abreviatura;

    //CONSTRUCTORES
    private Tipo_De_Documento(int id, String abrev){
        this.abreviatura=abrev;
        this.id = id;
    }

    //GETTERS
    public String getAbreviatura() {
        return abreviatura;
    }
    public int getId() {
        return id;
    }
    
    //METODOS
    public static Tipo_De_Documento obtenerTipo(String t_doc){
        Tipo_De_Documento[] tipodoc = Tipo_De_Documento.values();
        Tipo_De_Documento tipo_documento = null;
        String tipo_doc = "";

        for (Tipo_De_Documento td : tipodoc){
            tipo_doc = td.toString();
            if(tipo_doc.equals(t_doc)){
                tipo_documento = td;
            }
        } 

        return tipo_documento;
    }

    public static Tipo_De_Documento obtenerTipoPorId(String t_doc){
        Tipo_De_Documento[] tipodoc = Tipo_De_Documento.values();
        Tipo_De_Documento tipo_documento = null;
        String tipo_doc = "0";

        for (Tipo_De_Documento td : tipodoc){
            tipo_doc = td.getId() + "";
            if(tipo_doc.equals(t_doc)){
                tipo_documento = td;
            }
        } 

        return tipo_documento;
    }

}

