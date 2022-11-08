package com.mycompany.web.modelo;

public enum Tipo_De_Usuario {
    ADMINISTRADOR(1, "Administrador"), VENDEDOR(2,"Vendedor"), 
    PERITO(3,"Perito"), GESTOR_DE_SINIESTROS(4,"Gestor de Siniestros");
    
    private int id;
    private String abreviatura;

    //CONSTRUCTORES
    private Tipo_De_Usuario(int id, String abrev){
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
    public static Tipo_De_Usuario obtenerTipo(String t_user){
        Tipo_De_Usuario[] tipouser = Tipo_De_Usuario.values();
        Tipo_De_Usuario tipo_usuario = null;
        String tipo_user = "";

        for (Tipo_De_Usuario tu : tipouser){
            tipo_user = tu.toString();
            if(tipo_user.equals(t_user)){
                tipo_usuario = tu;
            }
        } 
        
        return tipo_usuario;
    }
    
}
