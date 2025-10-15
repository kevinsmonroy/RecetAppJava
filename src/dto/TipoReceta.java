package dto;


public enum TipoReceta {
    DESAYUNO("Desayuno"),
    ALMUERZO("Almuerzo"),
    CENA("Cena"),
    POSTRE("Postre");

    private  final String nombre;
    TipoReceta(String nombre){
        this.nombre= nombre;
    }
    public String ObtenerNombre(){
        return  nombre;
    }
}
