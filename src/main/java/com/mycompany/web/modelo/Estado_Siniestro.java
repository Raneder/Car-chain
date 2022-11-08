package com.mycompany.web.modelo;

public enum Estado_Siniestro {
    
    PENDIENTE (1, "Pendiente"), RECHAZADA (2,"Rechazada"), 
    APROBADA (3, "Aprobada");
    
    private int id;
    private String abreviatura;

    //CONSTRUCTORES
    private Estado_Siniestro(int id, String abrev){
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
    public static Estado_Siniestro obtenerEstadoSiniestro(String e_siniestro){
        Estado_Siniestro[] ests_siniestro = Estado_Siniestro.values();
        Estado_Siniestro est_siniestro = null;
        String estado = "";

        for (Estado_Siniestro es : ests_siniestro){
            estado = es.toString();
            if(estado.equals(e_siniestro)){
                est_siniestro = es;
            }
        } 
        
        return est_siniestro;
    }

}
