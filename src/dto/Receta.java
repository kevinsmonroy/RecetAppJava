package dto;
import java.util.ArrayList;
import java.util.List;

public class Receta {
    private String id;
    private String nombre;
    private TipoReceta tipo;
    private List<String> ingredientes;
    private List<String> pasos;
    private int cocción;
    private String fotoPath;
    private Boolean esFavorita;

    // Encapsulamiento
    public String ObtenerId(){
        return id;
    }
    public void modificarId(String id){
        this.id= id;
    }
    public String obtenerNombre(){
        return nombre;
    }
    public void modificarNombre(){
        this.nombre=nombre;
    }
    public TipoReceta obtenerTipo(){
        return tipo;
    }
    public void modificarTipo(TipoReceta tipo){
        this.tipo= tipo;
    }

    public List<String> obtenerIngredientes(){
       return ingredientes;
    }
    public void ModificarIngredientes(List<String> ingredientes){
        this.ingredientes=ingredientes;
    }
    public List<String> obtenerPasos(){
        return pasos;
    }
    public void modificarPasos(List<String> pasos){
        this.pasos=pasos;
    }
    public int obtenerCoccion(){
        return cocción;
    }
    public void modificarCoccion(int cocción){
        this.cocción= cocción;
    }
    public String obtenerFotoPath(){
        return fotoPath;
    }
    public void modificarFotoPath(String fotoPath){
        this.fotoPath= fotoPath;
    }
    public boolean obtenerEsFavorita(){
        return esFavorita;
    }
    public void modificarEsFavorita(boolean esFavorita){
        this.esFavorita= esFavorita;
    }
}
