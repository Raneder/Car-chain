package com.mycompany.web.modelo;

public enum Estado_Poliza {
    PENDIENTE(1, "Pendiente"), EN_REVISIÓN(2, "En Revisión"), 
    RECHAZADA(3, "Rechazada"), APROBADA(4, "Aprobada"), 
    VIGENTE(5, "Vigente"), IMPAGA(6, "Impaga"), 
    VENCIDA(7, "Vencida"), CANCELADA(8, "Cancelada");
    
    private int id;
    private String abreviatura;

    //CONSTRUCTORES
    private Estado_Poliza(int id, String abrev){
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
    public static Estado_Poliza obtenerEstadoPoliza(String e_poliza){
        Estado_Poliza[] ests_poliza = Estado_Poliza.values();
        Estado_Poliza est_poliza = null;
        String estado = "";

        for (Estado_Poliza ep : ests_poliza){
            estado = ep.name();
            if(estado.equals(e_poliza)){
                est_poliza = ep;
            }
        } 
        
        return est_poliza;
    }

}
